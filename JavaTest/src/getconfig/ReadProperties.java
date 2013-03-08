package getconfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	
	void getCfg(){
		InputStream i=null;
		try {
			i = new FileInputStream("resource/cfg.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("user"));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadProperties r = new ReadProperties();
		r.getCfg();
//		Properties prop = new Properties();
//		 
//    	try {
//    		//set the properties value
//    		prop.setProperty("database", "localhost");
//    		prop.setProperty("dbuser", "mkyong");
//    		prop.setProperty("dbpassword", "password");
// 
//    		//save properties to project root folder
//    		prop.store(new FileOutputStream("config.properties"), null);
// 
//    	} catch (IOException ex) {
//    		ex.printStackTrace();
//        }
//		
	}

}
