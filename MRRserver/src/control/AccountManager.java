package control;

import java.util.ArrayList;
import datatype.*;


public class AccountManager
{
	private ArrayList<Account> list;
	private	ArrayList<Account> registerlist;
	

	/*** search account with id ***/
	// found		: returns account itself
	// not found	: returns null
	public Account searchAccount(String id) throws Exception
	{
		for(Account iter : list) 					// iterates in account list
			if(iter.getId().equals(id)) return iter;
		
		return null; 								// not found
	}
	
	/*** validates id and pw ***/
	// invalid, not found	: returns 0
	// valid				: returns User - 1, Staff - 2, Manager - 3
	public int validate(String id, String pw) throws Exception
	{
		Account client = searchAccount(id);
		
		if(client == null) return 0;						// not found
		if(client.getPw() == pw) return client.getType();	// valid
		return 0;											// invalid
	}
	
	/*** add Registration record to register list ***/
	// accepted										: returns 0
	// rejected by an invalid property form			: returns 1
	// rejected by duplicated id in account list	: returns 2
	// rejected by duplicated id in register list	: returns 3
	public int addRegistration(Account inf) throws Exception
	{
		inf.setId(inf.getId().toLowerCase());				// To Lower character string
		inf.setMyrooms(new ArrayList<Room>());				// Clear room list
		inf.setMyreservations(new ArrayList<Reservation>());// Clear reservation list

		/************************/
		/*** Now working here ***/
		/************************/
		
		if(searchAccount(inf.getId()) != null) return 2;	// check duplicated id in account list
		for(Account e : registerlist)						// check duplicated id in register list
			if(e.getId().equals(inf.getId())) return 3;
		
		return 0;
	}
}
