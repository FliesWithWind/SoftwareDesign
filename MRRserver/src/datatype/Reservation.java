package datatype;

import java.io.Serializable;
import java.util.Calendar;

public class Reservation implements Serializable, Cloneable
{
	public Object clone() throws CloneNotSupportedException
	{ return super.clone(); }
	
	private Account	client;
	private Room	room;
	
	private long	date;		// getTimeInMillis() / 100 * 60 * 60 * 24;
	private float	rentcost;
	private boolean reserved;	// is reserved by a client
	private boolean	reqcancel;	// is requested by the client to be canceled?
	
	
	public Reservation(	Account client, Room room, long date, float rentcost,
						boolean reqcancel, boolean reserved)
	{
		setClient(client);
		setRoom(room);
		setDate(date);
		setRentcost(rentcost);
		setReqcancel(reqcancel);
		setReserved(reserved);
	}
	
	public Account getClient()
	{
		return client;
	}

	public void setClient(Account client)
	{
		this.client = client;
	}
	
	public Room getRoom()
	{
		return room;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}
	
	public long getDate()
	{
		return date;
	}

	public void setDate(long date)
	{
		this.date = date;
	}
	
	public float getRentcost()
	{
		return rentcost;
	}

	public void setRentcost(float rentcost)
	{
		this.rentcost = rentcost;
	}
	
	public boolean isReqcancel()
	{
		return reqcancel;
	}

	public void setReqcancel(boolean reqcancel)
	{
		this.reqcancel = reqcancel;
	}
	
	public boolean isReserved()
	{
		return reserved;
	}

	public void setReserved(boolean reserved)
	{
		this.reserved = reserved;
	}
}
