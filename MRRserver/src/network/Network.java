package network;

import java.net.*;
import java.io.*;

import control.Processor;

public class Network implements Runnable
{
	private control.Processor	processor;
	private Socket				client;

	private ObjectInputStream	is;
	private ObjectOutputStream	os;
	
	// generates Input, Output Stream
	public Network(Socket client)
	{
		processor	= Processor.getInstance();
		this.client	= client;
		
		try
		{
			is = new ObjectInputStream(client.getInputStream());
			os = new ObjectOutputStream(client.getOutputStream());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	// thread performs
	public void run()
	{
		if(is == null || os == null) System.out.println("Stream Error");
		else while(true)
		{
			try
			{
				Packet packet = (Packet) is.readObject();	// read from client
				if(packet == null) break; // if client sends EOF, break the loop
				// process packet in processor and response to client
				Packet sendpacket = processor.process(packet);
				os.writeObject(sendpacket);
				System.out.println("Packet sent: " + sendpacket.getFlag());
			}
			catch(EOFException e)
			{
				System.out.println("Got EOF");
				break;
			}
			catch(ClassNotFoundException e)
			{
				System.out.println("Invalid packet form");
				e.printStackTrace();
				break;
			}
			catch(IOException e)
			{
				e.printStackTrace();
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// client disconnected : notify and close streams
		try
		{
			System.out.println("Client " +
					client.getInetAddress().getHostName() + "(" + 
					client.getInetAddress().getHostAddress() + ")" +
					" disconnected!");
			
			is.close();
			os.close();
			client.close();
			
			System.out.println("Streams closed succesfully.");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
}
