package control;

import datatype.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RoomManager
{
	public static final int DATE_AROUND_INTERVAL = 7; 			// 
	public static final int ROOM_REMOVAL_FROM_LAST_RSRV = 30;	// 
	
	private ArrayList<Room> list;
	
	public RoomManager(ArrayList<Room> list)
	{
		this.list = list;
	}

	/*** create room and locate it to the owner ***/
	// success		: returns 0
	// duplicated	: returns 1
	// invalid form	: returns 2
	public int createRoom(Room inf, Account owner) throws Exception
	{
		Room tmp = searchRoom(inf.getName());
		
		if(tmp != null)				return 1; // check duplication
		if(!validateRoomForm(inf))	return 2; // check if valid form
		
		inf.setOwner(owner);			// set room's owner
		inf.initReservations();			// empty reservation list
		owner.getMyrooms().add(inf);	// add to owner's room list
		list.add(inf);					// add to list
		return 0;
	}
	
	public Room searchRoom(String name)
	{
		for(Room iter : list) 						// iterates in room list
			if(iter.getName().equals(name)) return iter;
		
		return null; 								// not found
	}

	/*** edit room with received information ***/
	// success		: returns 0
	// not found	: returns 1
	// invalid form	: returns 2
	// editing room name is not allowed
	public int editRoom(Room inf) throws Exception
	{
		Room tmp = searchRoom(inf.getName());
		
		if(tmp == null)				return 1; // check null
		if(!validateRoomForm(inf))	return 2; // check if valid form

		tmp.setCity(inf.getCity());
		tmp.setLocation(inf.getLocation());
		tmp.setDefault_rentcost(inf.getDefault_rentcost());
		tmp.setMaxcapacity(inf.getMaxcapacity());
		
		return 0;
	}

	/*** remove room ***/
	// success		: returns 0
	// not found	: returns 1
	// rejected		: returns 2
	public int removeRoom(String roomid) throws Exception
	{
		Room tmp = searchRoom(roomid);
		
		if(tmp == null)							return 1; // check null
		for(Reservation iter : tmp.getReservations())
			if(iter.getDate() >= DateTeller.getToday() + ROOM_REMOVAL_FROM_LAST_RSRV)
				if(iter.getClient() != null)	return 2; // check if reserved
		
		list.remove(tmp);
		return 0;
	}

	/*** check if it's valid form of Room information ***/
	// valid	: true
	// invalid	: false
	private boolean validateRoomForm(Room room) throws Exception
	{   
		Pattern p = Pattern.compile("[^a-zA-Z0-9]");
		if(p.matcher(room.getName()).find())		return false; // check if room name is alphanumeric

		String nametemp = room.getName();
		nametemp = nametemp.replace(" ", "");
		if(nametemp.equals(""))						return false; // check if name's just blank

		p = Pattern.compile("^[[a-zA-Z0-9] + [-]]");
		if(p.matcher(room.getLocation()).find())	return false; // location string valid check

		String locationtemp = room.getLocation();
		locationtemp = locationtemp.replace(" ", "");
		if(locationtemp.equals(""))					return false; // check if location's just blank

		return true;
	}
	
	/*** search by search options contained in Room object, Reservation object ***/
	// found		: returns searched list
	// not found	: returns zero length list
	public ArrayList<Room> primarySearch(Reservation reservationinf) throws Exception
	{
		ArrayList<Room> targetlist = list;
		
		targetlist = searchByName			(targetlist, reservationinf.getRoom().getName());
		targetlist = searchByDate			(targetlist, reservationinf.getDate());
		targetlist = searchByLocation		(targetlist, reservationinf.getRoom().getLocation());
		targetlist = searchByMaxcapacity	(targetlist, reservationinf.getRoom().getMaxcapacity());
		targetlist = searchByRentcost		(targetlist, reservationinf.getRentcost());
		targetlist = searchByCity			(targetlist, reservationinf.getRoom().getCity());
		targetlist = searchByAvailability	(targetlist, reservationinf.getClient() == null);
		
		return targetlist;
	}

	// search by search options contained in Room object, Reservation object
	// search by date performs with around interval (DATE_AROUND_INTERVAL)
	// found		: returns searched list
	// not found	: returns zero length list
	public ArrayList<Room> secondarySearch(Reservation reservationinf)
	{
		ArrayList<Room> targetlist = list;
		ArrayList<Room> templist;
		
		targetlist = searchByName			(targetlist, reservationinf.getRoom().getName());
		targetlist = searchByDateAround		(targetlist, reservationinf.getDate());
		targetlist = searchByMaxcapacity	(targetlist, reservationinf.getRoom().getMaxcapacity());
		targetlist = searchByRentcost		(targetlist, reservationinf.getRentcost());
		targetlist = searchByCity			(targetlist, reservationinf.getRoom().getCity());
		targetlist = searchByAvailability	(targetlist, reservationinf.getClient() == null);
		templist   = searchByLocation		(targetlist, reservationinf.getRoom().getLocation());

		// if there's no result that searched by location,
		// returns list not searched by location
		if(templist == null)	return targetlist;
		else					return templist;
	}
	
	private ArrayList<Room> searchByDate(ArrayList<Room> targetlist, long date)
	{
		if(date == -1) return targetlist;
		
		ArrayList<Room> result = new ArrayList<Room>();
		
		for(Room room : targetlist)
			for(Reservation reservation : room.getReservations())
				if(reservation.getDate() == date)
				{
					result.add(room);
					break;
				}
		
		return result;
	}
	
	private ArrayList<Room> searchByDateAround(ArrayList<Room> targetlist, long date)
	{
		if(date == -1) return targetlist;
		
		ArrayList<Room> result = new ArrayList<Room>();
		
		for(Room room : targetlist)
			for(Reservation reservation : room.getReservations())
				if(	reservation.getDate() <= date + DATE_AROUND_INTERVAL &&
					reservation.getDate() >= date - DATE_AROUND_INTERVAL	) 
					{
						result.add(room);
						break;
					}
		
		return result;
	}

	private ArrayList<Room> searchByCity(ArrayList<Room> targetlist, int city)
	{
		if(city == -1) return targetlist;
		
		ArrayList<Room> result = new ArrayList<Room>();
		
		for(Room room : targetlist)
			if(room.getCity() == city) result.add(room);
		
		return result;
	}

	private ArrayList<Room> searchByLocation(ArrayList<Room> targetlist, String location)
	{
		if(location == "") return targetlist;
		
		ArrayList<Room> result = new ArrayList<Room>();
		
		for(Room room : targetlist)
			if(room.getLocation().contains(location)) result.add(room);
		
		return result;
	}

	private ArrayList<Room> searchByName(ArrayList<Room> targetlist, String name)
	{
		if(name == "") return targetlist;
		
		ArrayList<Room> result = new ArrayList<Room>();
		
		for(Room room : targetlist)
			if(room.getName().contains(name)) result.add(room);
		
		return result;
	}

	private ArrayList<Room> searchByAvailability(ArrayList<Room> targetlist, boolean availability)
	{
		ArrayList<Room> result = new ArrayList<Room>();
		
		for(Room room : targetlist)
			for(Reservation reservation : room.getReservations())
				if((reservation.getClient() != null) == availability)
				{
					result.add(room);
					break;
				}
		
		return result;
	}

	private ArrayList<Room> searchByMaxcapacity(ArrayList<Room> targetlist, int maxcapacity)
	{
		if(maxcapacity == -1) return targetlist;
		
		ArrayList<Room> result = new ArrayList<Room>();

		for(Room room : targetlist)
			if(room.getMaxcapacity() >= maxcapacity) result.add(room);
		
		return result;
	}

	private ArrayList<Room> searchByRentcost(ArrayList<Room> targetlist, float rentcost)
	{
		if(rentcost == -1) return targetlist;
		
		ArrayList<Room> result = new ArrayList<Room>();

		for(Room room : targetlist)
			for(Reservation reservation : room.getReservations())
				if(reservation.getRentcost() <= rentcost)
				{
					result.add(room);
					break;
				}
		
		return result;
	}
}
