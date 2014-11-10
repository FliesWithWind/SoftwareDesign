import java.io.IOException;	
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;	
import java.net.SocketException;
import java.util.Scanner;

public class Client {
	private Socket socket = null;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private boolean isConnected = false;
    
    public Client(){
    	
    }
    
    public void transferTest(){
    	while(!isConnected){
    		try {
    			socket = new Socket("localhost",5555);
    			System.out.println("Connected");
    			isConnected = true;
    			outputStream = new ObjectOutputStream(socket.getOutputStream());
    			Room testRoom = new Room("TestRoom","TestLocation",10);
    			System.out.println("Object to be sent: " + testRoom);
    			outputStream.writeObject(testRoom);
    			socket.close();
    		} catch (SocketException se){
    			se.printStackTrace();
    		} catch (IOException e){
    			e.printStackTrace();
    		}
    	}
    	isConnected = false;
    }
    
    public void printMenu(){
    	System.out.println("***********Client console***********");
    	System.out.println("1. Run connection and transfer test");
    	System.out.println("2. Create a room");
    	System.out.println("3. List stored rooms");
    	System.out.println("4. Send a room to server");
    	System.out.println("0. Exit");
    }
    
    public static void main(String[] args) {
    	Client client = new Client();
    	Scanner in = new Scanner(System.in);
    	boolean exit = false;
    	int menuItem;
    	do {
    		client.printMenu();
    		menuItem = in.nextInt();
    		switch (menuItem){
    			case 1:
    				client.transferTest();
    				break;
    			case 2:
    				System.out.println("Not implemented yet");
    				break;
    			case 3:
    				System.out.println("Not implemented yet");
    				break;
    			case 4:
    				System.out.println("Not implemented yet");
    				break;
    			case 0:
    				exit = true;
    				in.close();
    				break;
    			default:
    				System.out.println("Invalid choice");
    		}
    	} while (!exit);
    }
}
