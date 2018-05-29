
import java.util.Scanner;

public class myMain_L11_myList_based_ADTs {
	
	//-------------------------------------
	//	select_option 
	//-------------------------------------		
	public static int select_option(){
		Scanner sc = new Scanner(System.in);
		int option = 0;
			
		System.out.println("--------------------------------------------------");
		System.out.println("	Select Concrete ADT myList Implementation");
		System.out.println("--------------------------------------------------");
		System.out.println("1. Array");
		System.out.println("2. ArrayList.");
		System.out.println("3. LinkedList.");
		
		boolean selected = false;
		
		while (selected == false){
			System.out.println("Please enter an option");
			try {
				option = sc.nextInt();
				sc.nextLine();
				if ((option >= 1) && (option <= 3))
					selected = true;
				else
					System.out.println("Sorry but the option must be 1..3");
				
			}
			catch (Exception e) {
				System.out.println("Sorry you did not enter a valid option");
				sc.next();
			}		
		}
		sc.close();
		
		return option;
	}
	
	//-------------------------------------
	//	test_of_all_methods 
	//-------------------------------------		
	public static void test_of_all_methods(){
		int mode = select_option();
		
		//-------------------------------------
		//	1. We create myStack 
		//-------------------------------------
		myStack<Integer> s1 = null;
		myStack<Boolean> s2 = null;
		myStack<String> s3 = null;
		
		switch(mode){
		case 1: 
			//m = my_create_empty(); //--> We cannot create a new myList using explicitly my_create_empty()
			Integer dummy1 = new Integer(0);
			s1 = new myStack_as_myList<Integer>(dummy1.getClass(), 10);  
			Boolean dummy2 = new Boolean(true);
			s2 = new myStack_as_myList<Boolean>(dummy2.getClass(), 10);  
			String dummy3 = new String("");
			s3 = new myStack_as_myList<String>(dummy3.getClass(), 10); 
			break;
		case 2: 
			//m = my_create_empty(); //--> We cannot create a new myList using explicitly my_create_empty()			
			s1 = new myStack_as_myList<Integer>(1); 
			s2 = new myStack_as_myList<Boolean>(1); 
			s3 = new myStack_as_myList<String>(1); 
			break;
		default: 
			//m = my_create_empty(); //--> We cannot create a new myList using explicitly my_create_empty()			
			s1 = new myStack_as_myList<Integer>(2); 
			s2 = new myStack_as_myList<Boolean>(2); 
			s3 = new myStack_as_myList<String>(2); 
			break;
		}
		
		//-------------------------------------
		//	2. Print the length 
		//-------------------------------------
		System.out.println("s1 Empty = " + s1.my_is_empty());
		s1.my_push(3);
		System.out.println("s1 Empty = " + s1.my_is_empty());
		
		System.out.println("s2 Empty = " + s2.my_is_empty());
		s2.my_push(true);
		System.out.println("s2 Empty = " + s2.my_is_empty());
	
		System.out.println("s3 Empty = " + s3.my_is_empty());
		s3.my_push("Ciao");
		System.out.println("s3 Empty = " + s3.my_is_empty());

		//-------------------------------------
		//	3. Peek and Pop
		//-------------------------------------
		System.out.println("s1 Top = " + s1.my_peek());
		s1.my_pop();
		try{
			System.out.println("s1 Empty = " + s1.my_peek());
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		try{
			s1.my_pop();
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}

		
		System.out.println("s2 Top = " + s2.my_peek());
		s2.my_pop();
		try{
			System.out.println("s2 Empty = " + s2.my_peek());
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		try{
			s2.my_pop();
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		
		System.out.println("s3 Top = " + s3.my_peek());
		s3.my_pop();
		try{
			System.out.println("s3 Empty = " + s3.my_peek());
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		try{
			s3.my_pop();
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}	
		
	}
	
	//-------------------------------------
	//	MAIN method 
	//-------------------------------------	
	public static void main(String[] args) {
		
		// 1. Create a myQueue of Integers
		myQueue<Integer> m1 = new myQueue_as_myList<Integer>(2);
		m1.my_enqueue(3);
		m1.my_enqueue(7);
		m1.my_enqueue(5);
		System.out.println(m1.my_is_empty());
		System.out.println(m1.my_peek());		
		
		// 2. Create a myQueue of myPair<Integer, Boolean>			
		myPair<Integer, Boolean> p1 = new myPair<Integer, Boolean>(3, true);
		myPair<Integer, Boolean> p2 = new myPair<Integer, Boolean>(7, false);
		myPair<Integer, Boolean> p3 = new myPair<Integer, Boolean>(5, true);
		
		myQueue<myPair<Integer, Boolean>> m2 = new myQueue_as_myList<myPair<Integer, Boolean>>(1);
		m2.my_enqueue(p1);
		m2.my_enqueue(p2);
		m2.my_enqueue(p3);
		
		System.out.println(m2.my_is_empty());
		System.out.println(m2.my_peek());			
		
		/*
		myPair<Integer, Boolean> p1 = new myPair<Integer, Boolean>(3, true);
		myPair<Boolean, String> p2 = new myPair<Boolean, String>(false, "Hello");
		
		
		myPair< myPair<Integer, Boolean>, myPair<Boolean, String> > p3 = 
				new myPair< myPair<Integer, Boolean>, myPair<Boolean, String> >(p1, p2);
		
		
		myPair<Integer, Boolean> p6 = new myPair<Integer, Boolean>(3, true);
		myPair<Integer, Boolean> p7 = new myPair<Integer, Boolean>(7, false);
		myPair<Integer, Boolean> p8 = new myPair<Integer, Boolean>(5, true);
		
		myList< myPair<Integer, Boolean> > m10 = new myListArrayList< myPair<Integer, Boolean> >(); 
		m10.my_add_element(0, p6);
		m10.my_add_element(1, p7);
		m10.my_add_element(2, p8);
		//m10.my_add_element(3, p2);
		*/
		
		
		
		
		
		
		
	}
		
}
