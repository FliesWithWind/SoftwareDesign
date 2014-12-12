package network;

import java.io.IOException;
import java.net.*;

public class Listener implements Runnable
{
	private static final int PORT = 44444;

	private ServerSocket		ss;
	
	// thread performs
	public void run()
	{
		// create listening socket
		System.out.println("Creating Listening Socket...");
		
		try
		{
			ss = new ServerSocket(PORT);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return;
		}

		// listen client
		System.out.println("Listening...");
		while(true)
		{
			try
			{
				Socket cs = ss.accept();
				if(cs == null)
				{
					System.out.println("");
					break;
				}
				
				System.out.println("Client " + 
									cs.getInetAddress().getHostName() + "(" + 
									cs.getInetAddress().getHostAddress() + ")" +
									" connected!");
				new Thread(new Network(cs)).start();
			}
			catch(IOException e)
			{
				e.printStackTrace();
				return;
			}
		}
	}
	
	// close server
	public void closeSocket()
	{
		try
		{
			ss.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
