import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Test {

	public void testLog() throws Exception{
		Logger loger = Logger.getLogger("javaTest");
		FileHandler fh = new FileHandler("log.txt");
		loger.setLevel(Level.ALL);
        SimpleFormatter sf = new SimpleFormatter();
        fh.setFormatter(sf);
        loger.log(Level.INFO, "这是一个消息");
        loger.log(Level.WARNING, "这是一个警告");
        loger.log(Level.SEVERE, "这是一个服务器消息");
        
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t  = new Test();
		try {
			t.testLog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
