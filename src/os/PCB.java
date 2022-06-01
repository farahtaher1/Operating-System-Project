package os;
import java.util.*;
public class PCB {
	
	int processID;
	int PC_counter=0;
	ProcessStates p = ProcessStates.ready;
	ArrayList<Variable> memory = new ArrayList<Variable>();
    boolean half = false;
    String m = "";
	public PCB(int processID) {
	this.processID = processID;
		
	}
	
	public void PC_incrementor() {
		PC_counter++;
	}
	
	public ArrayList<Variable> getMemory(){
		return this.memory;
	}
}
