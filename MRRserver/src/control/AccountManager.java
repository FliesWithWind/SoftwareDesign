package control;

import java.util.ArrayList;
import datatype.*;


public class AccountManager
{
	private ArrayList<Account> list;
	private	ArrayList<Account> registerlist;
	

	// search account with id
	// found		: returns account itself
	// not found	: return null
	public Account searchAccount(String id)
	{
		for(Account iter : list) 					// iterates in account list
			if(iter.getId().equals(id)) return iter;
		
		return null; 								// not found
	}
	
	// validates id and pw
	// invalid, not found	: returns 0
	// valid				: returns User - 1, Staff - 2, Manager - 3
	public int validate(String id, String pw)
	{
		Account client = searchAccount(id);
		
		if(client == null) return 0;						// not found
		if(client.getPw() == pw) return client.getType();	// valid
		return 0;											// invalid
	}
	
	/*
	// add Registration record to registerlist
	// accepted						: returns 0
	// rejected by duplicated id	: returns 1
	public int addRegistration(Account inf)
	{
		for(Account e : registerlist)	// check duplicated id
			if(e.getId().equals(inf.getId())) return 1;
		Account client = searchAccount(id);
		
		if(client == null) return 0;						// not found
		if(client.getPw() == pw) return client.getType();	// valid
		return 0;											// invalid
	}*/
}
