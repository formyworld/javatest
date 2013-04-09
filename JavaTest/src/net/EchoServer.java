package net;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			ServerSocket s = new ServerSocket(8189);
			Socket incoming = s.accept();
			
			try{
				InputStream inStream = incoming.getInputStream();
				OutputStream outStream = incoming.getOutputStream();
				
				Scanner in = new Scanner(inStream);
				PrintWriter out = new PrintWriter(outStream);
				
				out.println("hello , enter BYE to exit");
				
				boolean done  = false;
				while(!done && in.hasNextLine()){
					String line = in.nextLine();
					out.println("ECHO: "+line);
					if(line.trim().equalsIgnoreCase("bye")) done = true;
				}
			}
				finally{
					incoming.close();
				}
		
				
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
