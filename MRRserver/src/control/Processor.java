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
	
	/*** Client sends Account object, and server converts it to String form */
	public Packet process(Packet packet)
	{
		String msg;
		Account inf = (Account) packet.getData();
		msg =	"ID           : " + inf.getId() + "\n" + 
				"PW           : " + inf.getPw() + "\n" + 
				"Type         : " + inf.getType() + "\n" + 
				"Name         : " + inf.getName() + "\n" + 
				"E-mail       : " + inf.getEmail() + "\n" + 
				"Phone Number : " + inf.getPhonenum() + "\n" + 
				"Univ_Comp    : " + inf.getUniv_comp();
		packet.setData(msg);
		return packet;
	}
}
