package com.jbksoft.hbase.demo.client;

import au.com.bytecode.opencsv.CSVReader;

import java.io.StringReader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        String oneReviewCSV =           "\"2014-03-31\",\"avon-es\",\"catalog:avon-es::product::33259\",\"33259\",\"avon\",\"33259\",\"catalog:avon::product::33259\",\"8970233\",\"5\",\"review:avon:6c062cd7-8159-5146-be5f-6e8dd2aa9216\"";
        String oneNativeReviewCSV =     "\"review:avon-es:ef5c352d-40de-5705-8152-42297ec6951b\",\"catalog:avon-es::product::33259\",\"APPROVED\",\"Deja los ojos divinos luminosos me fascino\",\"5\",\"1264485606000\",\"avon-es\",\"13319194\"";
        String oneSyndicatedReviewCSV = "\"2014-03-31\",\"catalog:avon-es::product::33259\",\"avon-es\",\"33259\",\"5\",\"clarisonic\",\"Clarisonic\",\"20072\",\"review:clarisonic:16976260\",\"1327849161\"";
        App app = new App();
        app.printCSV(oneReviewCSV);
        app.printCSV(oneNativeReviewCSV);
        app.printCSV(oneSyndicatedReviewCSV);
    }

    public void printCSV(String csvLine) throws Exception {

        CSVReader csvReader = new CSVReader( new StringReader(csvLine), ',', '"', '\\');
        String [] csvCells = csvReader.readNext();
        for(String str : csvCells)
            System.out.print(str + ' ');
        System.out.println(csvCells[0]);
    }
}
