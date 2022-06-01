package os;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class test {
	
	public static void main(String[]args) {
		FileReader FR;
		try {
			FR = new FileReader("file1.txt");
			BufferedReader buffread = new BufferedReader(FR);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
}
