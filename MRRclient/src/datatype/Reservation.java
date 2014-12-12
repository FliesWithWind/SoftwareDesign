package datatype;

import java.io.Serializable;

public class Reservation implements Serializable
{
	private Account	client;
	private Room	room;
	
	private long	date;		//	getTime() / 24 * 60 * 60 * 1000
	private float	rentcost;
	private boolean	reqcancel;	// Is requested by the client to be canceled?
	
	
	public Reservation(Account client, Room room, long date, float rentcost, boolean reqcancel)
	{
		setClient(client);
		setRoom(room);
		setDate(date);
		setRentcost(rentcost);
		setReqcancel(reqcancel);
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
}
