// see:
// * https://www.flickr.com/services/developer/api/
// * https://www.flickr.com/services/api/response.json.html
// * https://www.flickr.com/services/api/flickr.photos.search.html
// * https://www.flickr.com/services/api/flickr.photos.getSizes.htm
var flickrApiUrl = 'https://api.flickr.com/services/rest/'
var flickerApiKey = '1c7b82878f0ad35b13a3fcf6e7ecded0'
var flickerApiFormat = 'json'
var loadingImageStr = '<img src="loading.gif" class="loading" alt="loading" />'


/*****************************************************************************/
// Global DOM Elements

var $sideBar = $('#side-bar')
var $searchButton = $('#search-button')
var $addTagButton = $('#add-tag-button')
var $tagsList = $('#tags-list')
var $carousel = $('#carousel')
var $mainImage = $('#main-image')
var $miniMap = $('#mini-map')
var $miniMapLeftButton = $('#mini-map-left-button')
var $miniMapRightButton = $('#mini-map-right-button')
var $mainImageInner = $('#main-image-inner')
var $miniMapInner = $('#mini-map-inner')
var $message = $('.message')
var $window = $(window)


/*****************************************************************************/
// Global State Variables

var pendingImages = 0
var currentImages = []
var currentImageId = null


/*****************************************************************************/
// Main Script

$window.resize(handleResize)
handleResize()


$addTagButton.click(function () {
    var $li = $('<li></li>')
    var $input = $('<input type="text" />')
    var $removeButton = $('<button>-</button>')

    $removeButton.click(function () {
        $li.remove()
    })

    $li.append($input)
    $li.append($removeButton)

    $tagsList.append($li)
})


$searchButton.click(function () {
    var tags = $tagsList
        .find('input')
        .get()
        .map(function (input) {
            return input.value.trim()
        })
        .filter(function (value) {
            return value !== ''
        })
        .join(',')

    if (tags === '') {
        showMessage('You must enter a tag...')
    } else {
        enterLoadingState()
        getPhotosByTags(tags, 'handleGetPhotosByTags')
    }
})


$window.keydown(function (event) {
    // right key
    if (event.keyCode === 39) {
        moveRight()
    // left key
    } else if (event.keyCode === 37) {
        moveLeft()
    }
})


$miniMapLeftButton.click(moveLeft)
$miniMapRightButton.click(moveRight)

showOnMouseOver($miniMapLeftButton)
showOnMouseOver($miniMapRightButton)


/*****************************************************************************/
// Functions That Use Global Variables

function getPhotosByTags(tags, cbName) {
    getUrl(flickrApiUrl + '?' + settingsToQueryString({
        format: flickerApiFormat,
        api_key: flickerApiKey,
        method: 'flickr.photos.search',
        tag_mode: 'all',
        tags: tags,
        jsoncallback: cbName,
    }))
}


function getPhotoSizes(id, cbName) {
    getUrl(flickrApiUrl + '?' + settingsToQueryString({
        format: flickerApiFormat,
        api_key: flickerApiKey,
        method: 'flickr.photos.getSizes',
        photo_id: id,
        jsoncallback: cbName,
    }))
}


function handleGetPhotosByTags(data) {
    if (data.photos.photo.length === 0) {
        showMessage('No Results')
        exitLoadingState()
    } else {
        pendingImages = data.photos.photo.length
        currentImages = []
        currentImageId = null
        $miniMapInner.hide()
        $mainImageInner.hide()

        data.photos.photo.forEach(function (photo) {
            getPhotoSizes(photo.id, 'handleGetPhotoSizes')
        })
    }
}


