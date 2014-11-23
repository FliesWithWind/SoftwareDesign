package console;

import java.io.*;
import java.util.ArrayList;

import datatype.Account;
import network.*;

public class ServerConsole {	
	
	private static ArrayList<Account> AccountList;
	
	public ServerConsole(){
		
	}
	
	private static boolean newAccount(String command){
    	String[] cmd = command.split(" ");
    	if(cmd.length!=8){
    		System.out.println("[Error] Invalid number of parameters!");
    		return false;
    	} else{
    		int type=0;
    		if(cmd[3].equalsIgnoreCase("user"))
    			type=1;
    		else if(cmd[3].equalsIgnoreCase("staff"))
    			type=2;
    		else if(cmd[3].equalsIgnoreCase("manager"))
    			type=3;
    		
    		for(int i=0;i<cmd.length;i++)
    			System.out.println(i + " " + cmd[i]);
    		Account tmp = new Account(cmd[1],cmd[2],type,cmd[4],cmd[5],cmd[6],cmd[7]);
    		try {
    			AccountList.add(tmp);
    		}
    		catch (Exception ex){
    			System.out.println("[Error] Problem occured while adding to the list.");
    		}
    		System.out.println("New account sucessfully created.");
    		return true;
    	}
	}
	
	private static void list(){
		for(int i=0;i<AccountList.size();i++)
			System.out.println(AccountList.get(i).getName() + " " + AccountList.get(i).getId() + " " + AccountList.get(i).getPhonenum());
	}
	
	public static void main(String[] args){
		AccountList = new ArrayList<Account>();
		
		System.out.println(":::::::Starting Server Console:::::::");
		System.out.println(":::::::Type help for more info:::::::");
		try
	    {
	      BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	      String message;

	      while (true) 
	      {
	        message = input.readLine();
	        if(message.equals("help")){
	    		System.out.println(":::::::List of avalible commands:::::::");
			 	System.out.println("create_account <login> <password> <user/staff/manager> <name> <e-mail> <phone_number> <commpany>");
			 	System.out.println("create_room <name> <city> <location> <capacity> <rent_cost>");
			 	System.out.println("list_accounts");
			 	System.out.println("list_rooms");
	        } else if(message.startsWith("create_account")){
	        	newAccount(message);
	        } else if(message.startsWith("list_accounts")){
	        	list();
	        }	        
	      }
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
		
	}
}
