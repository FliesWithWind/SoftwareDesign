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
		System.out.println("Input form : ID PW type name email phonenum univ_comp");
		
		datatype.Account acc = new datatype.Account("admin","pass",3,"TestName","mail@net.com","1214214","TestCompany");
		
		try
		{
			while(!(msg = s.nextLine()).equals("q"))
				if(msg.compareTo("test")==0)
					net.send(new network.Packet(Packet.REGISTER, null, null, acc));
				else
					net.send(new network.Packet(0, null, null, msg));
			{
				Account inf		= new Account(	msg.split(" ")[0],
												msg.split(" ")[1],
												Integer.parseInt(msg.split(" ")[2]),
												msg.split(" ")[3],
												msg.split(" ")[4],
												msg.split(" ")[5],
												msg.split(" ")[6]);
				
				Packet packet	= new Packet(0, msg.split(" ")[0], msg.split(" ")[1], inf);
				
				net.send(packet);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
