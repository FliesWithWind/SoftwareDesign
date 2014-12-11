package control;

import datatype.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ReservationManager
{
	ArrayList<Reservation> list;
	
	public ReservationManager(ArrayList<Reservation> list)
	{
		this.list = list;
	}

	public int reserve(Room room, long date, Account account) throws Exception
	{
		for(Reservation iter : room.getReservations())
			if(iter.getDate() == date) 
				iter.setClient(account);
		account.getMyreservations().add(iter);
		
		return 2;
	}
	
	public Reservation searchReservation(String roomid, long date)
	{
		for(Reservation iter : list) 				// iterates in account list
			if(iter.getRoom().getId().equals(roomid) && iter.getDate()==date) return iter;
		
		return null; 								// not found
	}
	
	
	public int requestCancelReservaation(String roomid, long date)
	{
		Reservation tmp = searchReservation(roomid, date);
		
		if(tmp != null)
		{
			tmp.setReqcancel(true);
			return 0;
		} else return 1;
	}
	
	public int openReservation(Reservation inf) throws Exception
	{
		Reservation tmp = searchReservation(inf.getRoom().getId(),inf.getDate());
		if(tmp != null)
		{
			list.add(inf);
			return true;
		} else
			return false;
	}
	
	public int closeReservation(String roomid, long date)
	{
		
	}
	
	public int cancelReservation(String roomid, long date)
	{
		Reservation tmp = searchReservation(roomid, date);
		
		if(tmp != null)
		{
			if(tmp.isReqcancel())
			{
				list.remove(tmp);
				return 0;
			}
			return 2;
		} else return 2;
	}
	
	/*** check if it's valid form of Account information ***/
	// valid	: true
	// invalid	: false
	private boolean validateReservationForm(Reservation inf) throws Exception
	{
		
	}
	
	public ArrayList<Reservation> getList(){
		return list;
	}
}
