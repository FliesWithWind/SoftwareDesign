package control;

import java.util.ArrayList;

import datatype.*;


public class AccountManager
{
	private ArrayList<Account> list;
	private	ArrayList<Account> registerlist;
	
	public AccountManager(){
		this.list = new ArrayList<Account>();
		this.registerlist = new ArrayList<Account>();
	}

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
	/*
	// add Registration record to registerlist
	// accepted						: returns 0
	// rejected by duaccplicated id	: returns 1
	public int addRegistration(Account inf)		roommanager
=======
	/*** add Registration record to register list ***/
	// accepted										: returns 0
	// rejected by an invalid property form			: returns 1
	// rejected by duplicated id in account list	: returns 2
	// rejected by duplicated id in register list	: returns 3
	/*public int addRegistration(Account inf) throws Exception
	{
		for(Account eacc : registerlist)	// check duplicated id
			if(e.getId().equals(inf.getId())) return 1;
		Account client = searchAccount(id);
		inf.setId(inf.getId().toLowerCase());				// To Lower character string
		inf.setMyrooms(new ArrayList<Room>());				// Clear room list
		inf.setMyreservations(new ArrayList<Reservation>());// Clear reservation list
		if(client == null) return 0;						// not found
		if(client.getPw() == pw) return client.getType();	// valid
		return 0;											// invalid
	}*/
	
	public boolean addRegistration(Account inf) throws Exception{
		for(Account eacc : registerlist)	// check duplicated id
			if(eacc.getId().equals(inf.getId())) return false;
		if(searchAccount(inf.getId())!=null)
			return false;
		registerlist.add(inf);
		return true;
	}
	
	public ArrayList<Account> getRegisterList(){
		return registerlist;
	}
	
	public ArrayList<Account> getAccountList(){
		return list;
	}
	
	public void setRegisterList(ArrayList<Account> input){
		this.registerlist = input;
	}
	
	public void setAccountList(ArrayList<Account> input){
		this.list = input;
	}
	
	public boolean deleteRegistration(Account acc){
		if(registerlist.contains(acc)){
			registerlist.remove(acc);
			return true;
		}
		else
			return false;
	}
	
	public boolean deleteAccount(Account acc){
		if(registerlist.contains(acc)){
			list.remove(acc);
			return true;
		}
		else
			return false;
	}
	
	public Account searchRegistration(String id)
	{
		for(Account iter : registerlist) 					// iterates in account list
			if(iter.getId().equals(id)) return iter;
		
		return null; 								// not found
	}
	
	public boolean acceptRegistration(String id){
		Account tmp = searchRegistration(id);
		if(tmp==null)
			return false;
		list.add(tmp);
		registerlist.remove(tmp);
		return true;
	}
}
