package test.com.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 */

/**
 * This class is used for reading the following
 * 1. File Type
 * 2. File Size
 * 3. File Name
 * 
 * File path can be stored in the properties file if saved locally in file system or can be stored in the resources folder
 * and class loader can be used to get the path
 * 
 * @author User
 *
 */
public class GetFileInformation 
{	
	
	
 	static String filepath = "C://Users//User//Desktop//testData";
	static File inputFile = new File("C://Users//User//Desktop//testData//TestData1.xlsx");
    static String SheetName = "Sheet1";
    
   
    public void loadTestData()
    {
    	
    }
    
	public  Map<Integer, HashMap<String, String>> getFileContent() throws IOException
	{

        
		FileInfo fileInfo = new FileInfo();
        Map mapTemp = new HashMap();
		HashMap<Integer, FileInfo> fileInfoMap = new HashMap<Integer, FileInfo>();
        File dir = new File(filepath);
        String[] files = dir.list();
        if (files.length == 0) 
        {
            System.out.println("The directory is empty");
        } else
        {
            for (int j = 0 ; j<files.length ; j++) 
            {
            	String aFile = files[j].toString();
                String extension = "";
                int i = aFile.lastIndexOf('.');
                if (i >= 0) 
                {
                    extension = aFile.substring(i+1);
                    
                }
                
                fileInfo.setFileExtension(extension);
                fileInfo.setFileName(aFile);
                fileInfo.setFileSize(aFile.length());
                System.out.println("File Name ---> " + aFile);
                System.out.println("File Extension ---> " + extension);
                System.out.println("File Size ---> " + aFile.length());
                
                fileInfoMap.put(j, fileInfo);
            }
            
            mapTemp = readExcelandCreateMap(inputFile,SheetName);
            
            for(int a = 0 ; a<mapTemp.size() ; a++)
            {
            	System.out.println("File contents ");
            	System.out.println(mapTemp.get(a));
            }
        }
        return mapTemp;
	}
	
	public static  Map<Integer, HashMap<String, String>> readExcelandCreateMap(File inputFile, String SheetName) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream((inputFile)));
        Map<Integer, HashMap<String, String>> data = new HashMap<>();
       

        int temp = workbook.getNumberOfSheets();
        int rowCnt = 0;
        String cellRegNo = "";
        String cellTestCaseName ="";
        
        HashMap<String,String> hasMapValues = new HashMap<String,String>();
        for (int i = 0; i < temp; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);

            if (sheet.getSheetName().equals(SheetName)) {
                Iterator<Row> rowIterator = sheet.rowIterator();

                while (rowIterator.hasNext()) {

                    Row nextRow = rowIterator.next();
                    if (nextRow.getRowNum() == 0) {
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        String colunms = "";
                        String colunms1 = "";
                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            String cellobj = cell.getStringCellValue();
                            colunms += cell.getStringCellValue();                           
                            if (cellIterator.hasNext())
                                colunms += ",";
                        }
                        continue;
                    }
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    
                    while (cellIterator.hasNext()) 
                    {
                        Cell cell = cellIterator.next();
                        
                        switch (cell.getCellType()) 
                        {
                            case Cell.CELL_TYPE_STRING:
                            	cellTestCaseName = cell.getStringCellValue();
                                System.out.println(cellTestCaseName);
                                hasMapValues = new HashMap<String,String>();
                                hasMapValues.put("TestCaseName", cellTestCaseName);
                                while (cellIterator.hasNext())
                                {
                                    Cell cell1 = cellIterator.next();
                                    String cellregNo = cell1.getStringCellValue();
                                    System.out.println(cellregNo);
                                    hasMapValues.put("regNo", cellregNo);                                  
                                    
                                    if (cellIterator.hasNext())
                                    	cellregNo += ",";
                                    
                                    while (cellIterator.hasNext())
                                    {
                                        Cell cell2 = cellIterator.next();
                                        String cellMake = cell2.getStringCellValue();
                                        System.out.println(cellMake);
                                        hasMapValues.put("Make", cellMake);                                  
                                        
                                        if (cellIterator.hasNext())
                                        	cellMake += ",";
                                                             
                                    while (cellIterator.hasNext())
                                    {
                                        Cell cell3 = cellIterator.next();
                                        String cellColour = cell3.getStringCellValue();
                                        System.out.println(cellColour);
                                        hasMapValues.put("Colour", cellColour);                                  
                                        
                                        if (cellIterator.hasNext())
                                        	cellColour += ",";
                                    }
                                    data.put(rowCnt, hasMapValues);
                                    rowCnt++;   
                                } 
                                }
                               break;

                            case Cell.CELL_TYPE_NUMERIC:
                                cellRegNo = cell.getStringCellValue();
                                break;
                        }
                        break;   
                    }      
                    }
                }
            }
        return data;
}

}
