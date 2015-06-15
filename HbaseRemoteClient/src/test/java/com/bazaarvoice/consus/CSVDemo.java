package com.bazaarvoice.consus;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 7/18/14
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class CSVDemo {

    public static void main(String[] args)
            throws Exception {

        String string = "\"review:avon-es:ef5c352d-40de-5705-8152-42297ec6951b\",\"catalog:avon-es::product::33259\",\"APPROVED\",\"Deja \\\\los ojos \\divinos\n luminosos \"me\" fascino\",\"5\",\"1264485606000\",\"avon-es\",\"13319194\",\"13319144\",\"false\",\"2\"";


        char separatorChar = ',';
        char quoteChar = '"';
        char escapeChar = '"';
        String lineEnd = "";


        CSVReader reader = new CSVReader(new StringReader(string));
        String[] columns = reader.readNext();

//        String[] columns = {"review:avon-es:ef5c352d-40de-5705-8152-42297ec6951b","catalog:avon-es::product::33259","APPROVED","Deja \\\\los ojos \\divinos\n" +
//                " luminosos \"me\" fascino","5","1264485606000","avon-es","13319194","13319144","false","2"};

        System.out.println(Arrays.toString(columns));

        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        csvWriter.writeNext(columns);
        csvWriter.flush();

        System.out.println(stringWriter.toString());
        //new CSVWriter()

    }
}
