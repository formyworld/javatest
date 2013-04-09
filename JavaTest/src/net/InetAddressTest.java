package net;

import java.net.InetAddress;

public class InetAddressTest {

	public static void main(String args[]){
		try{
			if(args.length >0 ){
				String host = args[0];
				InetAddress[] addresses = InetAddress.getAllByName(host);
				for(InetAddress a:addresses){
					System.out.println(a.getHostAddress()+a.getHostName());
				}
			}
			else
			{
				InetAddress local = InetAddress.getLocalHost();
				System.out.println(local.getHostAddress());
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
