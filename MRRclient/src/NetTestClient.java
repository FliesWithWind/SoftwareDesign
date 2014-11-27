import java.io.IOException;
import java.util.Scanner;

import datatype.Account;
import network.Packet;

public class NetTestClient
{
	public static void main(String[] args)
	{
		network.Network net = network.Network.getInstance();
		
		String msg;
		Scanner s = new Scanner(System.in);
		System.out.println("Simple Echo Program");
		//System.out.println("Input form : ID PW type name email phonenum univ_comp");
		
		datatype.Account acc = new datatype.Account("admin","pass",3,"TestAdmin","admin@net.com","1214214","TestCompany");
		datatype.Account acc1 = new datatype.Account("manager","pass",2,"TestManager","manager@net.com","1214214","TestCompany");
		datatype.Account acc2 = new datatype.Account("user","pass",1,"TestUser","user@net.com","1214214","TestCompany");
		
		datatype.Room room = new datatype.Room(null, "TestRoom1", 1, "Random street", 20, 20000, "Clientsiede gen id");
		datatype.Room room1 = new datatype.Room(null, "TestRoom2", 1, "Random street", 30, 50000, "Clientsiede gen id");
		datatype.Room room2 = new datatype.Room(null, "TestRoom3", 1, "Random street", 40, 70000, "Clientsiede gen id");
		
		try
		{
			while(!(msg = s.nextLine()).equals("q")){
				if(msg.compareTo("accounts")==0){
					net.send(new network.Packet(Packet.REGISTER, null, null, acc));
					net.send(new network.Packet(Packet.REGISTER, null, null, acc1));
					net.send(new network.Packet(Packet.REGISTER, null, null, acc2));
				}else if(msg.compareTo("rooms")==0){
					net.send(new network.Packet(Packet.CREATE_ROOM,"manager","pass",room));
					net.send(new network.Packet(Packet.CREATE_ROOM,"manager","pass",room1));
					net.send(new network.Packet(Packet.CREATE_ROOM,"manager","pass",room2));
				} else
					net.send(new network.Packet(0, null, null, msg));
			}
			/*{
				Account inf		= new Account(	msg.split(" ")[0],
												msg.split(" ")[1],
												Integer.parseInt(msg.split(" ")[2]),
												msg.split(" ")[3],
												msg.split(" ")[4],
												msg.split(" ")[5],
												msg.split(" ")[6]);
				
				Packet packet	= new Packet(0, msg.split(" ")[0], msg.split(" ")[1], inf);
				
				net.send(packet);
			}*/
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
