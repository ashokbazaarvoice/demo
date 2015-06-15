package com.bazaarvoice.bytelevel;

import java.io.*;
import java.util.Arrays;

/**
 * Created by ashok.agarwal on 6/5/15.
 */
public class ByteArrayOutputStreamDemo {
    public static void main(String[] args) throws IOException{

        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        for (int i=0;i<10;i++) {
            bout.write((byte) (Math.random() * 100));
        }

        byte[] byteArray = bout.toByteArray();
        for (byte b : byteArray)
            System.out.print(b+" ");

        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        String s = "This is a test.";
        for (int i = 0; i < s.length(); ++i)
            byteOutStream.write(s.charAt(i));
        System.out.println("outstream: " + byteOutStream);
        System.out.println("size: " + byteOutStream.size());


        ByteArrayInputStream inStream;
        inStream = new ByteArrayInputStream(byteOutStream.toByteArray());
        int inBytes = inStream.available();
        System.out.println("inStream has " + inBytes + " available bytes");
        byte inBuf[] = new byte[inBytes];
        int bytesRead = inStream.read(inBuf, 0, inBytes);
        System.out.println(bytesRead + " bytes were read");
        System.out.println("Actual Bytes:"+ Arrays.toString(inBuf));
        System.out.println("They are: " + new String(inBuf));

//        OutputStream outStream = null;
//        byteOutStream = null;
//        try {
//            outStream = new FileOutputStream("/Users/ashok.agarwal/dev/github/MavenSample1/byteoutput/fileNameToSave.ser");
//            byteOutStream = new ByteArrayOutputStream();
//            // writing bytes in to byte output stream
//            byteOutStream.write(byteArray); //data
//            byteOutStream.writeTo(outStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            outStream.close();
//        }
    }

}
