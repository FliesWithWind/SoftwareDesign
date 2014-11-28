package control;

import datatype.*;

import java.util.ArrayList;

public class RoomManager
{
	public static final int DATE_AROUND_INTERVAL = 7; // 
	
	private ArrayList<Room> list;
	
	public RoomManager(){
		list = new ArrayList<Room>();
	}
	
	// search by search options contained in Room object, Reservation object
	// found		: returns searched list
	// not found	: returns zero length list
	public ArrayList<Room> primarySearch(Room roominf, Reservation reservationinf)
	{
		ArrayList<Room> targetlist = list;
		
		targetlist = searchByName			(targetlist, roominf.getName());
		targetlist = searchByDate			(targetlist, reservationinf.getDate());
		targetlist = searchByLocation		(targetlist, roominf.getLocation());
		targetlist = searchByMaxcapacity	(targetlist, roominf.getMaxcapacity());
		targetlist = searchByRentcost		(targetlist, reservationinf.getRentcost());
		targetlist = searchByCity			(targetlist, roominf.getCity());
		targetlist = searchByAvailability	(targetlist, reservationinf.getClient() == null);
		
		return targetlist;
	}

	// search by search options contained in Room object, Reservation object
	// search by date performs with around interval (DATE_AROUND_INTERVAL)
	// found		: returns searched list
	// not found	: returns zero length list
	public ArrayList<Room> secondarySearch(Room roominf, Reservation reservationinf)
	{
		ArrayList<Room> targetlist = list;
		ArrayList<Room> templist;
		
		targetlist = searchByName			(targetlist, roominf.getName());
		targetlist = searchByDateAround		(targetlist, reservationinf.getDate());
		targetlist = searchByMaxcapacity	(targetlist, roominf.getMaxcapacity());
		targetlist = searchByRentcost		(targetlist, reservationinf.getRentcost());
		targetlist = searchByCity			(targetlist, roominf.getCity());
		targetlist = searchByAvailability	(targetlist, reservationinf.getClient() == null);
		templist   = searchByLocation		(targetlist, roominf.getLocation());

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
	
	/**
	 * When creating a room, room ID is given server side and it is based on
	 * owner ID and number of rooms  he has. (e.g. someid_room_1)
	 * @param inf
	 * @param owner
	 */
	
	public void createRoom(Room inf,Account owner){
		inf.setId(owner.getId() + "_room_" + owner.getMyrooms().size());
		owner.addRoom(inf);
		list.add(inf);
	}
	
	public ArrayList<Room> getList(){
		return list;
	}
	
	public Room searchRoom(String roomid)
	{
		for(Room iter : list) 					// iterates in account list
			if(iter.getId().equals(roomid)) return iter;
		
		return null; 								// not found
	}
	
	public boolean editRoom(Room inf){
		int i = list.indexOf(inf);
		if(i==-1)
			return false;
		list.get(i).setDefault_rentcost(inf.getDefault_rentcost());
		list.get(i).setLocation(inf.getLocation());
		list.get(i).setName(inf.getName());
		list.get(i).setMaxcapacity(inf.getMaxcapacity());
		return true;
	}
	
	public boolean removeRoom(String roomid){
		Room tmp = searchRoom(roomid);
		if(tmp==null)
			return false;
		list.remove(tmp);
		return true;
	}
}
