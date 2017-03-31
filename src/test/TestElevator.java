package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import elevator.Elevator;
import junit.framework.Assert;

public class TestElevator {

	@SuppressWarnings("deprecation")
	@Test
	public void test()
	{	
		try {
			//clearing the file that will store the output
			Elevator.fw = new FileWriter(Elevator.filename);
			
			//enabling file to append output
			Elevator.fw = new FileWriter(Elevator.filename,true);
			
		
		String temp;
		Elevator myelevator=new Elevator();
		
		BufferedReader bufferReader = new BufferedReader(new FileReader("src/resources/sampleinput.txt"));
		
		//fetching first line from input file that determine the mode to run
		String modeselection=bufferReader.readLine();
		
		//fetching data from input file line by line
		while((temp = bufferReader.readLine()) != null) {	
			if(modeselection.equals("ModeA"))
			myelevator.modeA(temp);
			
			else
				myelevator.modeB(temp);
			System.out.println();
		}
		
		//closing the file writer
		Elevator.fw.close();
		bufferReader.close();
		bufferReader=new BufferedReader(new FileReader("src/resources/outputfile.txt"));
		
		String expected;
		//skipping the first line of output as it gives same output for both the modes
		bufferReader.readLine();
		//storing second line of output in a String variable
		String actual=bufferReader.readLine();
		if(modeselection.equals("ModeA"))
		{
			expected="9 1 5 1 6 1 5 (30)";
		}
		else
		{
			expected="9 1 5 6 (13)";
		}
		
		//asserting for the expected output and actual output
		Assert.assertEquals(expected, actual);
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
	}

}
