package com.aranin.spring.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 9/3/13
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class GenerateSearchOverrides {
    public static void main(String[] args){
        BufferedWriter bw = null;
        try{
            try {

                File file = new File("C:/FDC-Site-Search-Queries-and-Alt-Title-Tags-Aug2013.sql");

                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                bw = new BufferedWriter(fw);

            } catch (IOException e) {
                e.printStackTrace();
            }
            FileInputStream file = new FileInputStream(new File("C:/FDC-Site-Search-Queries-and-Alt-Title-Tags-Aug2013.xlsx"));

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook (file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Get iterator to all the rows in current sheet


            Iterator<Row> rowIterator = sheet.iterator();
            String value;
            String searchTerm = null;
            String override = null;
            while(rowIterator.hasNext()){
            //Get iterator to all cells of current row
                Row row =   rowIterator.next();
                int rowNum = row.getRowNum();
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell =   cellIterator.next();
                    //System.out.println(cell.getCellType());
                    switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            int columnIndex =  cell.getColumnIndex();
                            if(columnIndex == 0){
                                 searchTerm = value;
                            }else if(columnIndex == 1){
                                 override = value;
                                 String delsql = "delete from search_metadata_override where keyword = '"+searchTerm+"';\n";
                                 bw.write(delsql);
                                 String sql = "insert into search_metadata_override (keyword , page_title) values ('"+searchTerm+"','"+override+"');\n";
                                 bw.write(sql);
                            }
                            //checkUrl(url, sponsorCode);
                            System.out.print(value + "\t\t");
                            break;
                    }

                }
                 System.out.print("\n");
            }

            file.close();

        }  catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally{
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public static BufferedWriter getBufferedWriter(String fileName) {
        BufferedWriter bw = null;
		try {

			File file = new File(fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

		} catch (IOException e) {
			e.printStackTrace();
		}

        return  bw;
	}
}
