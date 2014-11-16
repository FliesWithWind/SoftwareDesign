package control;

import datatype.*;
import network.*;
import fileio.FileIO;
import java.util.ArrayList;


public class Processor
{
	private FileIO				fileio;
	private AccountManager		accountmanager;
	private RoomManager			roommanager;
	private ReservationManager	reservationmanager;
	
	public Packet process(Packet packet)
	{
		return packet;
	}
}
