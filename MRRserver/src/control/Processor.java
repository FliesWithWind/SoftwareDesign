package control;

import datatype.*;
import network.*;
import fileio.FileIO;

import java.util.ArrayList;


public class Processor
{
	// singleton /////////////////////////////////////////////
	private	static volatile Processor self;   				//
	public	static Processor getInstance()					//
	{														//
		if(self == null) synchronized(Processor.class)		//
			{ if(self == null) self = new Processor(); }	//
		return self;										//
	}/////////////////////////////////////////////////////////

	public static final String CURRENT_VERSION = "0.5";
	
	private AccountManager		accountmanager;
	private RoomManager			roommanager;
	private ReservationManager	reservationmanager;
		
	public Processor()
	{
		ArrayList<Account> acclist;
		ArrayList<Account> reglist;
		
		try	// read from the data file
		{
			acclist	= FileIO.loadData();
			reglist	= FileIO.loadRegisters();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			acclist	= new ArrayList<Account>();
			reglist	= new ArrayList<Account>();
			System.out.println("Created the new list");
		}
		
		ArrayList<Room>	roomlist		= new ArrayList<Room>();
		ArrayList<Reservation> rsrvlist	= new ArrayList<Reservation>();
		
		for(Account iter : acclist) // fill up roomlist and rsrvlist
		{
			for(Room e : iter.getMyrooms()) roomlist.add(e);
			for(Reservation e : iter.getMyreservations()) rsrvlist.add(e);
		}
		
		accountmanager		= new AccountManager(acclist, reglist);
		roommanager			= new RoomManager(roomlist);
		reservationmanager	= new ReservationManager(rsrvlist);
	}
	

	/*** Client sends Account object, and server converts it to String form ***/
	public Packet process(Packet packet) throws Exception
	{		
		//For testing
		System.out.println("Packet recieved: " + packet.getFlag());

		int result; // manager's error count
		Account tempacc;
		Room temproom;
		
		/*** Request that doesn't require account validation ***/
		if(packet.getFlag() == Packet.REGISTER)
		{
			result = accountmanager.addRegistration((Account)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			
			return new Packet(Packet._ACCEPTED, null, null, null);
			break;
		}
		
		// Account validation
		int acctype = accountmanager.validate(packet.getId(), packet.getPw());

		/*** Requests those requires user authority ***/
		if(acctype == 0) return new Packet(Packet._INVALID_ACNT, null, null, null);
		switch(packet.getFlag())
		{
		case Packet.LOGIN: // version check
			if(!((String)packet.getData()).equals(CURRENT_VERSION))
				return new Packet(Packet._OUTDATED_VERSION, null, null, CURRENT_VERSION);
			return new Packet(Packet._LOGIN, null, null, null);
			
		case Packet.MY_ACNT: // authority check > send account
			if(acctype != 3) if(!packet.getId().equals((String)packet.getData()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			tempacc = (Account) accountmanager.searchAccount(packet.getId()).clone();
			tempacc.setMyrooms(null);
			tempacc.setMyreservations(null);
			return new Packet(Packet._MY_ACNT, null, null, tempacc);
			
		case Packet.EDIT_ACNT: // authority check
			if(acctype != 3) if(!packet.getId().equals(((Account)packet.getData()).getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = accountmanager.editAccount((Account)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			return new Packet(Packet._ACCEPTED,null,null,null);
			
		case Packet.MY_RSRVS:
			if(acctype != 3) if(!packet.getId().equals((String)packet.getData()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			ArrayList<Reservation> temprsrvl = new ArrayList<Reservation>();
			for(Reservation iter : accountmanager.searchAccount((String)packet.getData()).getMyreservations())
			{
				Reservation element = (Reservation) iter.clone();
				element.setClient(null);
				element.getRoom().setOwner(null);
				element.getRoom().setReservations(null);
				temprsrvl.add(element);
			}
			return new Packet(Packet._MY_RSRVS, null, null, temprsrvl);

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

		case Packet.SEARCH_ROOMS:
			
			break;
		}
		
		/*** Requests those requires staff authority ***/
		if(acctype == 1) return new Packet(Packet._INVALID_ACNT, null, null, null);
		switch(packet.getFlag())
		{
		case Packet.MY_ROOMS:
			if(acctype != 3) if(!packet.getId().equals((String)packet.getData()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			ArrayList<Room> temprl = new ArrayList<Room>();
			for(Room iter : accountmanager.searchAccount((String)packet.getData()).getMyrooms())
			{
				Room element = (Room) iter.clone();
				element.setOwner(null);
				element.setReservations(null);
				temprl.add(element);
			}
			return new Packet(Packet._MY_ROOMS, null, null, temprl);
			
		case Packet.CREATE_ROOM:
			if(acctype != 3) if(!packet.getId().equals(((Room)packet.getData()).getOwner().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			Account owner = accountmanager.searchAccount(((Room)packet.getData()).getOwner().getId());
			result = roommanager.createRoom((Room)packet.getData(), owner);
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			return new Packet(Packet._ACCEPTED,null,null,null);
			
		case Packet.EDIT_ROOM:
			temproom = roommanager.searchRoom(((Room)packet.getData()).getName());
			if(acctype != 3) if(!packet.getId().equals(temproom.getOwner().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = roommanager.editRoom((Room)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			return new Packet(Packet._ACCEPTED,null,null,null);
			
		case Packet.REMOVE_ROOM:
			temproom = roommanager.searchRoom((String)packet.getData());
			if(acctype != 3) if(!packet.getId().equals(temproom.getOwner().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = roommanager.removeRoom((String)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			return new Packet(Packet._ACCEPTED,null,null,null);
		}
		
		/*** Requests those requires manager authority ***/
		if(acctype == 2) return new Packet(Packet._INVALID_ACNT, null, null, null);
		switch(packet.getFlag())
		{
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
			
			
		case Packet.QUERY_RSRVS:
				return new Packet(Packet._QUERY_RSRVS,null,null,reservationmanager.getList());	
		}
		
	}
}
