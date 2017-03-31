package elevator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class Elevator {
	
	/**
	 * @param input
	 */
	public static String filename= "src/resources/outputfile.txt";
	public static FileWriter fw;
	
	//Function to implement functionality of mode B
	public void modeB(String input)
	{	
		try {
			
			//fetching initial floor of elevator from input string
		String a[]=input.split(":");
		int initialelevator=Integer.parseInt(a[0]);
		
		//changed input string to make fetching of data easy
		a[1]=a[1].replace(",","-");
		
		//saved all the floor details in string array
		String[] requests=a[1].split("-");
		
		
		//classifying the request as uprequest(up request source)
		//as updestination(up request destination)
		//as downrequest (down request source)
		//as downdestination (down request destination)
		TreeSet<Integer> uprequest=new TreeSet<>();
		TreeSet<Integer> updestination=new TreeSet<>();
		TreeSet<Integer> downrequest=new TreeSet<>();
		TreeSet<Integer> downdestination=new TreeSet<>();
		
		//moving cursor by 2 pointers because we have input in pairs i.e source and destination
		for(int i=0;i<requests.length;i=i+2)
		{	
			//checking for source and destination floors to determine it is uprequest or downrequest
			if((Integer.parseInt(requests[i])-Integer.parseInt(requests[i+1]))>=1)
			{
			downrequest.add(Integer.parseInt(requests[i]));
			downdestination.add(Integer.parseInt(requests[i+1]));
			}
			else
			{
				uprequest.add(Integer.parseInt(requests[i]));
				updestination.add(Integer.parseInt(requests[i+1]));
				
			}
			
			
			
		}
		
		//floorsum variable will give total floors moved by elevator
		int floorsum=0;
		
		//condition 1 :when all request are for down request
		if(uprequest.size()==0)
		{	floorsum=Math.abs(initialelevator-downrequest.last())+downrequest.last()-downdestination.first();
			if(initialelevator!=downrequest.last())
			{
				System.out.print(initialelevator+" ");
				fw.write(initialelevator+" ");
			
			}
			for(int j=downrequest.last();j>=downdestination.first();j--)
			{
				if(downrequest.contains(j)||downdestination.contains(j))
				{
					System.out.print(j+" ");
					fw.write(j+" ");
				}
			}
			System.out.print("("+ floorsum+ ")");
			fw.write("("+ floorsum+ ")\n");
		}
		
		//condition 2: when all request are down request
		else if(downrequest.size()==0)
		{	if(initialelevator!=uprequest.first())
			{
			System.out.print(initialelevator+" ");
			fw.write(initialelevator+" ");
			}
			floorsum=Math.abs(initialelevator-uprequest.first())+(updestination.last()-uprequest.first());
			for(int i=uprequest.first();i<=updestination.last();i++)
			{
				if(uprequest.contains(i)||updestination.contains(i))
					{System.out.print(i+" ");
					fw.write(i+" ");
					}
			}
			System.out.print("("+ floorsum+ ")");
			fw.write("("+ floorsum+ ")\n");
			
		}
		//condition 3:when we have up requests and down requests 
		//but serving up request first is more efficient 
		else if(Math.abs(initialelevator-uprequest.first())<=Math.abs(initialelevator-downrequest.last()))
		{
			if(initialelevator!=uprequest.first())
			{
				System.out.print(initialelevator+" ");
				fw.write(initialelevator+" ");
			}
			floorsum=Math.abs(initialelevator-uprequest.first())+(updestination.last()-uprequest.first())+Math.abs(updestination.last()-downrequest.last())+downrequest.last()-downdestination.first();
			for(int i=uprequest.first();i<=updestination.last();i++)
			{
				if(uprequest.contains(i)||updestination.contains(i))
					{System.out.print(i+" ");
					fw.write(i+" ");
					}
			}
			
			int j;
			if(updestination.last()==downrequest.last())
			{	j=downrequest.last()-1;
			}
			else
			{
				j=downrequest.last();
			}
	
				for(;j>=downdestination.first();j--)
				{
					if(downrequest.contains(j)||downdestination.contains(j))
					{
						System.out.print(j+" ");
						fw.write(j+" ");
					}
				}
				
				System.out.print("("+ floorsum+ ")");	
				fw.write("("+ floorsum+ ")\n");
		}
		
		//condition 4:when we have up requests and down requests 
		//but serving down request first is more efficient
		else
		{
			floorsum=Math.abs(initialelevator-downrequest.last())+(downrequest.last()-downdestination.first())+Math.abs(downdestination.first()-uprequest.first())+(updestination.last()-uprequest.first());
			if(initialelevator!=downrequest.last())
				{
				System.out.print(initialelevator+" ");
				fw.write(initialelevator+" ");
				}
			for(int i=downrequest.last();i>=downdestination.first();i--)
			{
				if(downrequest.contains(i)||downdestination.contains(i))
				{
					System.out.print(i+" ");
					fw.write(i+" ");
				}
			}
			int j;
			if(downdestination.first()==uprequest.first())
			{
				j=uprequest.first()+1;
			}
			else
			{
				j=uprequest.first();
			}
			for(;j<=updestination.last();j++)
			{
				if(uprequest.contains(j)||updestination.contains(j))
					System.out.print(j+" ");
					fw.write(j+" ");
			}
			
			System.out.print("("+ floorsum+ ")");
			fw.write("("+ floorsum+ ")\n");
			
		}	
			
				
		}
		catch(Exception e)
		{
	
		}
		
	}
	
	//function to implement modeA logic
	public void modeA(String input)
	{	
		try {
			
			
			
		//fetching data from the input string
		String a[]=input.split(":");
		int initialelevator=Integer.parseInt(a[0]);
		
		a[1]=a[1].replace(",","-");
		
		String[] requests=a[1].split("-");
		if(initialelevator!=Integer.parseInt(requests[0]))
			{System.out.print(initialelevator+" ");
			
			//storing output in outputfile
			fw.write(initialelevator+" ");
			}
		int floorsum=0;
		for(int i=0;i<requests.length;i++)
		{
			if(i==0)
			{
				floorsum=Math.abs(initialelevator-Integer.parseInt(requests[i]));
				System.out.print(requests[i]+" ");
				fw.write(requests[i]+" ");
			}
			else
			{
				floorsum+=Math.abs(Integer.parseInt(requests[i])-Integer.parseInt(requests[i-1]));
				if(Integer.parseInt(requests[i])!=Integer.parseInt(requests[i-1]))
				{
					System.out.print(requests[i]+" ");
					fw.write(requests[i]+" ");
				}
				
			}
			
		}
		System.out.print("("+ floorsum+ ")");
		fw.write("("+ floorsum+ ")\n");
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //the true will append the new data
    	//appends the 
	}
	public static void main(String[] args) {
		
		Elevator elevator=new Elevator();
		try {
			fw = new FileWriter(filename);
			fw = new FileWriter(filename,true);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String temp;
		
		try {
			BufferedReader bufferReader = new BufferedReader(new FileReader("src/resources/sampleinput.txt"));
			String modeselection=bufferReader.readLine();
			
			while((temp = bufferReader.readLine()) != null) {	
				if(modeselection.equals("ModeB"))
				elevator.modeB(temp);
				else
					elevator.modeA(temp);
				System.out.println();
			}
			fw.close();
			bufferReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//elevator.modeA(input5);
	}
}
