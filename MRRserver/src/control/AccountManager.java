package control;

import java.util.ArrayList;

import datatype.*;


public class AccountManager
{
	private ArrayList<Account> list;
	private	ArrayList<Account> registerlist;
	
	
	//creating empty lists
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
	
	/**
	 * Add new registration entry. If there exist same ID it returns false.
	 * @param inf
	 * @return
	 * @throws Exception
	 */
	public boolean addRegistration(Account inf) throws Exception{
		for(Account eacc : registerlist)
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
	
	/**
	 * Deleting registration. If not found, returns false.
	 * @param inf
	 * @return
	 */
	public boolean deleteRegistration(Account inf){
		if(registerlist.contains(inf)){
			registerlist.remove(inf);
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Deleting account. If not found returns false.
	 * @param inf
	 * @return
	 */
	public boolean deleteAccount(Account inf){
		if(registerlist.contains(inf)){
			list.remove(inf);
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
	
	/**
	 * Accepting registration.
	 * @param id
	 * @return
	 */
	public boolean acceptRegistration(String id){
		Account tmp = searchRegistration(id);
		if(tmp==null)
			return false;
		list.add(tmp);
		registerlist.remove(tmp);
		return true;
	}
	
	public boolean rejectRegistration(String id){
		Account tmp = searchRegistration(id);
		if(tmp==null)
			return false;
		registerlist.remove(tmp);
		return true;
	}
	
	/**
	 * Editing account. Everything can be changed, besides ID.
	 * @param inf
	 * @return
	 */
	
	public boolean editAccount(Account inf){
		int i = list.indexOf(inf);
		if(i==-1)
			return false;
		list.get(i).setName(inf.getName());
		list.get(i).setEmail(inf.getEmail());
		list.get(i).setPhonenum(inf.getPhonenum());
		list.get(i).setPw(inf.getPw());
		list.get(i).setUniv_comp(inf.getUniv_comp());
		list.get(i).setType(inf.getType());
		return true;
	}
}
