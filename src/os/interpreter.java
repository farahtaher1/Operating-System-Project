package os;
import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class interpreter {
	os Os;
	Scheduler Scheduler;
	static String []q;
	public interpreter() {
	
	}
	
	
	
     public void setOS(os x,Scheduler y ) {
    	 
    	 this.Os=x;
    	 this.Scheduler= y;
    	 
    	 
    	 
     }
	 public void interpretered(Process currentProcess) throws IOException{
		// String filename = currentProcess.filename;
		 try {
			 
			 
		        FileReader FR = new FileReader(currentProcess.filename);
		        BufferedReader buffread = new BufferedReader(FR);
		      

		        String str = "";
		
		      
		        for(int x = 0; x < currentProcess.PCB.PC_counter; x++){
				    buffread.readLine();
				}
		        
				str = buffread.readLine();
				if(str==null) {
					Scheduler.currentProcess=null;
					 
					Scheduler.count=0;
					return;
				}
		        	  q = str.split(" ");
		        	
		          
		 
		        buffread.close();
		       

		   
		 }
		 
		         catch (Exception ex) {
		            System.out.println("exception 2, " );
		            ex.printStackTrace();
		        }
		 
		 
		 
	       
		
			
			
				if(q[0].equals("print")) {
					Os.print(currentProcess,q[1]);
				}
				else if(q[0].equals("assign")) {
					String x = q[1];
					String y = q[2];
					if(y .equals("input")) {
						if(currentProcess.PCB.half==true) {
							currentProcess.PCB.half=false;
							Os.assignv(currentProcess, x, currentProcess.PCB.m);
						}else {
							Os.assignInput(currentProcess,x);
							
						}
						
					}
					else if(y.equals("readfile")) {
						if(currentProcess.PCB.half==true) {
							currentProcess.PCB.half=false;
							Os.assignv(currentProcess, x, currentProcess.PCB.m);
						}else {
						Os.assignFromFile(currentProcess,x,q[3]);}
					}
					else {
						Os.assignv(currentProcess,x,y);
					}
					
				}
			   else if(q[0].equals("writeFile")) {
				   Os.writeFile(q[1],q[2],currentProcess);
			   }
			   else if(q[0] .equals("readFile")) {
				   Os.readFile(q[1],currentProcess);
			   }
			   else if(q[0].equals("printFromTo")) {
				   String x = q[1];
					String y = q[2];
				   Os.printFromTo(x,y,currentProcess);
				   
			   }
			   else if(q[0].equals("semWait")) {
				   Os.clock = Os.clock+1;
					Scheduler.count= Scheduler.count-1;
				   String x = q[1];
				   if(x.equals("userInput")) {
					   
					   Scheduler.input.semWait(currentProcess,Scheduler);
				   }
				   else if(x.equals("file")) {
					   Scheduler.file.semWait(currentProcess,Scheduler);
				   }
				   else {
					   Scheduler.output.semWait(currentProcess,Scheduler);
				   }
				   
			   }
			   else if(q[0].equals("semSignal")) {
					Os.clock = Os.clock+1;
					Scheduler.count= Scheduler.count-1;
				   String x = q[1];
				   if(x.equals("userInput")) {
					   Scheduler.input.semSignal(currentProcess, Scheduler);
				   }
				   else if(x.equals("file")) {
					   Scheduler.file.semSignal(currentProcess, Scheduler);
				   }
				   else {
					   Scheduler.output.semSignal(currentProcess, Scheduler);
				   }
				   
			   }
				
				
				
			}
			 
	
		 

	 
	 public static void main(String[]args) {

		 }



		 }
		
	  
			 
	 
	 

	

