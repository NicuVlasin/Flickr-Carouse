
public interface myQueue<T> {
	
	//-------------------------------------------------------------------
	// Create an empty myQueue: my_create_empty
	//-------------------------------------------------------------------
	//public myStack my_create_empty(); --> Java does not support constructors in interfaces
		
	//-------------------------------------------------------------------
	// Basic Operation --> Check if myQueue is empty: my_is_empty
	//-------------------------------------------------------------------	
	public boolean my_is_empty();
	
	//-------------------------------------------------------------------
	// Basic Operation --> Get first element from front of myQueue: my_peek
	//-------------------------------------------------------------------
	public T my_peek() throws myException;
		
	//-------------------------------------------------------------------
	// Basic Operation --> Add element to back of myQueue: my_enqueue 
	//-------------------------------------------------------------------
	public void my_enqueue(T element) throws myException;
	
	//-------------------------------------------------------------------
	// Basic Operation --> Remove element from front of myQueue: my_dequeue 
	//-------------------------------------------------------------------	
	public void my_dequeue() throws myException;
	
}