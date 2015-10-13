package com.abc.copy.collections;

import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by ashok.agarwal on 6/18/15.
 */
public class Java7Demo {
    public static void main(String args[]) {

        System.out.println(args.length+" : "+args[0]+" : "+args[0].equals(1));

        int billion = 1_000_000_000; // numeric with underscore

        int mask = 0b01010000101; // binary with representation

        String state = "NEW"; // String based switch

        switch (state) {
            case "NEW":
                System.out.println("Order is in NEW state");
                break;
            case "CANCELED":
                System.out.println("Order is Cancelled");
                break;
            case "REPLACE":
                System.out.println("Order is replaced successfully");
                break;
            case "FILLED":
                System.out.println("Order is filled");
                break;
            default:
                System.out.println("Invalid");
        }

        // try with resource

        try (
                FileInputStream fin = new FileInputStream("info.xml");
                BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        ) {
            if (br.ready()) {
                String line1 = br.readLine();
                System.out.println(line1);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Info.xml is not found");
        } catch (IOException ex) {
            System.out.println("Can't read the file");
        }

        // multiple exception in catch
        try {
            if (1 > 1)
                throw new ClassNotFoundException();
            else
                throw new SQLException();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    // throwing precise Exception
    public void precise() throws ParseException, IOException {
        try {
            new FileInputStream("abc.txt").read();
            new SimpleDateFormat("ddMMyyyy").parse("12-03-2014");
        } catch (Exception ex) {
            System.out.println("Caught exception: " + ex.getMessage());
            throw ex;
        }
    }


}
