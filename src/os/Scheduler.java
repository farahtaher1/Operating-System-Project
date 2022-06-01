package os;

import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {
	int count=0;
	Queue ready;
	Mutex input= new Mutex();
	Mutex output= new Mutex();
	Mutex file= new Mutex();
	Process currentProcess;
	
	
	public Scheduler() {
		ready=new LinkedList();
		
		
	}
	public void intialize(Process p) {
		ready.add(p);
	}
	
	public void dispatch() {
		
		if(currentProcess!=null) {
		   ready.add(currentProcess);
		}
		
		currentProcess=(Process)ready.remove();
		
		
	}
	public int getCount() {
		
		return count;
	}

	
}
