import java.io.IOException;
import java.util.Scanner;

import network.Packet;

public class NetTestClient
{
	public static void main(String[] args)
	{
		network.Network net = network.Network.getInstance();
		
		String msg;
		Scanner s = new Scanner(System.in);
		System.out.println("Simple Echo Program");
		
		datatype.Account acc = new datatype.Account("admin","pass",3,"TestName","mail@net.com","1214214","TestCompany");
		
		try
		{
			while(!(msg = s.nextLine()).equals("q"))
				if(msg.compareTo("test")==0)
					net.send(new network.Packet(Packet.REGISTER, null, null, acc));
				else
					net.send(new network.Packet(0, null, null, msg));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
