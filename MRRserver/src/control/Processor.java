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
	
	public Processor(){
		accountmanager = new AccountManager();
		roommanager = new RoomManager();
		reservationmanager = new ReservationManager();
	}
	

	/*** Client sends Account object, and server converts it to String form 
	 * @throws Exception */
	public Packet process(Packet packet) throws Exception
	{
		//For testing
		System.out.println("Packet recieved: " + packet.getFlag());
		switch(packet.getFlag()){
			case Packet.LOGIN:
				
				break;
				
			case Packet.MY_ACNT:
				
				break;
				
			case Packet.REGISTER:
				if(packet.getData()!=null)
					accountmanager.addRegistration((Account)packet.getData());
				break;
				
			case Packet.EDIT_ACNT:
				
				break;
				
			case Packet.MY_ROOMS:
				
				break;
				
			case Packet.CREATE_ROOM:
				Account owner = accountmanager.searchAccount(packet.getId());
				if(packet.getData()==null)
					System.out.println("Null data!");
				else if(owner!=null){
					roommanager.createRoom((Room)packet.getData(),owner);
					System.out.println("New room added for: " + owner.getName());
				}
				else
					System.out.println("Account does not exist.");
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
		return packet;
	}
	
	public AccountManager getAM(){
		return accountmanager;
	}
	
	public RoomManager getRM(){
		return roommanager;
	}
}
