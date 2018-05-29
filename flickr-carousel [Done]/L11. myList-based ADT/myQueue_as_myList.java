
public class myQueue_as_myList<T> implements myQueue<T> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private myList<T> myInternalList;
	
	//-------------------------------------------------------------------
	// Create an empty myQueue: my_create_empty
	//-------------------------------------------------------------------
	//public myList my_create_empty(){}

	public myQueue_as_myList(int mode){
		if (mode == 1)
			this.myInternalList = new myListArrayList<T>();
		else
			this.myInternalList = new myListLinkedList<T>();
	}

	public myQueue_as_myList(Class<?> t, int size){
		this.myInternalList = new myListArray<T>(t, size);  
	}
		
	//-------------------------------------------------------------------
	// Basic Operation --> Check if myQueue is empty: my_is_empty
	//-------------------------------------------------------------------	
	public boolean my_is_empty(){
		return (this.myInternalList.my_get_length() == 0);
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Get first element from front of myQueue: my_peek
	//-------------------------------------------------------------------
	public T my_peek() throws myException{
		try{
			return this.myInternalList.my_get_element(this.myInternalList.my_get_length()-1);
		}
		catch(Exception ex){
			throw new myException("Trying to Access to an Empty myQueue");
		}
	}
		
	//-------------------------------------------------------------------
	// Basic Operation --> Add element to back of myQueue: my_enqueue 
	//-------------------------------------------------------------------
	public void my_enqueue(T element) throws myException{
		try{
			this.myInternalList.my_add_element(0, element);
		}
		catch(Exception ex){
			throw new myException("Trying to Add to a Full myQueue");
		}		
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Remove element from front of myQueue: my_dequeue 
	//-------------------------------------------------------------------	
	public void my_dequeue() throws myException{
		try{
			this.myInternalList.my_remove_element(this.myInternalList.my_get_length()-1);
		}
		catch(Exception ex){
			throw new myException("Trying to Remove from an Empty myQueue");
		}				
	}
	
}
