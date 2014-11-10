import java.io.*;
import java.net.*;

public class Server {
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private ObjectInputStream inStream = null;
	
	public Server(){
		
	}
	
	public void listen(){
		try{
			serverSocket = new ServerSocket(5555);
			socket = serverSocket.accept();
			System.out.println("Server is waiting for data");
			inStream = new ObjectInputStream(socket.getInputStream());
			Room tmp = (Room) inStream.readObject();
			System.out.println("Object recieved: " + tmp.getClass().getName());
			System.out.println("Room name: " +tmp.name_ + " Location: " + tmp.location_);
			socket.close();
		} catch (SocketException se){
			System.exit(0);
		} catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException cn){
			cn.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.listen();
	}
}
