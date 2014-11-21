package network;

import control.*;

import java.net.*;
import java.io.*;

public class Network implements Runnable
{
	// singleton /////////////////////////////////////////
	private	static volatile Network self;   			//
	public	static Network getInstance()				//
	{													//
		if(self == null) synchronized(Network.class)	//
			{ if(self == null) self = new Network(); }	//
		return self;									//
	}/////////////////////////////////////////////////////
	
	
	private static final String	IP		= "127.0.0.1";	// server ip
	private static final int	PORT	= 44444;		// port
	
	private PacketProcessor packetprocessor;
	
	private Socket client;
	private ObjectInputStream	is;
	private ObjectOutputStream	os;
	
	
	private Network()
	{
		packetprocessor = PacketProcessor.getInstance();
	}
	
	// * Receiver
	// This is performed after sending packet to server,
	// receives packet from server and send it to packet processor.
	// After this receiving packet from server, disconnects.
	public void run()
	{
		Packet packet;
		
		try
		{	
			packet = receive();		// read from server
			disconnect();			// send EOF
			
			while((packet = receive()) != null)		// when non EOF
				packetprocessor.process(packet);	// process the packet
			
			closeStreams();	// disconnected : notify and close streams
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
	}
	
	// sends packet to server and runs packet receiver thread
	public void send(Packet packet) throws IOException
	{
		try
		{
			connect(IP, PORT);			// connect		
			os.writeObject(packet);		// send message
			(new Thread(this)).start(); // run receiver
		}
		catch(IOException e)
		{
			e.printStackTrace();
			packetprocessor.process(null);
			// send EOF to Packet Processor when connection error
		}
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