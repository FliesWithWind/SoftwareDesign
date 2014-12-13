package console;

import java.util.Scanner;

import control.Processor;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Starting Server...");
		
		Processor.getInstance();
		
		(new Thread(new network.Listener())).run();
		
		Scanner s = new Scanner(System.in);
		
		while(true)
		{
	        String msg = s.nextLine();
	        
	        if(msg.equals("q")) return;
		}
	}
	
}