function handleGetPhotoSizes(data) {
    var id = currentImages.length
    var thumbnailSize = data.sizes.size.find(function (size) {
        return size.label.toLowerCase() === 'thumbnail'
    })
    var largeSize = data.sizes.size.reduce(function (state, next) {
        return parseInt(state.height, 10) > parseInt(next.height, 10)
            ? state
            : next
    }, {height: '0'})

    var $thumbnailImage = $('<img />')

    $thumbnailImage
        .attr('src', thumbnailSize.source)
        .load(function () {
            pendingImages--

            if (pendingImages <= 0) {
                populateMiniMap()
            }
        })
        .click(function() {
            displayImage(id)
        })
    constrainHeight(
        $thumbnailImage,
        thumbnailSize.height / thumbnailSize.width,
        $miniMapInner.height()
    )

    $miniMapInner.append($thumbnailImage)

    currentImages.push({
        id: id,
        thumbnailSize: thumbnailSize,
        largeSize: largeSize,
        $thumbnailImage: $thumbnailImage,
    })
}


function populateMiniMap() {
    exitLoadingState()
    displayImage(0)
}


function enterLoadingState() {
    $searchButton.html(loadingImageStr)
    $miniMapInner.html('')
    $mainImageInner.html('')
}


function exitLoadingState() {
    $searchButton.text('Search')
}


function showMessage(message) {
    $message.text(message)
    $miniMapInner.hide()
    $mainImageInner.hide()
}


function displayImage(id) {
    if (id === null) {
        return
    }
    currentImageId = id
    showMessage('')
    displayImageInMiniMap(id)
    displayImageInMain(id)
}


function displayImageInMiniMap(id) {
    $miniMapInner.show()

    var $img = currentImages[id].$thumbnailImage
    var left = ($miniMap.width() / 2)
        - $img.get(0).offsetLeft
        - ($img.width() / 2)

    $('#mini-map img').fadeTo('fast', 0.5)
    $img.fadeTo('fast', 1)
    $miniMapInner.animate({left: left}, 'fast')
}


function displayImageInMain(id) {
    $mainImageInner.html(loadingImageStr)
    $mainImageInner.show()

    var largeSize = currentImages[id].largeSize
    var $largeImage = $('<img />')

    var height = parseInt(largeSize.height, 10)
    var width = parseInt(largeSize.width, 10)
    var ratio = height / width
    var availableHeight = $mainImageInner.height()
    var availableWidth = $mainImageInner.width()

    $largeImage
        .attr('src', largeSize.source)
        .load(function () {
            if (id === currentImageId) {
                $mainImageInner.html($largeImage)
            }
        })
    constrainHeight($largeImage, ratio, height, availableHeight)
    constrainWidth($largeImage, ratio, $largeImage.attr('width'), availableWidth)
}


function handleResize() {
    $carousel.width($window.width() - $sideBar.outerWidth())
    displayImage(currentImageId)
}


function moveLeft() {
    if (currentImageId !== null && currentImageId > 0) {
        currentImageId--
        displayImage(currentImageId)
    }
}


function moveRight() {
    if (currentImageId !== null && currentImageId < currentImages.length - 1) {
        currentImageId++
        displayImage(currentImageId)
    }
}


/*****************************************************************************/
// General Purpose Functions

function getUrl(url) {
    $.get({
        url: encodeURI(url),
        error: function (_, __, error) {
            // TODO
            console.error('error', error)
        },
    })
}


function settingsToQueryString(settings) {
    var str = ''

    for (var key in settings) {
        if (settings.hasOwnProperty(key)) {
            str += key + '=' + settings[key] + '&'
        }
    }

    str = str.substr(0, str.length - 1)

    return str
}


function constrainHeight($img, ratio, height, availableHeight) {
    if (height > availableHeight) {
        height = availableHeight
        var width = height / ratio
    }

    $img.attr({
        height: height,
        width: width,
    })
}


function constrainWidth($img, ratio, width, availableWidth) {
    if (width > availableWidth) {
        width = availableWidth
        var height = width * ratio
    }

    $img.attr({
        height: height,
        width: width,
    })
}


function showOnMouseOver($el) {
    $el.mouseenter(function () {
        $el.fadeTo('fast', 0.5)
    }).mouseleave(function () {
        $el.fadeTo('fast', 0)
    })
}
