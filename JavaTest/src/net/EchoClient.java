package net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class EchoClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket s = new Socket();
		try {
//			s.connect(new InetSocketAddress("192.168.3.88", 8189));
//			Scanner socket_scanner = new Scanner(s.getInputStream());
//			PrintWriter printer = new PrintWriter(s.getOutputStream());
			Scanner console_scanner = new Scanner(System.in);
			
			boolean done = false;
			while(!done && console_scanner.hasNextLine()){
				String from_console = console_scanner.nextLine();
				System.out.println(from_console);
				if(from_console.trim().equalsIgnoreCase("quit")) done=true;
			}
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
