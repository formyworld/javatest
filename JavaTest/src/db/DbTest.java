package db;

import java.sql.Statement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DbTest {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream db_info_file = null;
		Properties db_info = null;
		String driver = null;
		String conn_url = null;
		String username = null;
		String password = null;
		Connection conn = null;
		
	//1 read db info
		try {
			db_info_file = new FileInputStream("resource/db.properties");
			db_info = new Properties();
			db_info.load(db_info_file);
			conn_url = db_info.getProperty("conn_url");
			driver = db_info.getProperty("driver");
			username = db_info.getProperty("username");
			password = db_info.getProperty("password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	//2 connect 
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(conn_url,username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//3 query
		try {
			Statement smt = conn.createStatement();
			String sql = "select rfid rfid from userinfo where username='chengang10'";
			ResultSet ret = smt.executeQuery(sql);
			System.out.println(ret.getRow());	
			while(ret.next()){
				System.out.println(ret.getRow());
				System.out.println(ret.getInt("rfid"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	//4 close
	}

}
