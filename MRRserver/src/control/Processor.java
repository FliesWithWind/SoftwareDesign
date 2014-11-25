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
	
<<<<<<< HEAD
	public Processor(){
		accountmanager = new AccountManager();
		roommanager = new RoomManager();
		reservationmanager = new ReservationManager();
	}
	
=======
	/*** Client sends Account object, and server converts it to String form */
>>>>>>> branch 'master' of https://github.com/FliesWithWind/SoftwareDesign
	public Packet process(Packet packet)
	{
<<<<<<< HEAD
		//For testing
		System.out.println("Packet recieved: " + packet.getFlag());
		switch(packet.getFlag()){
			case Packet.LOGIN:
				
				break;
				
			case Packet.MY_ACNT:
				
				break;
				
			case Packet.REGISTER:
				accountmanager.addAccount((Account)packet.getData());
				break;
				
			case Packet.EDIT_ACNT:
				
				break;
				
			case Packet.MY_ROOMS:
				
				break;
				
			case Packet.CREATE_ROOM:
				
				break;
				
			case Packet.EDIT_ROOM:
				
				break;
				
			case Packet.REMOVE_ROOM:
				
				break;
				
			case Packet.MY_RSRVS:
				
				break;
				
			case Packet.RESERVE:
				
				break;
				
			case Packet.REQ_CANCEL_RSRV:
				
				break;
				
			case Packet.OPEN_RSRV:
				
				break;
				
			case Packet.CLOSE_RSRV:
				
				break;
				
			case Packet.CANCEL_RSRV:
				
				break;
				
			case Packet.QUERY_REGS:
				return new Packet(Packet._QUERY_REGS,null,null,accountmanager.getRegisterList());
				//break;
				
			case Packet.ACCEPT_REG:
				
				break;
				
			case Packet.REJECT_REG:
				
				break;
				
			case Packet.SEARCH_ROOMS:
				
				break;
				
			case Packet.QUERY_RSRVS:
				
				break;				
			
		}
		return null;
	}
	
	public AccountManager getAM(){
		return accountmanager;
=======
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
>>>>>>> branch 'master' of https://github.com/FliesWithWind/SoftwareDesign
	}
}
