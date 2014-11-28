package control;

import datatype.*;

import java.util.ArrayList;

public class ReservationManager
{
	ArrayList<Reservation> list;
	
	public ReservationManager(){
		list = new ArrayList<Reservation>();
	}
	
	public Reservation searchReservation(String roomid,long date)
	{
		for(Reservation iter : list) 					// iterates in account list
			if(iter.getRoom().getId().equals(roomid) && iter.getDate()==date) return iter;
		
		return null; 								// not found
	}
	
	public boolean resevre(Room room, long date, Account account){
		list.add(new Reservation(account,room,date,room.getDefault_rentcost(),false));
		return true;
	}
	
	public boolean requestCancelReservaation(String roomid,long date){
		Reservation tmp = searchReservation(roomid,date);
		if(tmp!=null){
			tmp.setReqcancel(true);
			return true;
		} else
			return false;
	}
	
	public boolean openReservation(Reservation inf){
		Reservation tmp = searchReservation(inf.getRoom().getId(),inf.getDate());
		if(tmp!=null){
			list.add(inf);
			return true;
		} else
			return false;
	}
	
	/*public boolean closeReservation(String roomid, long date){
		
	}*/
	
	public boolean cancelReservation(String roomid, long date){
		Reservation tmp = searchReservation(roomid,date);
		if(tmp!=null){
			list.remove(tmp);
			return true;
		} else
			return false;
	}
	
	public ArrayList<Reservation> getList(){
		return list;
	}
}
