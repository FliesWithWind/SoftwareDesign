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
		Account 	tempacc;
		Reservation	temprsrv;
		Room 		temproom;

		ArrayList<Reservation> 	temprsrvl;
		ArrayList<Reservation> 	temprsrvl2;
		ArrayList<Room> 		temprooml;
		ArrayList<Reservation> temprsrvlarr[] = new ArrayList[2];
				
		long tempdate;
		
		/*** Request that doesn't require account validation ***/
		if(packet.getFlag() == Packet.REGISTER)
		{
			result = accountmanager.addRegistration((Account)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			
			return new Packet(Packet._ACCEPTED, null, null, null);
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
			return new Packet(Packet._ACCEPTED, null, null, null);
			
		case Packet.MY_ACNT: // authority check > send account
			if(acctype != 3) if(!packet.getId().equals((String)packet.getData()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			tempacc = (Account) accountmanager.searchAccount(packet.getId()).clone();
			tempacc.setMyrooms(null);
			tempacc.setMyreservations(null);
			return new Packet(Packet._ACCEPTED, null, null, tempacc);
			
		case Packet.EDIT_ACNT: // authority check
			if(acctype != 3) if(!packet.getId().equals(((Account)packet.getData()).getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = accountmanager.editAccount((Account)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			return new Packet(Packet._ACCEPTED, null, null, null);
			
		case Packet.MY_RSRVS:
			if(acctype != 3) if(!packet.getId().equals((String)packet.getData()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			temprsrvl = new ArrayList<Reservation>();
			for(Reservation iter : accountmanager.searchAccount((String)packet.getData()).getMyreservations())
			{
				Reservation element = (Reservation) iter.clone();
				element.setClient(null);
				element.getRoom().setOwner(null);
				element.getRoom().setReservations(null);
				temprsrvl.add(element);
			}
			return new Packet(Packet._ACCEPTED, null, null, temprsrvl);

		case Packet.RESERVE:
			if(acctype != 3) if(!packet.getId().equals(((String[])packet.getData())[0]))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			tempacc = accountmanager.searchAccount(((String[])packet.getData())[0]);
			temproom = roommanager.searchRoom(((String[])packet.getData())[1]);
			tempdate = Long.parseLong(((String[])packet.getData())[2]);
			result = reservationmanager.reserve(temproom, tempdate, tempacc);
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			// send my reservation and reservations of the room
			temprsrvlarr[0] = new ArrayList<Reservation>();
			for(Reservation iter : tempacc.getMyreservations())
			{
				Reservation element = (Reservation) iter.clone();
				element.setClient(null);
				element.getRoom().setOwner(null);
				element.getRoom().setReservations(null);
				temprsrvlarr[0].add(element);
			}
			tempdate = Long.parseLong(((String[])packet.getData())[2]);
			temprsrvlarr[1] = new ArrayList<Reservation>();
			for(Reservation iter : temproom.getReservations())
				if(iter.getDate() > tempdate - 31 && iter.getDate() < tempdate + 31)
				{
					Reservation element = (Reservation) iter.clone();
					element.setClient(null);
					element.getRoom().setOwner(null);
					element.getRoom().setReservations(null);
					temprsrvlarr[1].add(element);
				}
			return new Packet(Packet._ACCEPTED, null, null, temprsrvlarr);
			
		case Packet.REQ_CANCEL_RSRV:
			tempdate = Long.parseLong(((String[])packet.getData())[1]);
			temproom = roommanager.searchRoom(((String[])packet.getData())[0]);
			temprsrv = reservationmanager.searchReservation(temproom, tempdate);
			if(acctype != 3) if(!packet.getId().equals(temprsrv.getClient().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = reservationmanager.requestCancelReservation(temprsrv);
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			// send my reservation and reservations of the room
			temprsrvlarr[0] = new ArrayList<Reservation>();
			for(Reservation iter : temprsrv.getClient().getMyreservations())
			{
				Reservation element = (Reservation) iter.clone();
				element.setClient(null);
				element.getRoom().setOwner(null);
				element.getRoom().setReservations(null);
				temprsrvlarr[0].add(element);
			}
			temprsrvlarr[1] = new ArrayList<Reservation>();
			for(Reservation iter : temproom.getReservations())
				if(	iter.getDate() > tempdate - 31 && iter.getDate() < tempdate + 31)
				{
					Reservation element = (Reservation) iter.clone();
					element.setClient(null);
					element.getRoom().setOwner(null);
					element.getRoom().setReservations(null);
					temprsrvlarr[1].add(element);
				}
			return new Packet(Packet._ACCEPTED, null, null, temprsrvlarr);
			
		case Packet.OPEN_RSRV:
			temprsrv = (Reservation)packet.getData();
			temproom = roommanager.searchRoom(temprsrv.getRoom().getName());
			if(acctype != 3) if(!packet.getId().equals(temproom.getOwner().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = reservationmanager.openReservation(temprsrv, temproom);
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			// send reservations of the room
			temprsrvl = new ArrayList<Reservation>();
			for(Reservation iter : temproom.getReservations())
				if(	iter.getDate() >= temprsrv.getDate() - 31 && 
					iter.getDate() <= temprsrv.getDate() + 31)
				{
					Reservation element = (Reservation) iter.clone();
					element.setClient(null);
					element.getRoom().setOwner(null);
					element.getRoom().setReservations(null);
					temprsrvl.add(element);
				}
			return new Packet(Packet._ACCEPTED, null, null, temprsrvl);	
			
		case Packet.CLOSE_RSRV:
			tempdate = Long.parseLong(((String[])packet.getData())[1]);
			temproom = roommanager.searchRoom(((String[])packet.getData())[0]);
			temprsrv = reservationmanager.searchReservation(temproom, tempdate);
			if(acctype != 3) if(!packet.getId().equals(temproom.getOwner().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = reservationmanager.closeReservation(temprsrv);
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			// send reservations of the room
			temprsrvl = new ArrayList<Reservation>();
			for(Reservation iter : temproom.getReservations())
				if(	iter.getDate() >= temprsrv.getDate() - 31 && 
					iter.getDate() <= temprsrv.getDate() + 31)
				{
					Reservation element = (Reservation) iter.clone();
					element.setClient(null);
					element.getRoom().setOwner(null);
					element.getRoom().setReservations(null);
					temprsrvl.add(element);
				}
			return new Packet(Packet._ACCEPTED, null, null, temprsrvl);	
			
		case Packet.CANCEL_RSRV:
			temproom = roommanager.searchRoom(((String[])packet.getData())[0]);
			temprsrv = reservationmanager.searchReservation(temproom,
					Long.parseLong(((String[])packet.getData())[1]));
			if(acctype != 3) if(!packet.getId().equals(temprsrv.getRoom().getOwner().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = reservationmanager.cancelReservation(temprsrv);
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			// send reservations of the room
			temprsrvl = new ArrayList<Reservation>();
			for(Reservation iter : temproom.getReservations())
				if(	iter.getDate() >= temprsrv.getDate() - 31 && 
					iter.getDate() <= temprsrv.getDate() + 31)
				{
					Reservation element = (Reservation) iter.clone();
					element.setClient(null);
					element.getRoom().setOwner(null);
					element.getRoom().setReservations(null);
					temprsrvl.add(element);
				}
			return new Packet(Packet._ACCEPTED, null, null, temprsrvl);	
			
		case Packet.SEARCH_ROOMS:
			temprooml = roommanager.primarySearch((Reservation)packet.getData());
			if(temprooml != null) return new Packet(Packet._SEARCH_PRI, null, null, temprooml);
			temprooml = roommanager.secondarySearch((Reservation)packet.getData());
			return new Packet(Packet._SEARCH_SEC, null, null, temprooml);
			
		case Packet.QUERY_RSRVS:
			temproom = roommanager.searchRoom(((String[])packet.getData())[0]);
			tempdate = Long.parseLong(((String[])packet.getData())[1]);
			temprsrvl = new ArrayList<Reservation>();
			for(Reservation iter : temproom.getReservations())
				if(iter.getDate() >= tempdate - 31 && iter.getDate() <= tempdate + 31)
				{
					Reservation element = (Reservation) iter.clone();
					element.setClient(null);
					element.getRoom().setOwner(null);
					element.getRoom().setReservations(null);
					temprsrvl.add(element);
				}
			return new Packet(Packet._ACCEPTED, null, null, temprsrvl);	
		}
		
		/*** Requests those requires staff authority ***/
		if(acctype == 1) return new Packet(Packet._INVALID_ACCESS, null, null, null);
		switch(packet.getFlag())
		{
		case Packet.MY_ROOMS:
			if(acctype != 3) if(!packet.getId().equals((String)packet.getData()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			temprooml = new ArrayList<Room>();
			for(Room iter : accountmanager.searchAccount((String)packet.getData()).getMyrooms())
			{
				Room element = (Room) iter.clone();
				element.setOwner(null);
				element.setReservations(null);
				temprooml.add(element);
			}
			return new Packet(Packet._ACCEPTED, null, null, temprooml);
			
		case Packet.CREATE_ROOM:
			temproom = (Room)packet.getData();
			if(acctype != 3) if(!packet.getId().equals(temproom.getOwner().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			Account owner = accountmanager.searchAccount(temproom.getOwner().getId());
			result = roommanager.createRoom(temproom, owner);
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			// send my rooms
			temprooml = new ArrayList<Room>();
			for(Room iter : temproom.getOwner().getMyrooms())
			{
				Room element = (Room) iter.clone();
				element.setOwner(null);
				element.setReservations(null);
				temprooml.add(element);
			}
			return new Packet(Packet._ACCEPTED, null, null, temprooml);
			
		case Packet.EDIT_ROOM:
			temproom = roommanager.searchRoom(((Room)packet.getData()).getName());
			if(acctype != 3) if(!packet.getId().equals(temproom.getOwner().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = roommanager.editRoom((Room)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			// send my rooms
			temprooml = new ArrayList<Room>();
			for(Room iter : temproom.getOwner().getMyrooms())
			{
				Room element = (Room) iter.clone();
				element.setOwner(null);
				element.setReservations(null);
				temprooml.add(element);
			}
			return new Packet(Packet._ACCEPTED, null, null, temprooml);
			
		case Packet.REMOVE_ROOM:
			temproom = roommanager.searchRoom((String)packet.getData());
			if(acctype != 3) if(!packet.getId().equals(temproom.getOwner().getId()))
				return new Packet(Packet._INVALID_ACCESS, null, null, null);
			result = roommanager.removeRoom((String)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			// send my rooms
			temprooml = new ArrayList<Room>();
			for(Room iter : temproom.getOwner().getMyrooms())
			{
				Room element = (Room) iter.clone();
				element.setOwner(null);
				element.setReservations(null);
				temprooml.add(element);
			}
			return new Packet(Packet._ACCEPTED, null, null, temprooml);
		}
		
		/*** Requests those requires manager authority ***/
		if(acctype == 2) return new Packet(Packet._INVALID_ACCESS, null, null, null);
		switch(packet.getFlag())
		{
		case Packet.QUERY_REGS:
			return new Packet(Packet._ACCEPTED, null, null, accountmanager.getRegisterList());
			
		case Packet.ACCEPT_REG:
			result = accountmanager.acceptRegistration((String)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			return new Packet(Packet._ACCEPTED, null, null, accountmanager.getRegisterList());
			
		case Packet.REJECT_REG:
			result = accountmanager.rejectRegistration((String)packet.getData());
			if(result != 0) return new Packet(Packet._REJECTED, null, null, result);
			return new Packet(Packet._ACCEPTED, null, null, accountmanager.getRegisterList());
		}

		return new Packet(Packet._UNKNOWN, null, null, null);
	}
}
