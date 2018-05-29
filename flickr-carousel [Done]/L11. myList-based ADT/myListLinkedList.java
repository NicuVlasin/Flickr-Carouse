
public class myListLinkedList<T> implements myList<T> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private myNode<T> head;

	//-------------------------------------------------------------------
	// Basic Operation --> Create an empty MyList: my_create_empty
	//-------------------------------------------------------------------
	//public myList my_create_empty(){}
	
	public myListLinkedList(){
		this.head = null;
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Get number of elements in MyList: my_get_length
	//-------------------------------------------------------------------	
	public int my_get_length(){
		//1. Traverse the elements 
		myNode<T> current = this.head;
		int count = 0; 
		
		//2. For each node we find, mark it as counted and move to the next one
		while (current != null){
			current = current.getNext();
			count = count + 1;
		}
		
		//3. Return the number of nodes being counted
		return count;
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Get element at of MyList at a concrete position: my_get_element
	//-------------------------------------------------------------------
	public T my_get_element(int index) throws myException{
		//1. We look for the element
		myNode<T> current = this.head;
		int count = 0; 
		
		while ((current != null) && (count < index)){
			current = current.getNext();
			count = count + 1;
		}
		
		//2.1. If the index is a valid one we access to the element and return it.
		if (current != null){
			T element = current.getInfo();
			return element;
		}
		//2.2. If the index is a wrong one
		else
			throw new myException("Invalid Index. The ADT does not have such an Index Position");		
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Add element to MyList at a concrete position: my_add_element 
	//-------------------------------------------------------------------
	public void my_add_element(int index, T element) throws myException{
		//1. We look for the element
		myNode<T> current = this.head;
		myNode<T> previous = null;
		int count = 0; 
		
		while ((current != null) && (count < index)){
			previous = current;
			current = current.getNext();
			count = count + 1;
		}
		
		//2.1. If the index is a valid one
		if (count == index){
			//2.1.1. We Create the new node
			myNode<T> new_node = new myNode<T>(element);
			
			//2.1.2. We adjust the previous pointer
			if (previous == null)
				this.head = new_node;
			else
				previous.setNext(new_node);
			
			//2.1.3. We adjust the successor pointer
			new_node.setNext(current);		
		}		//2.2. If the index is a wrong one
		else
			throw new myException("Invalid Index. The ADT does not have such an Index Position");	
				
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Remove element of MyList at a concrete position: my_remove_element 
	//-------------------------------------------------------------------	
	public void my_remove_element(int index) throws myException{
		//1. We look for the element
		myNode<T> current = this.head;
		myNode<T> previous = null;
		int count = 0; 
		
		while ((current != null) && (count < index)){
			previous = current;
			current = current.getNext();
			count = count + 1;
		}
		
		//2.1. If the index is a valid one
		if (count == index){
			//2.1.1. We adjust the previous pointer
			if (previous == null)
				this.head = current.getNext();
			else
				previous.setNext(current.getNext());			
		}
		//2.2. If the index is a wrong one
		else
			throw new myException("Invalid Index. The ADT does not have such an Index Position");						
	}
	
}
