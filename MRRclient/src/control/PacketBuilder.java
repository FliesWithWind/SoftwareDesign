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
	public void login(String id, String pw)
	{
		this.id = id;
		this.pw = pw;
		
		try
		{
			network.send(new Packet(Packet.LOGIN, this.id, this.pw, null));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// View my account properties
	public void myAccount(String id)
	{
		try
		{
			network.send(new Packet(Packet.MY_ACNT, this.id, this.pw, id));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Request to register
	public void register(Account inf)
	{
		try
		{
			network.send(new Packet(Packet.REGISTER, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Edit my account properties
	public void editAccount(Account inf)
	{
		try
		{
			network.send(new Packet(Packet.EDIT_ACNT, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// View my room list
	public void myRooms(String id)
	{
		try
		{
			network.send(new Packet(Packet.MY_ROOMS, this.id, this.pw, id));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Create new room
	// The ID the room's owner must be contained in inf.owner.id
	public void createRoom(Room inf)
	{
		try
		{
			network.send(new Packet(Packet.CREATE_ROOM, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Edit room properties
	public void editRoom(Room inf)
	{
		try
		{
			network.send(new Packet(Packet.EDIT_ROOM, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Remove room
	public void removeRoom(String name)
	{
		try
		{
			network.send(new Packet(Packet.REMOVE_ROOM, this.id, this.pw, name));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// View reservation list I reserved
	public void myReservations(String id)
	{
		try
		{
			network.send(new Packet(Packet.MY_RSRVS, this.id, this.pw, id));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Reserve a room at a certain date
	public void reserve(String id, String name, long date)
	{
		String[] inf = {id, name, Long.toString(date)}; // date to be parsed in server
		
		try
		{
			network.send(new Packet(Packet.RESERVE, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Request to cancel a certain reservation
	public void requestCancelReservation(String name, long date)
	{
		String[] inf = {name, Long.toString(date)}; // date to be parsed in server
		
		try
		{
			network.send(new Packet(Packet.REQ_CANCEL_RSRV, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Open a room in a certain date to be leased
	// The name the room must be contained in inf.room.name
	public void openReservation(Reservation inf)
	{
		try
		{
			network.send(new Packet(Packet.OPEN_RSRV, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Close a room not to be leased
	public void closeReservation(String name, long date)
	{
		String[] inf = {name, Long.toString(date)}; // date to be parsed in server
		
		try
		{
			network.send(new Packet(Packet.CLOSE_RSRV, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Cancel a reservation requested to be canceled
	public void cancelReservation(String name, long date)
	{
		String[] inf = {name, Long.toString(date)}; // date to be parsed in server
		
		try
		{
			network.send(new Packet(Packet.CANCEL_RSRV, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Query for requests for registration
	public void queryRegistrations()
	{
		try
		{
			network.send(new Packet(Packet.QUERY_REGS, this.id, this.pw, null));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Accept registration
	public void acceptRegistration(String id)
	{
		try
		{
			network.send(new Packet(Packet.ACCEPT_REG, this.id, this.pw, id));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Reject registration
	public void rejectRegistration(String id)
	{
		try
		{
			network.send(new Packet(Packet.REJECT_REG, this.id, this.pw, id));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Search rooms
	// Information on room's properties must be contained in inf.room
	public void searchRooms(Reservation inf)
	{
		try
		{
			network.send(new Packet(Packet.SEARCH_ROOMS, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}

	// Query reservations on a certain room
	public void searchRooms(String name, long date)
	{
		String[] inf = {name, Long.toString(date)}; // date to be parsed in server
		
		try
		{
			network.send(new Packet(Packet.QUERY_RSRVS, this.id, this.pw, inf));
		}
		catch(IOException e)
		{
			// ***to be implemented*** calls UI's dialogue()
			// ***to be implemented*** calls UI's unblock()
		}
	}
	
}
