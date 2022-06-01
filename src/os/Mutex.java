package os;

import java.util.LinkedList;
import java.util.Queue;

public class Mutex {
	boolean available=true;
	int ownerId;
	Queue queue =new LinkedList<Process>();
	//MutexType type;
	
	
	public Mutex(){
		
	//	this.type=type;
	}
	
	public void semWait(Process p,Scheduler s) {
		
		
		p.PCB.PC_incrementor();
		if (available == true) {
			ownerId = p.PCB.processID;
			available = false;
			} 
		else {
			queue.add(p);
			p.PCB.p = ProcessStates.blocked;
			s.currentProcess=(Process)s.ready.remove();
			
		}
	}
	
	public void semSignal(Process p,Scheduler s) {

		p.PCB.PC_incrementor();
		if(ownerId == p.PCB.processID) {
			if (queue.isEmpty())
				available = true;
			else {
			Process c=(Process)queue.remove();
			s.ready.add(c);
			ownerId=c.PCB.processID;
			}
			}
	}
 
	public static void main(String Args[]) {
		
	}
	
	
}
