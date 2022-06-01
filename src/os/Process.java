package os;

public class Process {
	String filename;
	PCB PCB;

	public Process (PCB PCB, String filename) {
		this.filename=filename;
		this.PCB  = PCB;
	}
}
