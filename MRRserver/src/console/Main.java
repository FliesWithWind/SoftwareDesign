package console;

import java.util.Scanner;

import control.Processor;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Starting Server...");
		
		Processor.getInstance();
		
		Thread server = (new Thread(new network.Listener()));

		server.start();
		
		Scanner s = new Scanner(System.in);

		while(true)
		{
	        String msg = s.nextLine();
	        
	        if(msg.equals("q"))
	        {
	        	System.out.println("Terminating Server...");
	        	server.interrupt();
	        	Processor.saveData();
	        	System.out.println("Good bye!");
	        	System.exit(0);;
	        }
		}
	}
	
}
