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
		
		try
		{
			while(!(msg = s.nextLine()).equals("q"))
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
