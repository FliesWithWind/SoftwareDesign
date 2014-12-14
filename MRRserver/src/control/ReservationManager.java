package control;

import datatype.*;

public class ReservationManager
{
	/*** find room with room id and date, and set client as the account ***/
	// success		: returns 0
	// not found	: returns 1
	// passed date	: returns 2
	// occupied		: returns 3
	public int reserve(Room room, long date, Account account) throws Exception
	{
		Reservation temp = searchReservation(room, date);

		if(temp == null)					return 1; // check null
		if(date < DateTeller.getToday())	return 2; // check if passed date
		if(temp.isReserved())				return 3; // check if reserved
		
		temp.setClient(account);					// set client
		temp.setReserved(true);						// set reserved
		account.getMyreservations().add(temp);		// add to owner's my reservation list
		
		return 0;
	}
	
	public Reservation searchReservation(Room room, long date)
	{
		for(Reservation iter : room.getReservations())	// iterates in reservation list
			if(iter.getDate() == date) return iter;
		
		return null; 									// not found
	}
	
	/*** turn on reqcancel of the reservation ***/
	// success		: returns 0
	// not found	: returns 1
	// passed date	: returns 2
	public int requestCancelReservation(Reservation rsrv)
	{
		if(rsrv == null) 							return 1;	// check null - not found
		if(rsrv.getDate() < DateTeller.getToday())	return 2;	// check if passed date
		
		rsrv.setReqcancel(true);	// set requested for canceled
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
		Reservation temp = searchReservation(inf.getRoom(), inf.getDate());

		if(temp != null)							return 1; // check duplication
		if(inf.getDate() < DateTeller.getToday())	return 2; // check if passed date
		if(!validateReservationForm(inf)) 			return 3; // check if valid form
		if(inf.getDate() < DateTeller.getToday())	return 4; // check if passed date

		inf.setReqcancel(false);			// set reqcancel to be false
		inf.setRoom(room);					// set room object
		room.getReservations().add(inf);	// add to room object's reservation list
		return 0;
	}

	/*** remove unoccupied reservation ***/
	// success		: returns 0
	// not found	: returns 1
	// passed date	: returns 2
	// occupied		: returns 3
	public int closeReservation(Reservation rsrv)
	{
		if(rsrv == null)							return 1; // check null
		if(rsrv.getDate() < DateTeller.getToday())	return 2; // check if passed date
		if(rsrv.isReserved())						return 3; // check if valid form
		
		rsrv.getRoom().getReservations().remove(rsrv);	// remove rsrv from the room obj
		rsrv = null;
		
		return 0;
	}

	/*** remove client from the reservation requested to be canceled ***/
	// success			: returns 0
	// not found		: returns 1
	// passed date		: returns 2
	// not reqcancel	: returns 3
	public int cancelReservation(Reservation rsrv)
	{
		if(rsrv == null)							return 1; // check null
		if(rsrv.getDate() < DateTeller.getToday())	return 2; // check if passed date
		if(!rsrv.isReqcancel())						return 3; // check if valid form

		rsrv.getClient().getMyreservations().remove(rsrv);	// remove rsrv from the client obj
		rsrv.setClient(null);								// remove client from the rsrv obj
		rsrv.setReserved(false);							// set unreserved
		rsrv.setReqcancel(false);							// recover reqcancel to be false
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
