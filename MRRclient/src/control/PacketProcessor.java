package control;

import datatype.*;
import network.Packet;

public class PacketProcessor 
{
	// singleton /////////////////////////////////////////////////
	private	static volatile PacketProcessor self;   			//
	public	static PacketProcessor getInstance()				//
	{															//
		if(self == null) synchronized(PacketProcessor.class)	//
			{ if(self == null) self = new PacketProcessor(); }	//
		return self;											//
	}/////////////////////////////////////////////////////////////
	
	
	private PacketProcessor ()
	{
	}
	
	public void process(Packet packet)
	{
		
	}
}
