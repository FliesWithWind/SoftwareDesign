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

	public int reserve(Room roomid, long date, Account account) throws Exception
	{
		list.add(new Reservation(account,room.date,room.getDefault_rentcost(),false));
		
		return true;
	}
	
	public Reservation searchReservation(String roomid, long date)
	{
		for(Reservation iter : list) 				// iterates in account list
			if(iter.getRoom().getId().equals(roomid) && iter.getDate()==date) return iter;
		
		return null; 								// not found
	}
	
	
	public boolean requestCancelReservaation(String roomid,long date)
	{
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
	
	/*** check if it's valid form of Account information ***/
	// valid	: true
	// invalid	: false
	private boolean validateReservationForm(Reservation inf) throws Exception
	{
		if(inf.get)
		Pattern p = Pattern.compile("[^a-zA-Z0-9]");
		if(p.matcher(inf.getId()).find()) return false;			// check if ID's alphanumeric
		if(p.matcher(inf.getPw()).find()) return false;			// check if PW's alphanumeric
		
		int type = inf.getType();								// check type's range
		if(type > 3 || type < 1) return false;
		
		String nametemp = inf.getName();						// check if name's just blank
		nametemp = nametemp.replace(" ", "");
		if(nametemp.equals("")) return false;
		
		if(	!inf.getEmail().contains("@") ||					// check if email contains @, .
			!inf.getEmail().contains(".")) return false;
		
		p = Pattern.compile("^[[0-9]+[-]]");					// check if Phonenum's numeric
		if(p.matcher(inf.getPhonenum()).find()) return false;

		String univ_comptemp = inf.getUniv_comp();				// check if univ_comp's just blank
		univ_comptemp.replace(" ", "");
		if(univ_comptemp.equals("")) return false;
		
		return true;
	}
	
	public ArrayList<Reservation> getList(){
		return list;
	}
}
