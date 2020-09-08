package multi_threading;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread
{
	String ipString;
	int portString;
	
	public Client(String ip,int port)
	{
		ipString=ip;
		portString=port;
	}
	
	public void run() 
	{
		connect(ipString, portString);
	}
	
	public void connect(String ip,int port)//connect server
	{	
		InetSocketAddress inetSocketAddress=new InetSocketAddress(ip,port);
		try
		{
			Socket s=new Socket();
			s.connect(inetSocketAddress);
			this.receive(s);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void receive(Socket s)//receive and printout server message
	{
		try
		{
			DataInputStream input=new DataInputStream(s.getInputStream());
			while(true)
			{
				System.out.println("[Client IP:"+s.getRemoteSocketAddress().toString()+"] Server says--"+input.readUTF());
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		while(true)//infinite loop
		{
			Scanner scanner=new Scanner(System.in);
			System.out.print("ip:");
			String ip=scanner.nextLine();
			System.out.print("port:");
			int port=scanner.nextInt();
			new Client(ip,port).start();//new a client thread and start it
		}
	}
	
	
}
