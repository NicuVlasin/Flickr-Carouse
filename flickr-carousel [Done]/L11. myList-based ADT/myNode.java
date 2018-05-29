
public class myNode<T> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private T info;
	private myNode<T> next;
	
	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	public myNode(T n){
		this.info = n;
		this.next = null;
	}
	
	//--------------------------------------------------
	// Get methods
	//--------------------------------------------------
	public T getInfo(){
		return this.info;
	}

	public myNode<T> getNext(){
		return this.next;
	}	
	
	//--------------------------------------------------
	// Set methods
	//--------------------------------------------------
	public void setInfo(T n){
		this.info = n;
	}
	
	public void setNext(myNode<T> n){
		this.next = n;
	}
	
}
