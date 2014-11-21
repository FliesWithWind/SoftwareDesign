package control;

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
	
	private PacketBuilder()
	{
		
	}
	
	public void register(Account inf)
	{
		Packet packet;
		
		packet.setFlag(flag);
	}
}
