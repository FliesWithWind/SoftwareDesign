package datatype;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable
{
	public static final transient int SEOUL 	= 0;
	public static final transient int DAEJEON 	= 1;
	public static final transient int DAEGU 	= 2;
	public static final transient int JEONJU 	= 3;
	
	private Account					owner;
	private ArrayList<Reservation>	reservations;

	private String	id;
	private String	name;
	private int		city; // Seoul - 0, Daejeon - 1, Daegu - 2, Jeonju - 3
	private String	location;
	private int		maxcapacity;
	private float	default_rentcost;

	//Second constructor without specifying reservations ArrayList, as we just need to create an empty one 
	public Room(Account owner,
			String name, int city, String location, 
			int maxcapacity, float default_rentcost, String id)
	{	
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		setOwner(owner);
		setName(name);
		setCity(city);
		setLocation(location);
		setMaxcapacity(maxcapacity);
		setDefault_rentcost(default_rentcost);
		setId(id);
	}

	public Room(Account owner, ArrayList<Reservation> reservations,
				String name, int city, String location, 
				int maxcapacity, float default_rentcost, String id)
	{
		setOwner(owner);
		setReservations(reservations);
		setId(id);
		setName(name);
		setCity(city);
		setLocation(location);
		setMaxcapacity(maxcapacity);
		setDefault_rentcost(default_rentcost);
	}
	
	public Account getOwner()
	{
		return owner;
	}
	
	public void setOwner(Account owner)
	{
		this.owner = owner;
	}
	
	public ArrayList<Reservation> getReservations()
	{
		return reservations;
	}
	
	public void setReservations(ArrayList<Reservation> reservations)
	{
		this.reservations = reservations;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getCity()
	{
		return city;
	}
	
	public void setCity(int city)
	{
		this.city = city;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public int getMaxcapacity()
	{
		return maxcapacity;
	}
	
	public void setMaxcapacity(int maxcapacity)
	{
		this.maxcapacity = maxcapacity;
	}
	
	public float getDefault_rentcost()
	{
		return default_rentcost;
	}
	
	public void setDefault_rentcost(float default_rentcost)
	{
		this.default_rentcost = default_rentcost;
	}
}
