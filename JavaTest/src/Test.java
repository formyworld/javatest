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
        loger.log(Level.INFO, "����һ����Ϣ");
        loger.log(Level.WARNING, "����һ������");
        loger.log(Level.SEVERE, "����һ����������Ϣ");
        
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
