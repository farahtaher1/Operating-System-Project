package os;

public class intVariable extends Variable{

	int value;
	
	public int getValue() {
		
		return value;
	}
	
	public intVariable(String x, int y) {
		super(x);
		this.value = y;
	
	}
	
}
