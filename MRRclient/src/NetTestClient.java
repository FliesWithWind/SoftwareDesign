import java.io.IOException;
import java.util.Scanner;

public class NetTestClient
{
	public static void main(String[] args)
	{
		network.Network net = network.Network.getInstance();
		
		String msg;
		Scanner s = new Scanner(System.in);
		System.out.println("Simple Echo Program");
		
		try
		{
			while(!(msg = s.nextLine()).equals("q"))
				net.send(new network.Packet(0, null, null, msg));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
