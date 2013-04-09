package oslab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePdfFromSource {

	/**
	 * to init PDF CREATING VARIABLES
	 */
	public CreatePdfFromSource(){
	
		//1
		tempTarget = new Document(PageSize.A4.rotate());
		target = new Document(PageSize.A4.rotate());
		
		
		//2
		 try {
			PdfWriter tempWriter=PdfWriter.getInstance(tempTarget, new FileOutputStream(tempFile));
			tempTarget.open();
			tempCanvas = tempWriter.getDirectContentUnder();
			tempCt = new ColumnText(tempWriter.getDirectContent());
			setBorder(tempCanvas);
			
			PdfWriter writer=PdfWriter.getInstance(target, new FileOutputStream(targetFile));
			target.open();
			canvas = writer.getDirectContentUnder();
			ct = new ColumnText(writer.getDirectContent());
			setBorder(canvas);
						
		 } catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	private void close(){
//		try {
//			tempTarget.add(new Paragraph("thank you ", new Font(FontFamily.TIMES_ROMAN, 20)));
//			target.add(new Paragraph("thank you ", new Font(FontFamily.TIMES_ROMAN, 20)));
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		tempTarget.close();
		target.close();
	}
	
	/**
	 * 
	 * @param f
	 */
	public void checkFile(File f,String dirname,int hier){
		String filename = f.getName();
		String filefullname = dirname==null?f.getName():dirname+"/"+f.getName();
		int  hi  = hier +1;
		
		if(f.isFile() && isSource(filename)){
			fname.add(f.getName());
			fullname.add(filefullname);
			fpages.add(pages);
			fpos.add(-1);
			fhier.add(hi);
			
			///to do with PDF creating////
			writeFileToPdf(f, filefullname);
			
		}
		if(f.isDirectory()){
			fname.add(f.getName());
			fullname.add(filefullname);
			fpages.add(pages);
			fpos.add(-1);
			fhier.add(hi);
		
			
			File children[] = f.listFiles();
			for(File child:children){
				checkFile(child,filefullname,hi);
			}
				
			
		}
			
		
	}
	
	private void writeFileToPdf(File f,String fullname){
		 int ps = 1;
		 int column = 0;
	     int status = ColumnText.START_COLUMN;
	   //get content
		int line = readFileByLines(f);
		//set header
		setHeaderName(fullname+"  lines: "+Integer.toString(line), tempCanvas);
		setHeaderPage(Integer.toString(ps++),tempCanvas);
		//set booter
		setBooterName(Integer.toString(tempPages++), tempCanvas);
		
		///begin to write
		 while(ColumnText.hasMoreText(status)){
	        	
	        	tempCt.setSimpleColumn(COLUMNS[column][0], COLUMNS[column][1],
	                    COLUMNS[column][2], COLUMNS[column][3]) ;
	        	
	        	tempCt.setYLine(COLUMNS[column][3]);
	        	
	        	try {
					status = tempCt.go();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	 column = Math.abs(column - 1);
	             if (column == 0 && ColumnText.hasMoreText(status)){
	                 tempTarget.newPage();
	                 setBorder(tempCanvas);
	               //set header
	         		setHeaderName(fullname, tempCanvas);
	         		setHeaderPage(Integer.toString(ps++),tempCanvas);
	         		//set booter
	         		setBooterName(Integer.toString(tempPages++), tempCanvas);
	             }
	                    
	        }
		
		/// new page
		 tempTarget.newPage();
         setBorder(tempCanvas);
		//============== the offical ==================================
       
         //
         int count = ps-1;
        ps=1;
        column = 0;
        status = ColumnText.START_COLUMN;
         //set header
 		setHeaderName(fullname+"  lines: "+Integer.toString(line), canvas);
 		setHeaderPage(Integer.toString(ps++)+" / "+Integer.toString(count),canvas);
 		//set booter
 		setBooterName(Integer.toString(pages++), canvas);
 		
 		///begin to write
 		 while(ColumnText.hasMoreText(status)){
 	        	
 	        	ct.setSimpleColumn(COLUMNS[column][0], COLUMNS[column][1],
 	                    COLUMNS[column][2], COLUMNS[column][3]) ;
 	        	
 	        	ct.setYLine(COLUMNS[column][3]);
 	        	
 	        	try {
 					status = ct.go();
 				} catch (DocumentException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 	        	 column = Math.abs(column - 1);
 	             if (column == 0 && ColumnText.hasMoreText(status)){
 	                 target.newPage();
 	                 setBorder(canvas);
 	               //set header
 	         		setHeaderName(fullname, canvas);
 	         		setHeaderPage(Integer.toString(ps++)+" / "+Integer.toString(count),canvas);
 	         		//set booter
 	         		setBooterName(Integer.toString(pages++), canvas);
 	             }
 	                    
 	        }
 		
 		/// new page
 		 target.newPage();
          setBorder(canvas);
		
		
	}
	public  int readFileByLines(File f) {
		File file = f;
		BufferedReader reader = null;
		int line = 1;
		try {
			
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
		    
			
			while ((tempString = reader.readLine()) != null) {
				
//				System.out.println("line " + line + ": " + tempString);
				Paragraph p = new Paragraph();
				p.setLeading(LEADING);
				p.setFont(new Font(FontFamily.COURIER, FONTSIZE));
				p.add( line + ": " +tempString);
				
				Paragraph p1 = new Paragraph();
				p1.setLeading(LEADING);
				p1.setFont(new Font(FontFamily.COURIER, FONTSIZE));
				p1.add(line + ": " +tempString);
				
				tempCt.addElement(p);
				ct.addElement(p1);
				
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return line;
	}
	/**
	 * check whethere file to be dealed with
	 * @param name
	 * @return
	 */
	private Boolean isSource(String name){
		if(name.matches("[a-z A-Z]*.cc"))
			return true;
		if(name.matches("[a-z A-Z]*.h"))
			return true;
		if(name.matches("[a-z A-Z]*.c"))
			return true;
		if(name.matches("[a-z A-Z]*.s"))
			return true;
//		if(name.matches("Make[a-z A-Z .]*"))
//			return true;
		
		return false;
	}
	/***
	 * set A4 border 
	 * @param canvas
	 */
	private void  setBorder(PdfContentByte canvas){
    	canvas.saveState();
    	/// floor
    	canvas.setColorStroke(new BaseColor(0x30, 0x30, 0x30));
    	canvas.moveTo(LEFT, FLOOR-1);
    	canvas.lineTo(RIGHT, FLOOR-1);
    	canvas.stroke();
    	/// ceil
    	canvas.moveTo(LEFT, CEIL);
    	canvas.lineTo(RIGHT, CEIL);
    	canvas.stroke();
    	/// mid
    	canvas.setColorStroke(new BaseColor(0xa0, 0xa0, 0xa0));
    	canvas.moveTo(WIDTH/2, FLOOR);
    	canvas.lineTo(WIDTH/2, CEIL);
    	canvas.stroke();    	
    	
    	
    	canvas.restoreState();
    }
	
	 private void setHeaderName(String name,PdfContentByte canvas){
	    	
	    	try {
	    		canvas.saveState();
	        	canvas.beginText();
	        	canvas.moveText(LEFT, CEIL+2);
	        	
				canvas.setFontAndSize(BaseFont.createFont(), 10);
				canvas.showText(name);			
				canvas.endText();
		    	canvas.restoreState();
		    	
		    	
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	   private void setHeaderPage(String name,PdfContentByte canvas){
	    	
	    	try {
	    		canvas.saveState();
	    		// show backgroud
				canvas.setColorFill(new BaseColor(0xf0, 0xf0, 0xf0));

				canvas.rectangle(RIGHT-25, CEIL, 25, 15);
				canvas.fill();

				// /show text
	        	canvas.beginText();
	        	canvas.moveText(RIGHT-23, CEIL+2);
	        	canvas.setColorFill(BaseColor.BLACK);
				canvas.setFontAndSize(BaseFont.createFont(), 10);
				canvas.showText(name);			
				canvas.endText();
		    	canvas.restoreState();
		    	
		    	
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	   private void setBooterName(String name,PdfContentByte canvas){
	   	
	   	try {
	   		canvas.saveState();
	       	canvas.beginText();
	       	canvas.moveText(RIGHT-20, FLOOR-12);
	       	
				canvas.setFontAndSize(BaseFont.createFont(), 13);
				canvas.showText(name);			
				canvas.endText();
		    	canvas.restoreState();
		    	
		    	
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	
	   }
	public static void main(String[] args) {
		CreatePdfFromSource c = new CreatePdfFromSource();
		File f = new File(sourceName);
		c.checkFile(f, null, 0);
		c.printListToPdf();
		c.printList();
		c.close();
		
	}
	private void printListToPdf(){
		for(int i =1;i<fname.size();i++){
			Paragraph p = new Paragraph();
			p.add("P#");
			String num = String.format("%-" + 3 + "s", Integer.toString(fpages.get(i)));
			p.add(num);
			p.add(" : ");
			for(int j=0;j<fhier.get(i)-2;j++)
				p.add(" ** ");
			p.add(fname.get(i));
			ct.addElement(p);
			
		}
		int column = 0;
	     int status = ColumnText.START_COLUMN;
	     while(ColumnText.hasMoreText(status)){
	        	
	        	ct.setSimpleColumn(COLUMNS[column][0], COLUMNS[column][1],
	                    COLUMNS[column][2], COLUMNS[column][3]) ;
	        	
	        	ct.setYLine(COLUMNS[column][3]);
	        	
	        	try {
					status = ct.go();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	 column = Math.abs(column - 1);
	             if (column == 0 && ColumnText.hasMoreText(status)){
	                 target.newPage();
	                 setBorder(canvas);
	               //set header
	         		
	             }
	                    
	        }
	}
	public void printList(){
		for(int i =0;i<fname.size();i++){
			System.out.println(fname.get(i)+" : "+fullname.get(i)+" : "+fpages.get(i));
		}
	}
	//========statitics when creating pdf==================
	private List<String> fname = new ArrayList<String>();
	private List<String> fullname = new ArrayList<String>();
	private List<Integer> fpages = new ArrayList<Integer>();
	private List<Integer> fpos = new ArrayList<Integer>();
	private List<Integer> fhier = new ArrayList<Integer>();
	private int tempPages = 1;
	private int pages = 1;
	//========pdf layout ===================================
 	public static final float WIDTH=841;//595;
    public static final float HIGHT=595;//842;
    public static final float CEIL=HIGHT-20;
    public static final float FLOOR=14;
    public static final float LEFT=15;
    public static final float RIGHT=WIDTH-15;
    public static final float FONTSIZE =(float) 9.5;
    public static final float LEADING =10;
    public static final float[][] COLUMNS = {
		{ LEFT, FLOOR, WIDTH / 2 , CEIL },
		{ WIDTH / 2+2 , FLOOR, RIGHT, CEIL } };
    
	//=======pdf file name=====================================
    private final String tempFile="nachostemp.pdf";
	private final String targetFile="nachos.pdf";
	//=======pdf creating tempfiles ===========================
	private Document tempTarget = null;
	private Document target = null;
	private PdfContentByte tempCanvas = null;
	private PdfContentByte canvas = null;
	private ColumnText tempCt =null;
	private ColumnText ct =null;
	
    //=======source directory name============================	
	private static final String   sourceName = "nachos-3.4";
}
