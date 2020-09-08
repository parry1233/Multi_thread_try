package multi_threading;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.List;

public class Server extends Thread
{
	
	private ServerSocket SS;
	public List<Socket> list=new ArrayList<Socket>();//list to collect clients which connect to this server
	
	public Server()
	{
		this.initSS();
	}
	
	private void initSS()//initialize server socket
	{
		try
		{
			SS=new ServerSocket(5000+(int)(Math.random()*1001),5);//random server socket port
			System.out.println("Listening port:"+SS.getLocalPort());
		} 
		catch (Exception e)
		{
			this.initSS();//if exception happen, initialize again
		}
	}
	
	public void run()
	{
		while(true)
		{
			try
			{
				list.add(SS.accept());//while server thread run,add the new connected client socket to list
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		Server server = new Server();//new a server first
		server.start();//start server thread
		while(true)//infinite for loop
		{
			Scanner in=new Scanner(System.in);
			System.out.println("Write input:");
			String string=in.nextLine();//user input
			for(Socket s:server.list)//for loop which do for all socket listed
			{
				try 
				{
					DataOutputStream output=new DataOutputStream(s.getOutputStream());
					output.writeUTF(string);//write output to the client which for loop is pointed to
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
			}
		}
	}

}
