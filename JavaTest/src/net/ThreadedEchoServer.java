package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.print.attribute.standard.OutputDeviceAssigned;

public class ThreadedEchoServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int i = 1;
			ServerSocket s = new ServerSocket(8189);

			while (true) {
				Socket incoming = s.accept();
				System.out.println("get a client " + i++);
				Runnable r = new ThreadedEchoHandler(incoming);
				Thread t = new Thread(r);
				t.start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class ThreadedEchoHandler implements Runnable {

	private Socket s = null;

	public ThreadedEchoHandler(Socket incoming) {
		// TODO Auto-generated constructor stub
		s = incoming;

	}

	@Override
	public void run() {
		Scanner scanner = null;
		PrintWriter printer = null;
		try {
			try {
				scanner = new Scanner(s.getInputStream());
				printer = new PrintWriter(s.getOutputStream());

				boolean quit = false;
				while (!quit && scanner.hasNextLine()) {
					String msg = scanner.nextLine();
					printer.println(msg);
					if (msg.trim().equalsIgnoreCase("bye"))
						quit = true;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				s.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
