import java.io.IOException;	
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;	
import java.net.SocketException;

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
    		} catch (SocketException se){
    			se.printStackTrace();
    		} catch (IOException e){
    			e.printStackTrace();
    		}
    	}
    }
    
    public static void main(String[] args) {
    	Client client = new Client();
    	client.transferTest();
    }
}
