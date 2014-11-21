package control;

import java.io.IOException;

import network.*;
import datatype.*;

public class PacketBuilder 
{
	// singleton /////////////////////////////////////////////////
	private	static volatile PacketBuilder self;   				//
	public	static PacketBuilder getInstance()					//
	{															//
		if(self == null) synchronized(PacketBuilder.class)		//
			{ if(self == null) self = new PacketBuilder(); }	//
		return self;											//
	}/////////////////////////////////////////////////////////////
	
	// private UIs uis; ***to be implemented*** has UI object
	private Network network;
	
	private String id;
	private String pw;
	
	private PacketBuilder()
	{
		// uis = uis; ***to be implemented*** initializes UI object
		network = Network.getInstance();
	}
	
	// Request to validate ID-PW
	public void login(Account inf)
	{
		try
		{
			network.send(new Packet(Packet.LOGIN, id, pw, null));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// View my account properties
	public void myAccount(Account inf)
	{
		try
		{
			network.send(new Packet(Packet.LOGIN, id, pw, null));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}
	
	
	
}
