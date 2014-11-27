package console;

import java.io.*;
import java.util.ArrayList;

import control.*;
import datatype.Account;
import datatype.Room;
import network.*;

public class ServerConsole {	
	
	private static ArrayList<Room> RoomList;
	
	public ServerConsole(){
		
	}	 	
	
	private static void listAccounts(AccountManager am){
		for(int i=0;i<am.getAccountList().size();i++)
			System.out.println("Name: " + am.getAccountList().get(i).getName() + "\t\tID: " + am.getAccountList().get(i).getId() + "\tE-mail: " + am.getAccountList().get(i).getEmail() + "\tPhone number: " + am.getAccountList().get(i).getPhonenum());
	}
	
	private static void listRegistrations(AccountManager am){
		for(int i=0;i<am.getRegisterList().size();i++)
			System.out.println("Name: " + am.getRegisterList().get(i).getName() + "\t\tID: " + am.getRegisterList().get(i).getId() + "\tE-mail: " + am.getRegisterList().get(i).getEmail() + "\tPhone number: " + am.getRegisterList().get(i).getPhonenum());
	}
	
	private static void listRooms(RoomManager rm){
		for(int i=0;i<rm.getList().size();i++)
			System.out.println("Name: " + rm.getList().get(i).getName() + "\tCity: " + rm.getList().get(i).getCity() + "\tLocation: " + rm.getList().get(i).getLocation() + "\tCapacity: " + rm.getList().get(i).getMaxcapacity() + "\tRent Cost: " + rm.getList().get(i).getDefault_rentcost());
	}
	
	private static ArrayList<Account> loadAccounts() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream("accounts.xml"));
		ArrayList<Account> list = (ArrayList) fileIn.readObject();
		fileIn.close();
		return list;
	}
	
	private static void acceptAll(AccountManager am){
		int size = am.getRegisterList().size();
		for(int i=0;i<size;i++)
			if(am.acceptRegistration(am.getRegisterList().get(0).getId()))
				System.out.println("Accepted sucesfully.");
	}
	
	/*private static void saveRooms() throws FileNotFoundException, IOException{
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
		//RoomList = new ArrayList<Room>();
		
		
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
	    		System.out.println("list_reg");
	    		System.out.println("list_accounts");
			 	System.out.println("list_rooms");
			 	System.out.println("accept_all");
			 	System.out.println("save_accounts");
			 	System.out.println("load_accounts");
			 	System.out.println("save_rooms");
			 	System.out.println("load_rooms");
	        } else if(message.startsWith("list_accounts"))
	        	listAccounts(processor.getAM());
	        else if(message.startsWith("list_reg"))
	        	listRegistrations(processor.getAM());
	        else if(message.startsWith("list_rooms"))
	        	listRooms(processor.getRM());
	        else if(message.startsWith("accept_all"))
	        	acceptAll(processor.getAM());
	        else if(message.startsWith("save_accounts")){
	        	fileio.FileIO.saveData(processor.getAM().getAccountList());
	        	fileio.FileIO.saveRegisters(processor.getAM().getRegisterList());
	        }else if(message.startsWith("load_accounts")){
	        	processor.getAM().setAccountList(fileio.FileIO.loadData());
	        	processor.getAM().setRegisterList(fileio.FileIO.loadRegisters());
	        }else if(message.startsWith("save_rooms"))
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
