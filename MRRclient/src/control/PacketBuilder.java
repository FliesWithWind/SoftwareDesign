package control;

import ui.*;

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
	
	public static final String CURRENT_VERSION = "0.5";
	
	// private UIs mainframe; ***to be implemented*** has UI object
	private Network network;
        
	private String id;
	private String pw;
	
	private PacketBuilder()
	{
		// uis = uis; ***to be implemented*** initializes UI object
		network = Network.getInstance();
	}
	
    public String getLoginId()
    {
        return id;
    }
    
    private void handleConnectionError(int flag)
    {
        switch(flag)
        {
        case Packet.LOGIN:
            LoginFrame.getInstance().setEnabled(true);
            LoginFrame.getInstance().showDialog(STR.NOTI_CONNECTION_ERROR);
            break;
            
        case Packet.REGISTER:
            RegisterFrame.getInstance().setEnabled(true);
            RegisterFrame.getInstance().showDialog(STR.NOTI_CONNECTION_ERROR);
            break;
            
        default:
            MainFrame.getInstance().unfreeze();
            MainFrame.getInstance().showDialog(STR.NOTI_CONNECTION_ERROR);
        }
    }
    
	// Request to validate ID-PW
	public void login(String id, String pw)
	{
		this.id = id;
		this.pw = pw;
		
		try
		{
			network.send(new Packet(Packet.LOGIN, this.id, this.pw, CURRENT_VERSION));
		}
		catch(IOException e)
		{handleConnectionError(Packet.LOGIN);}
	}

	// View my account properties
	public void myAccount(String id)
	{
		try
		{
			network.send(new Packet(Packet.MY_ACNT, this.id, this.pw, id));
		}
		catch(IOException e)
		{handleConnectionError(Packet.MY_ACNT);}
	}

	// Request to register
	public void register(Account inf)
	{
		try
		{
			network.send(new Packet(Packet.REGISTER, null, null, inf));
		}
		catch(IOException e)
		{handleConnectionError(Packet.REGISTER);}
	}

	// Edit my account properties
	public void editAccount(Account inf)
	{
		try
		{
			network.send(new Packet(Packet.EDIT_ACNT, this.id, this.pw, inf));
		}
		catch(IOException e)
		{handleConnectionError(Packet.EDIT_ACNT);}
	}

	// View my room list
	public void myRooms(String id)
	{
		try
		{
			network.send(new Packet(Packet.MY_ROOMS, this.id, this.pw, id));
		}
		catch(IOException e)
		{handleConnectionError(Packet.MY_ROOMS);}
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
		{handleConnectionError(Packet.CREATE_ROOM);}
	}

	// Edit room properties
	public void editRoom(Room inf)
	{
		try
		{
			network.send(new Packet(Packet.EDIT_ROOM, this.id, this.pw, inf));
		}
		catch(IOException e)
		{handleConnectionError(Packet.EDIT_ROOM);}
	}

	// Remove room
	public void removeRoom(String name)
	{
		try
		{
			network.send(new Packet(Packet.REMOVE_ROOM, this.id, this.pw, name));
		}
		catch(IOException e)
		{handleConnectionError(Packet.REMOVE_ROOM);}
	}

	// View reservation list I reserved
	public void myReservations(String id)
	{
		try
		{
			network.send(new Packet(Packet.MY_RSRVS, this.id, this.pw, id));
		}
		catch(IOException e)
		{handleConnectionError(Packet.MY_RSRVS);}
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
		{handleConnectionError(Packet.RESERVE);}
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
		{handleConnectionError(Packet.REQ_CANCEL_RSRV);}
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
		{handleConnectionError(Packet.OPEN_RSRV);}
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
		{handleConnectionError(Packet.CLOSE_RSRV);}
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
		{handleConnectionError(Packet.CANCEL_RSRV);}
	}

	// Query for requests for registration
	public void queryRegistrations()
	{
		try
		{
			network.send(new Packet(Packet.QUERY_REGS, this.id, this.pw, null));
		}
		catch(IOException e)
		{handleConnectionError(Packet.QUERY_REGS);}
	}

	// Accept registration
	public void acceptRegistration(String id)
	{
		try
		{
			network.send(new Packet(Packet.ACCEPT_REG, this.id, this.pw, id));
		}
		catch(IOException e)
		{handleConnectionError(Packet.ACCEPT_REG);}
	}

	// Reject registration
	public void rejectRegistration(String id)
	{
		try
		{
			network.send(new Packet(Packet.REJECT_REG, this.id, this.pw, id));
		}
		catch(IOException e)
		{handleConnectionError(Packet.REJECT_REG);}
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
		{handleConnectionError(Packet.SEARCH_ROOMS);}
	}

	// Query reservations on a certain room
	public void queryReservations(String name, long date)
	{
		String[] inf = {name, Long.toString(date)}; // date to be parsed in server
		
		try
		{
			network.send(new Packet(Packet.QUERY_RSRVS, this.id, this.pw, inf));
		}
		catch(IOException e)
		{handleConnectionError(Packet.QUERY_RSRVS);}
	}
	
}
