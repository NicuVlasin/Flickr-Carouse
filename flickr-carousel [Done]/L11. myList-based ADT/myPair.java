
public class myPair<T1,T2> {

	//--------------------------------------------------
	// Attributes
	//--------------------------------------------------
	private T1 first;
	private T2 second;
	
	//--------------------------------------------------
	// Constructor
	//--------------------------------------------------
	public myPair(T1 f, T2 s){
		this.first = f;
		this.second = s;
	}
	
	//--------------------------------------------------
	// Get methods
	//--------------------------------------------------
	public T1 getFirst(){
		return this.first;
	}

	public T2 getSecond(){
		return this.second;
	}
	
	//--------------------------------------------------
	// Set methods
	//--------------------------------------------------
	public void setFirst(T1 i){
		this.first = i;
	}

	public void setSecond(T2 i){
		this.second = i;
	}
	
}
