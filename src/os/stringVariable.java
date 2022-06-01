package os;

public class stringVariable extends Variable{
	

String value;


public stringVariable(String x, String y) {
	super(x);
	this.value =y;
	
}

public String getValue() {
	
	return value;
}
}
