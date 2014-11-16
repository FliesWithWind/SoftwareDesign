package datatype;

import java.io.Serializable;

public class Reservation implements Serializable
{
	private Account	client;
	private Room	room;
	
	private long	date;		//	getTime() / 24 * 60 * 60 * 1000
	private float	rentcost;
	
	
	public Reservation(Account client, Room room, long date, float rentcost)
	{
		setClient(client);
		setRoom(room);
		setDate(date);
		setRentcost(rentcost);
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
}
