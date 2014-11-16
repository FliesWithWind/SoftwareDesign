package network;

import java.net.*;
import java.io.*;

public class Network
{
	private static final String	IP		= "127.0.0.1";	// server ip
	private static final int	PORT	= 44444;		// port
		
	private Socket client;
	private ObjectInputStream	is;
	private ObjectOutputStream	os;
	
	
	public Packet communicate(Packet packet)
	{
		try
		{
			
			connect(IP, PORT);		// connect		
			send(packet);			// send message
			packet = receive();		// read from server
			disconnect();			// send EOF
			
			while(true) 			// for graceful shutdown
				if(receive() == null)
				{
					closeStreams();	// disconnected : notify and close streams
					break;
				}
			
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Invalid packet form");
			e.printStackTrace();
			packet = null;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			packet = null;
		}

		
		return packet;
	}
	
	
	private void connect(String ip, int port) throws IOException
	{
		// generates socket
		System.out.println("Connecting...");
		client = new Socket(ip, port);
		System.out.println("Connected!");
		
		// generates Input, Output Stream
		os = new ObjectOutputStream(client.getOutputStream());
		is = new ObjectInputStream(client.getInputStream());
		System.out.println("Stream generated.");
	}
	
	private void send(Packet packet) throws IOException
	{
		os.writeObject(packet);
	}
	
	private Packet receive() throws IOException, ClassNotFoundException
	{
		return (Packet) is.readObject();
	}
	
	private void disconnect() throws IOException
	{
		client.shutdownInput();
	}
	
	private void closeStreams() throws IOException
	{
		System.out.println("Disconnected!");
		
		is.close();
		os.close();
		client.close();
		
		System.out.println("Streams closed succesfully.");
	}
}