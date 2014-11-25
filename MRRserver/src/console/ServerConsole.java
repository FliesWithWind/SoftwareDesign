package console;

import java.io.*;
import java.util.ArrayList;

import control.*;
import datatype.Account;
import datatype.Room;
import network.*;

public class ServerConsole {	
	
	//private static ArrayList<Account> AccountList;
	private static ArrayList<Room> RoomList;
	
	public ServerConsole(){
		
	}
	
	/**
	 * Method to find and return Account object using id
	 * Returns null if Account was not found.
	 * @param id
	 * @return
	 */
	
	/*private static Account findAccount(String id){
		Account tmp;
		for(int i=0;i<AccountList.size();i++){
			if(AccountList.get(i).getId().equals(id)){
				tmp=AccountList.get(i);
				return tmp;
			}
		}
		return null;
	}*/
	
	
	private static Account newAccount(String command){
    	String[] cmd = command.split(" ");	//Split string into parts
    	if(cmd.length!=8){					//Check if command is correct
    		System.out.println("[Error] Invalid number of parameters!");
    		return null;
    	} else{
    		int type=0; // get account type value
    		if(cmd[3].equalsIgnoreCase("user"))
    			type=1;
    		else if(cmd[3].equalsIgnoreCase("staff"))
    			type=2;
    		else if(cmd[3].equalsIgnoreCase("manager"))
    			type=3;
    		// Debug to check if string is split correctly
    		//for(int i=0;i<cmd.length;i++)
    		//	System.out.println(i + " " + cmd[i]);
    		
    		Account tmp = new Account(cmd[1],cmd[2],type,cmd[4],cmd[5],cmd[6],cmd[7]);
    		System.out.println("New account sucessfully created.");
    		return tmp;
    	}
	}
	
	
	/*private static boolean newRoom(String command){
    	String[] cmd = command.split(" ");	//Split string into parts
    	if(cmd.length!=7){					//Check if command is correct
    		System.out.println("[Error] Invalid number of parameters!");
    		return false;
    	} else{
    		// Debug to check if string is split correctly
    		//for(int i=0;i<cmd.length;i++)
    		//	System.out.println(i + " " + cmd[i]);
    		
    		int city=0; //get city value
    		if(cmd[3].equalsIgnoreCase("seoul"))
    			city=1;
    		else if(cmd[3].equalsIgnoreCase("daejeon"))
    			city=2;
    		else if(cmd[3].equalsIgnoreCase("daegu"))
    			city=3;
    		else if(cmd[3].equalsIgnoreCase("jeonju"))
    			city=4;
  
    		Account acc = findAccount(cmd[1]);
    		if(acc == null){
    			System.out.println("Account id does not exist.");
    			return false;
    		} else {
	    		Room tmp = new Room(acc,cmd[2],city,cmd[4],Integer.parseInt(cmd[5]),Float.parseFloat(cmd[6]));
	    		try {
	    			RoomList.add(tmp);
	    		}
	    		catch (Exception ex){
	    			System.out.println("[Error] Problem occured while adding to the list.");
	    		}
	    		System.out.println("New account sucessfully created.");
	    		return true;
    		}
    	}
	}*/
	
	private static void listAccounts(AccountManager am){
		for(int i=0;i<am.getRegisterList().size();i++)
			System.out.println("Name: " + am.getRegisterList().get(i).getName() + "\tID: " + am.getRegisterList().get(i).getId() + "\tE-mail: " + am.getRegisterList().get(i).getEmail() + "\tPhone number: " + am.getRegisterList().get(i).getPhonenum());
	}
	
	/*private static void listRooms(){
		for(int i=0;i<RoomList.size();i++)
			System.out.println("Name: " + RoomList.get(i).getName() + "\tCity: " + RoomList.get(i).getCity() + "\tLocation: " + RoomList.get(i).getLocation() + "\tCapacity: " + RoomList.get(i).getMaxcapacity() + "\tRent Cost: " + RoomList.get(i).getDefault_rentcost());
	}
	
	private static void saveAccounts() throws FileNotFoundException, IOException{
		ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("accounts.xml"));
		try{
			fileOut.writeObject(AccountList);
			fileOut.close();
		}
		catch(IOException e){
			System.out.println("Encountered problem while writign to the file.");
		}
	}
	
	private static ArrayList<Account> loadAccounts() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("accounts.xml"));
		ArrayList<Account> list = (ArrayList) fileIn.readObject();
		fileIn.close();
		return list;
	}
	
	private static void saveRooms() throws FileNotFoundException, IOException{
		ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("rooms.xml"));
		try{
			fileOut.writeObject(RoomList);
			fileOut.close();
		}
		catch(IOException e){
			System.out.println("Encountered problem while writign to the file.");
		}
	}

	private static ArrayList<Room> loadRooms() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("rooms.xml"));
		ArrayList<Room> list = (ArrayList) fileIn.readObject();
		fileIn.close();
		return list;
	}*/
	
	public static void main(String[] args){
		Processor processor = new Processor();
		//AccountManager AM = new AccountManager();
		RoomList = new ArrayList<Room>();
		
		
		network.Listener net = new network.Listener(processor);
		(new Thread(net)).start();
		System.out.println("::::::::Starting the Listener::::::::");		
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
			 	System.out.println("create_room <owner_id> <name> <city> <location> <capacity> <rent_cost>");
			 	System.out.println("list_accounts");
			 	System.out.println("list_rooms");
			 	System.out.println("save_accounts");
			 	System.out.println("load_accounts");
			 	System.out.println("save_rooms");
			 	System.out.println("load_rooms");
	        } else if(message.startsWith("create_account"))
	        	processor.getAM().addAccount(newAccount(message));
	        else if(message.startsWith("list_accounts"))
	        	listAccounts(processor.getAM());
	        else if(message.startsWith("create_room"))
	        	System.out.println("Not implemented yet.");
	        else if(message.startsWith("list_rooms"))
	        	System.out.println("Not implemented yet.");
	        else if(message.startsWith("save_accounts"))
	        	System.out.println("Not implemented yet.");
	        else if(message.startsWith("load_accounts"))
	        	System.out.println("Not implemented yet.");
	        else if(message.startsWith("save_rooms"))
	        	System.out.println("Not implemented yet.");
	        else if(message.startsWith("load_rooms"))
	        	System.out.println("Not implemented yet.");
	      }
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
		
	}
}
