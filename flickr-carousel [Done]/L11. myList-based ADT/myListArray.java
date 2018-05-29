
import java.lang.reflect.Array;

public class myListArray<T> implements myList<T> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private T[] items; 
	private int num_items;
	private int items_size;
	
	//-------------------------------------------------------------------
	// Basic Operation --> Create an empty myList: my_create_empty
	//-------------------------------------------------------------------
	//public myList my_create_empty(){}
	
	public myListArray(Class<?> t, int size){
		@SuppressWarnings("unchecked")
		final T[] i = (T[]) Array.newInstance(t, size);
		this.items = i;		
		this.num_items = 0;
		this.items_size = size;
	}
		
	//-------------------------------------------------------------------
	// Basic Operation --> Get number of integers in myList: my_get_length
	//-------------------------------------------------------------------	
	public int my_get_length(){
		return this.num_items;
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Get integer of myList at a concrete index: my_get_element
	//-------------------------------------------------------------------
	public T my_get_element(int index) throws myException{
		//1. If the index is a valid one we access to the element and return it.
		if ((index >= 0) && (index < this.num_items)){
			T value = this.items[index];				
			return value;
		}
		//2. If the index is a wrong one. 
		else{
			//2.1. If the index belongs to the array, but the index is currently non-occupied 
			if ((index >= this.num_items) && (index < this.items_size))
				throw new myException("Trying to Access to a Non-ocuppied Index Position of the ADT");
			//2.2. If the index does not belong to the array 
			else
				throw new myException("Invalid Index. The ADT does not have such an Index Position");
		}
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Add integer to myList at a concrete index: my_add_element 
	//-------------------------------------------------------------------
	public void my_add_element(int index, T value) throws myException{
		//1. If the array is full
		if (this.num_items == this.items_size)
			throw new myException("ADT is Full; Impossible to Add a new Element to it");
		//2. If the array is not full
		else{
			//2.1. If the index is correct, we insert the new element to the array
			if ((index >= 0) && (index <= this.num_items)){
				//2.1.1. We move all the elements in the array to make room for the new element
				for (int i = (this.num_items - 1); i >= index; i--)
					this.items[i+1] = this.items[i];
				//2.1.2. We insert the element and increment the number of elements in the array.
				this.items[index] = value;	
				this.num_items = this.num_items + 1;
			}
			//2.2. If the index is incorrect
			else{
				//2.2.1. If the index belongs to the array, but the index is currently non-occupied 
				if ((index > this.num_items) && (index < this.items_size))
					throw new myException("Trying to Add after First Non-occupied Index Position of the ADT");
				//2.2.2. If the index does not belong to the array 
				else
					throw new myException("Invalid Index. The ADT does not have such an Index Position");
			}
		}		
	}
	
	//-------------------------------------------------------------------
	// Basic Operation --> Remove index of myList: my_remove_element 
	//-------------------------------------------------------------------	
	public void my_remove_element(int index) throws myException{
		//1. If the index is a valid one we remove the element
		if ((index >= 0) && (index < this.num_items)){
			for (int i = index; i < (this.num_items - 1); i++)
				this.items[i] = this.items[i+1];
			this.num_items = this.num_items - 1;		
		}
		//2. If the index is a wrong one. 
		else{
			//2.1. If the index belongs to the array, but the index is currently non-occupied 
			if ((index >= this.num_items) && (index < this.items_size))
				throw new myException("Trying to Remove from a Non-ocuppied Index Position of the ADT");
			//2.2. If the index does not belong to the array 
			else
				throw new myException("Invalid Index. The ADT does not have such an Index Position");
		}		
	}	


}