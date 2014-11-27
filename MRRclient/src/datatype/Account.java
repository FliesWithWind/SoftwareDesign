package datatype;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable
{
	public static final transient int UNSIGNED 	= 0;
	public static final transient int USER	 	= 1;
	public static final transient int STAFF		= 2;
	public static final transient int MANAGER 	= 3;
	
	private ArrayList<Reservation>	myreservations;
	private ArrayList<Room>			myrooms;
	
	private String	id;
	private String	pw;
	private int		type;		// unsigned - 0, User - 1, Staff - 2, Manager - 3
	private String	name;
	private String	email;
	private String	phonenum;
	private String	univ_comp;
	
	
	public Account(	String id, String pw, int type,
					String name, String email, String phonenum, String univ_comp)
	{
		setId(id);
		setPw(pw);
		setType(type);
		setName(name);
		setEmail(email);
		setPhonenum(phonenum);
		setUniv_comp(univ_comp);
		myrooms = new ArrayList<Room>();
	}
	
	public ArrayList<Reservation> getMyreservations()
	{
		return myreservations;
	}
	
	public void setMyreservations(ArrayList<Reservation> myreservations)
	{
		this.myreservations = myreservations;
	}
	
	public ArrayList<Room> getMyrooms()
	{
		return myrooms;
	}
	
	public void setMyrooms(ArrayList<Room> myrooms)
	{
		this.myrooms = myrooms;
	}
	
	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getPw()
	{
		return pw;
	}

	public void setPw(String pw)
	{
		this.pw = pw;
	}
	
	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getPhonenum()
	{
		return phonenum;
	}
	
	public void setPhonenum(String phonenum)
	{
		this.phonenum = phonenum;
	}
	
	public String getUniv_comp()
	{
		return univ_comp;
	}
	
	public void setUniv_comp(String univ_comp)
	{
		this.univ_comp = univ_comp;
	}
	
	public void addRoom(Room newroom){
		myrooms.add(newroom);
	}
}
