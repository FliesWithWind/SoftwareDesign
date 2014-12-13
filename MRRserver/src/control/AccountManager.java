package control;

import java.util.ArrayList;
import java.util.regex.Pattern;

import datatype.*;


public class AccountManager
{
	private ArrayList<Account> list;
	private	ArrayList<Account> registerlist;
	
	public AccountManager(ArrayList<Account> list, ArrayList<Account> registerlist)
	{
		this.list			= list;
		this.registerlist	= registerlist;
	}
	
	/*** validates id and pw ***/
	// invalid, not found	: returns 0
	// valid				: returns User - 1, Staff - 2, Manager - 3
	public int validate(String id, String pw) throws Exception
	{
		Account client = searchAccount(id);
		
		if(client == null) return 0;						// not found
		if(client.getPw().equals(pw)) return client.getType();	// valid
		return 0;											// invalid
	}

	/*** search account with id ***/
	// found		: returns account itself
	// not found	: returns null
	public Account searchAccount(String id)
	{
		for(Account iter : list) 					// iterates in account list
			if(iter.getId().equals(id)) return iter;
		
		return null; 								// not found
	}
	
	/*** add Registration record to register list ***/
	// accepted										: returns 0
	// rejected by duplicated id in account list	: returns 1
	// rejected by duplicated id in register list	: returns 2
	// rejected by an invalid information form		: returns 3
	public int addRegistration(Account inf) throws Exception
	{	
		inf.setId(inf.getId().toLowerCase());					// To Lower character string
		inf.initMyrooms();										// Clear room list
		inf.initMyreservations();								// Clear reservation list
		inf.setId(inf.getId().replace(" ", ""));				// remove blanks in ID
		inf.setPw(inf.getPw().replace(" ", ""));				// remove blanks in PW
		
		if(searchAccount(inf.getId()) != null)		return 1; // check duplicated id in account list
		if(searchRegistration(inf.getId()) != null) return 2; // check duplicated id in register list
		if(!validateAccountForm(inf))				return 3; // check check form of information
		
		registerlist.add(inf);
		return 0;
	}

	/*** Edit Account information ***/
	// accepted			: returns 0
	// not found		: returns 1
	// invalid form		: returns 2
	// Editing id or type is not allowed
	public int editAccount(Account inf) throws Exception
	{
		Account target = searchAccount(inf.getId());
		
		if(!validateAccountForm(inf))	return 1; // check if it's valid form
		if(target == null)				return 2; // check if it's registered
		
		target.setPw(inf.getPw());
		target.setName(inf.getName());
		target.setEmail(inf.getEmail());
		target.setPhonenum(inf.getPhonenum());
		target.setUniv_comp(inf.getUniv_comp());
		
		return 0;
	}

	/*** Move a Registration to Account list ***/
	// accepted		: returns 0
	// not found	: returns 1
	public int acceptRegistration(String id) throws Exception
	{
		Account target = searchRegistration(id);
		
		if(target == null) return 1; // null checking
		
		list.add(target);
		registerlist.remove(target);
		
		return 0;
	}

	/*** Remove a Registration from register list ***/
	// accepted		: returns 0
	// not found	: returns 1
	// Editing id or type is unauthorized
	public int rejectRegistration(String id) throws Exception
	{
		Account target = searchRegistration(id);
		
		if(target == null) return 1; // null checking
		
		registerlist.remove(target);
		
		return 0;
	}
	
	/*** check if it's valid form of Account information ***/
	// valid	: true
	// invalid	: false
	private boolean validateAccountForm(Account inf) throws Exception
	{
		Pattern p = Pattern.compile("[^a-zA-Z0-9]");
		if(p.matcher(inf.getId()).find())		return false; // check if ID's alphanumeric
		if(p.matcher(inf.getPw()).find())		return false; // check if PW's alphanumeric
		
		int type = inf.getType();
		if(type > 3 || type < 1)				return false; // check type's range
		
		String nametemp = inf.getName();
		nametemp = nametemp.replace(" ", "");
		if(nametemp.equals(""))					return false; // check if name's just blank
		
		if(	!inf.getEmail().contains("@") ||
			!inf.getEmail().contains("."))		return false; // check if email contains @, .
		
		p = Pattern.compile("^[[0-9]+[-]]");
		if(p.matcher(inf.getPhonenum()).find())	return false; // check if Phonenum's numeric

		String univ_comptemp = inf.getUniv_comp();				
		univ_comptemp.replace(" ", "");
		if(univ_comptemp.equals(""))			return false; // check if univ_comp's just blank
		
		return true;
	}
	
	public ArrayList<Account> getRegisterList()
	{
		return registerlist;
	}
	
	public ArrayList<Account> getAccountList()
	{
		return list;
	}
	
	public void setRegisterList(ArrayList<Account> input)
	{
		this.registerlist = input;
	}
	
	public void setAccountList(ArrayList<Account> input)
	{
		this.list = input;
	}

	/*** search registration with id ***/
	// found		: returns registration itself
	// not found	: returns null
	private Account searchRegistration(String id) throws Exception
	{
		for(Account iter : registerlist) 			// iterates in account list
			if(iter.getId().equals(id)) return iter;
		
		return null; 								// not found
	}
}
