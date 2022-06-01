package os;

import java.util.*;
import java.io.*;
public class os {
interpreter myInterpreter;
Scheduler myScheduler;
int clock=0;
int sizeinput;
int sizeoutput;
int sizefile;
String[] memory=new String[40];
boolean block1=false;
boolean block2=false;
int last=0;
Scanner sc = new Scanner(System.in);





public os() {
	
}


public os(interpreter a, Scheduler b) {
	
	this.myInterpreter=a;
	this.myScheduler=b;
	a.setOS(this, this.myScheduler);
	try {
	    File disk = new File("disk.txt");
	    if (disk.createNewFile()) {
	      System.out.println("File created: " + disk.getName());
	    } else {
	      System.out.println("File already exists.");
	    }
	  } catch (IOException e) {
	    System.out.println("An error occurred.");
	    e.printStackTrace();
	  };
	  for(int i=0;i<40;i++) {
		  memory[i]="a";
		  System.out.print(memory[i]);
	  }
	
}



public void assignv(Process currentProcess, String name, String x) {
	

	boolean flag =true;
	
    try { 
        Integer.parseInt(x); 
    } catch(NumberFormatException e) { 
        flag= false; 
    } catch(NullPointerException e) {
        flag= false;
    }
    
    if(flag==false) {
    	
    	stringVariable v = new stringVariable(name,x);
    	
    	currentProcess.PCB.memory.add(v);
    }
    else {
    	int v = Integer.parseInt(x);
    	intVariable p = new intVariable(name,v);
    	currentProcess.PCB.memory.add(p);

    }
	currentProcess.PCB.PC_incrementor();
    clock = clock+1;
	myScheduler.count= myScheduler.count-1;
	
}


public void assignInput(Process CurrentProcess, String name) {
	
	
	
	String E = input();
	

	
    if(myScheduler.count==0) {
    	
    	CurrentProcess.PCB.half=true;
    	CurrentProcess.PCB.m=E;
    	return;
    }
	
	
		assignv(CurrentProcess,name, E);
		
		
	

	
}



public void print(Process CurrentProcess, String a) {
	CurrentProcess.PCB.PC_incrementor();
	
	for(int i=0;i<CurrentProcess.PCB.memory.size();i++) {
		
		if(CurrentProcess.PCB.memory.get(i).getName()==a) {
			System.out.println();
			

			
		if(CurrentProcess.PCB.memory.get(i) instanceof intVariable) {
			
		intVariable x=	(intVariable) CurrentProcess.PCB.memory.get(i);
			
			System.out.println(x.getValue());
		}
			
		
			
			
		}
		
		
		
		
	}
	
	clock = clock+1;
	myScheduler.count= myScheduler.count-1;
}




public  String input(){
	
	clock = clock+1;
	myScheduler.count= myScheduler.count-1;
	
	System.out.println("please input:");
while(true) {
	
	if(sc.hasNext()) {
		
		break;
	}
	
}
		return sc.next();
	
	
}



public void assignFromFile(Process currentProcess, String x, String y) {
	String e=readFile(y,currentProcess);

	
    if(myScheduler.count==0) {
    	
    	currentProcess.PCB.half=true;
    	currentProcess.PCB.m=e;
    	return;
    }
	assignv(currentProcess,x,e);

	
}



public void printFromTo(String x, String y, Process CurrentProcess) {
	intVariable e= new intVariable("aa", 3);
	intVariable d=new intVariable("aa", 3);

	for(int i=0;i<CurrentProcess.PCB.memory.size();i++) {
		
		if(CurrentProcess.PCB.memory.get(i).getName().equals(x)) {
			//System.out.println();
			
		 e=(intVariable)	CurrentProcess.PCB.memory.get(i);

		
		}
		
		

		
	}
	
	for(int i=0;i<CurrentProcess.PCB.memory.size();i++) {
		
		if(CurrentProcess.PCB.memory.get(i).getName().equals(y)) {
			//System.out.println();
			
		 d=(intVariable)	CurrentProcess.PCB.memory.get(i);

		
		}
		
	}
	for(int i =e.getValue(); i<=d.getValue();i++) {
		
		System.out.println(i);
	}
		
	CurrentProcess.PCB.PC_incrementor();
	clock = clock+1;
	myScheduler.count= myScheduler.count-1;
}

public void writeFile(String r, String a, Process CurrentProcess) throws IOException {
	
	
	String name="";
for(int i=0;i<CurrentProcess.PCB.memory.size();i++) {
		
		if(CurrentProcess.PCB.memory.get(i).getName().equals(r)) {
			
			
		stringVariable w = (stringVariable) CurrentProcess.PCB.memory.get(i);
		
		name=w.getValue();
			
		}}

	for(int i=0;i<CurrentProcess.PCB.memory.size();i++) {
		
		if(CurrentProcess.PCB.memory.get(i).getName().equals(a)) {
			//System.out.println();
			

			
		if(CurrentProcess.PCB.memory.get(i) instanceof intVariable) {
			
		intVariable x=	(intVariable) CurrentProcess.PCB.memory.get(i);
			
		FileOutputStream fos=new FileOutputStream(name, true);  
		String str =   Integer.toString(x.getValue());
		byte[] b = str.getBytes();
		fos.write(b);
		fos.close();
		}
			
		else if(CurrentProcess.PCB.memory.get(i) instanceof stringVariable) {
			
	stringVariable x=	(stringVariable) CurrentProcess.PCB.memory.get(i);
			
	FileOutputStream fos=new FileOutputStream(name, true);  
	String str = x.getValue();
	byte[] b = str.getBytes();
	fos.write(b);
	fos.close();
		}
			
			
		}
		
		
		
		
	}
	CurrentProcess.PCB.PC_incrementor();

	clock = clock+1;
	myScheduler.count= myScheduler.count-1;
	
}

public void output() {
	if(myScheduler.currentProcess !=null) {
	
	System.out.println("current Process is "+myScheduler.currentProcess.PCB.processID);
	
	if(myInterpreter.q!=null) {
		for(int i = 0; i < myInterpreter.q.length; i++){
			 System.out.print(myInterpreter.q[i]+" ");
			}
		System.out.println();
		}
	System.out.print("Ready queue [");

	for(int i=0;i<myScheduler.ready.size();i++) {
		Process n=(Process)myScheduler.ready.remove();
		System.out.print(n.PCB.processID+",");
		myScheduler.ready.add(n);
		
	}
	System.out.println("]");
	
	System.out.print("Blocked for input [");
	for(int i=0;i<myScheduler.input.queue.size();i++) {
		Process n=(Process)myScheduler.input.queue.remove();
		System.out.print(n.PCB.processID+",");
		myScheduler.input.queue.add(n);
	}
	System.out.println("]");
	
	System.out.print("Blocked for output [");
	for(int i=0;i<myScheduler.output.queue.size();i++) {
		Process n=(Process)myScheduler.output.queue.remove();
		System.out.print(n.PCB.processID+",");
		myScheduler.output.queue.add(n);
	}
	System.out.println("]");
	
	System.out.print("Blocked for file [");
	for(int i=0;i<myScheduler.file.queue.size();i++) {
		Process n=(Process)myScheduler.file.queue.remove();
		System.out.print(n.PCB.processID+",");
		myScheduler.file.queue.add(n);
	}
	System.out.println("]");
	
	
	
	}
	
	
	
}


public String readFile(String string, Process CurrentProcess) {
	
	clock = clock+1;
	myScheduler.count= myScheduler.count-1;
	
	String name="";
	for(int i=0;i<CurrentProcess.PCB.memory.size();i++) {
			
			if(CurrentProcess.PCB.memory.get(i).getName().equals(string)) {
				
				
			stringVariable w = (stringVariable) CurrentProcess.PCB.memory.get(i);
			
			name=w.getValue();
				
			}}
	
	
	 try {
	        FileReader FR = new FileReader(name);
	        BufferedReader buffread = new BufferedReader(FR);
	      

	        String str = buffread.readLine();
	
	     
	        	
	          
	  System.out.print(str);
	
	        buffread.close();
	       
	        return str;
	   
	 }
	 
	         catch (Exception ex) {
	            System.out.println("exception 2, " );
	           
	            ex.printStackTrace();
	            return "";
	        } 
	
}






public void runtime(int quantum, int p1, int p2, int p3) throws IOException {
	int counter =0;
	
	for(int i=0; i<400; i++) {
		
	if(clock==p1) {
		counter++;
		PCB pcb = new PCB(1);
		Process x = new Process(pcb,"Program_1.txt");
		myScheduler.intialize(x);
		System.out.println("process 1 has been initialised");
		
	}
	if(clock==p2) {
		counter++;
		PCB pcb = new PCB(2);
		Process x = new Process(pcb,"Program_2.txt");
		
		myScheduler.intialize(x);
		
	}
	if(clock==p3) {
		counter++;
		PCB pcb = new PCB(3);
		Process x = new Process(pcb,"Program_3.txt");
		myScheduler.intialize(x);
		
	}
	
	if(myScheduler.getCount()==0 && !myScheduler.ready.isEmpty()) {
		
		myScheduler.dispatch();
		
		myScheduler.count=quantum;
		
				
		
		System.out.println("process "+myScheduler.currentProcess.PCB.processID+ " has been dispatched");
		

	}
	if(myScheduler.getCount()==0 && myScheduler.ready.isEmpty()) {
		
		
		
		myScheduler.count=quantum;
		
				
		if(myScheduler.currentProcess!=null) {
		System.out.println("process "+myScheduler.currentProcess.PCB.processID+ " has been dispatched");
		}

	}
	
	if(myScheduler.count!=0) {
		
		if(myScheduler.currentProcess!=null) {
		myInterpreter.interpretered(myScheduler.currentProcess);	
		output();
		
		
		
		if(myScheduler.input.queue.size()-sizeinput==1) {
			myScheduler.count=quantum;
			sizeinput=myScheduler.input.queue.size();
			
			
		}
		if(myScheduler.output.queue.size()-sizeoutput==1) {
			myScheduler.count=quantum;
			sizeoutput=myScheduler.output.queue.size();
			
		}
		if(myScheduler.file.queue.size()-sizefile==1) {
			myScheduler.count=quantum;
			sizeoutput=myScheduler.file.queue.size();
			
		}
		
		
	}

	

		
		
		
	}
		
	}
	
	
	
}


public static void main(String args[]) throws IOException {
Scheduler s = new Scheduler();
interpreter i = new interpreter();
os OS = new os(i,s);
PCB pcb1 = new PCB(1);
Process p1 = new Process(pcb1,"Program_1.txt");
PCB pcb2 = new PCB(2);
Process p2 = new Process(pcb2,"Program_2.txt");
PCB pcb3 = new PCB(3);
Process p3 = new Process(pcb3,"Program_3.txt");

OS.output();

OS.runtime(2,0, 8,9 );



}
	
}
