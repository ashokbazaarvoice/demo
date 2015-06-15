package com.aranin.spring.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 8/23/13
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class SponsorCodeCheck {
    public static void main(String[] args){
        String url = "";
        String sponsorCode = "FC_TG_TURKEY";
        try{
            FileInputStream file = new FileInputStream(new File("C:\\FC_2013_CODES_TGTURKEY.xlsx"));

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook (file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();

            while(rowIterator.hasNext()){
            //Get iterator to all cells of current row
                Row row =   rowIterator.next();
                int rowNum = row.getRowNum();
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell =   cellIterator.next();

                    switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            //System.out.print(cell.getBooleanCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            //System.out.print(cell.getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            if(cell.getStringCellValue().indexOf("http://") != -1){
                                url = cell.getStringCellValue();
                                checkUrl(url, sponsorCode);
                                //System.out.print(cell.getStringCellValue() + "\t\t");
                            }

                            break;
                    }

                }
                 System.out.print("\n");
            }

            file.close();

        }  catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void checkUrl(String urlString, String sponsorCode){
        String matcher = "mdManager.addParameter(\"Sponsorship\",\"" + sponsorCode +"\");";
        URL url = null;
        try {
            url = new URL(urlString);


        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;

        boolean present = true;
        while ((inputLine = in.readLine()) != null) {

            if(inputLine.indexOf("mdManager.addParameter(\"Sponsorship\"") != -1){
                if(inputLine.indexOf(sponsorCode) == -1){
                   present  = false;
                }
                System.out.print(urlString + " : sponsor code present : " + present);
                break;
            }



        }

        in.close();
        }catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }



    }
}
