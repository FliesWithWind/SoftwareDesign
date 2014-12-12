package control;

import datatype.*;

import java.util.ArrayList;

public class ReservationManager
{
	ArrayList<Reservation> list;
	
	public ReservationManager(ArrayList<Reservation> list)
	{
		this.list = list;
	}

	/*** find room with room id and date, and set client as the account ***/
	// success		: returns 0
	// not found	: returns 1
	// passed date	: returns 2
	// occupied		: returns 3
	public int reserve(String roomid, long date, Account account) throws Exception
	{
		Reservation temp = searchReservation(roomid, date);

		if(temp == null)					return 1; // check null
		if(date < DateTeller.getToday())	return 2; // check if passed date
		if(temp.isReserved())				return 3; // check if reserved
		
		temp.setClient(account);					// set client
		temp.setReserved(true);						// set reserved
		account.getMyreservations().add(temp);		// add to owner's my reservation list
		
		return 0;
	}
	
	public Reservation searchReservation(String roomname, long date)
	{
		for(Reservation iter : list) 				// iterates in reservation list
			if(iter.getRoom().getName().equals(roomname) && iter.getDate() == date) return iter;
		
		return null; 								// not found
	}
	
	/*** turn on reqcancel of the reservation ***/
	// success		: returns 0
	// not found	: returns 1
	// passed date	: returns 2
	public int requestCancelReservation(String roomid, long date)
	{
		Reservation temp = searchReservation(roomid, date);

		if(temp == null) 					return 1;	// check null - not found
		if(date < DateTeller.getToday())	return 2;	// check if passed date
		
		temp.setReqcancel(true);	// set requested for canceled
		return 0;
	}

	/*** create new Reservation object ***/
	// success		: returns 0
	// duplicated	: returns 1
	// passed date	: returns 2
	// invalid form	: returns 3
	// passed date	: returns 4
	public int openReservation(Reservation inf, Room room) throws Exception
	{
		Reservation temp = searchReservation(inf.getRoom().getName(), inf.getDate());

		if(temp != null)							return 1; // check duplication
		if(inf.getDate() < DateTeller.getToday())	return 2; // check if passed date
		if(!validateReservationForm(inf)) 			return 3; // check if valid form
		if(inf.getDate() < DateTeller.getToday())	return 4; // check if passed date

		inf.setReqcancel(false);			// set reqcancel to be false
		inf.setRoom(room);					// set room object
		room.getReservations().add(inf);	// add to room object's reservation list
		list.add(inf);						// add to list
		return 0;
	}

	/*** remove unoccupied reservation ***/
	// success		: returns 0
	// not found	: returns 1
	// passed date	: returns 2
	// occupied		: returns 3
	public int closeReservation(String roomid, long date)
	{
		Reservation temp = searchReservation(roomid, date);

		if(temp == null)					return 1; // check null
		if(date < DateTeller.getToday())	return 2; // check if passed date
		if(temp.isReserved())				return 3; // check if valid form
		
		temp.getRoom().getReservations().remove(temp);	// remove rsrv from the room obj
		list.remove(temp);								// remove rsrv from the list
		temp = null;
		
		return 0;
	}

	/*** remove client from the reservation requested to be canceled ***/
	// success			: returns 0
	// not found		: returns 1
	// passed date		: returns 2
	// not reqcancel	: returns 3
	public int cancelReservation(String roomid, long date)
	{
		Reservation temp = searchReservation(roomid, date);

		if(temp == null)					return 1; // check null
		if(date < DateTeller.getToday())	return 2; // check if passed date
		if(!temp.isReqcancel())				return 3; // check if valid form

		temp.getClient().getMyreservations().remove(temp);	// remove rsrv from the client obj
		temp.setClient(null);								// remove client from the rsrv obj
		temp.setReserved(false);							// set unreserved
		temp.setReqcancel(false);							// recover reqcancel to be false
		return 0;
	}
		
	/*** check if it's valid form of Reservation information ***/
	// valid	: true
	// invalid	: false
	private boolean validateReservationForm(Reservation inf)
	{
		if(inf.isReqcancel())	return false;
		if(inf.isReserved())	return false;
		
		return true;
	}
}
