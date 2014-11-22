import java.io.IOException;
import java.util.Scanner;


public class NetTestServer
{
	public static void main(String[] args)
	{
		String msg;
		Scanner s = new Scanner(System.in);
		
		network.Listener net = new network.Listener(new control.Processor());
		(new Thread(net)).start();

		System.out.println("Server on");
		
		while(!(msg = s.nextLine()).equals("q"));
	}
}
