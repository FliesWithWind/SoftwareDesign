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
					if(accountmanager.validate(packet.getId(), packet.getPw())!=0)
						return new Packet(Packet._LOGIN,null,null,null);
					else
						return new Packet(Packet._INVALID_ACNT,null,null,null);
					
			case Packet.MY_ACNT:
					return new Packet(Packet._MY_ACNT,null,null,accountmanager.searchAccount(packet.getId()));
				
			case Packet.REGISTER:
				if(packet.getData()!=null)
					if(accountmanager.addRegistration((Account)packet.getData()))
						return new Packet(Packet._ACCEPTED,null,null,null);
					else
						return new Packet(Packet._REJECTED,null,null,null);
				break;
				
			case Packet.EDIT_ACNT:
				if(packet.getData()!=null)
					if(accountmanager.editAccount((Account)packet.getData()))
						return new Packet(Packet._ACCEPTED,null,null,null);
					else
						return new Packet(Packet._REJECTED,null,null,null);
				break;
				
			case Packet.MY_ROOMS:
				return new Packet(Packet._MY_ROOMS,null,null,accountmanager.searchAccount(packet.getId()).getMyrooms());
				
			case Packet.CREATE_ROOM:
				Account owner = accountmanager.searchAccount(packet.getId());
				if(owner!=null){
					roommanager.createRoom((Room)packet.getData(),owner);
					return new Packet(Packet._ACCEPTED,null,null,null);
				}else
					return new Packet(Packet._REJECTED,null,null,null);
				
			case Packet.EDIT_ROOM:
				if(packet.getData()!=null)
					if(roommanager.editRoom((Room)packet.getData()))
						return new Packet(Packet._ACCEPTED,null,null,null);
					else
						return new Packet(Packet._REJECTED,null,null,null);
				break;
				
			case Packet.REMOVE_ROOM:
				if(packet.getData()!=null)
					if(roommanager.removeRoom((String)packet.getData()))
						return new Packet(Packet._ACCEPTED,null,null,null);
					else
						return new Packet(Packet._REJECTED,null,null,null);
				break;
				
			case Packet.MY_RSRVS:
				return new Packet(Packet._MY_RSRVS,null,null,accountmanager.searchAccount(packet.getId()).getMyreservations());
				
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
				if(packet.getData()!=null)
					if(accountmanager.acceptRegistration((String)packet.getData()))
						return new Packet(Packet._ACCEPTED,null,null,null);
					else
						return new Packet(Packet._REJECTED,null,null,null);
				break;
				
			case Packet.REJECT_REG:
				if(packet.getData()!=null)
					if(accountmanager.rejectRegistration((String)packet.getData()))
						return new Packet(Packet._ACCEPTED,null,null,null);
					else
						return new Packet(Packet._REJECTED,null,null,null);
				break;
				
			case Packet.SEARCH_ROOMS:
				
				break;
				
			case Packet.QUERY_RSRVS:
					return new Packet(Packet._QUERY_RSRVS,null,null,reservationmanager.getList());			
			
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
