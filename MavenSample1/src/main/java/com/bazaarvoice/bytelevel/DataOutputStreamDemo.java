package com.bazaarvoice.bytelevel;

import java.io.*;


/**
 * Created by ashok.agarwal on 6/5/15.
 *
 * This demo helps to understand the byte level in hadoop writables.
 */
public class DataOutputStreamDemo {
    public static void main(String[] args) throws Exception{

        String filename = "/Users/ashok.agarwal/dev/github/MavenSample1/byteoutput/output.ser";
        String output = "Java Code Geeks - Java Examples";

        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {

            fos = new FileOutputStream(filename);

            dos = new DataOutputStream(fos);

            dos.writeBytes(output);
            dos.writeUTF(output);

            int i = 100;

           /*
            * To write an int value to a file, use
            * void writeInt(int i) method of Java DataOutputStream class.
            *
            * This method writes specified int to output stream as 4 bytes value.
            */

            dos.writeInt(i);

            int ch = 65;

           /*
            * To write a char value to a file, use
            * void writeChar(int ch) method of Java DataOutputStream class.
            *
            * This method writes specified char to output stream as 2 bytes value.
            */

            dos.writeChar(ch);

            /*
            * To determine total number of bytes written to underlying stream, use
            * int size() method.
            *
            */

            int bytesWritten = dos.size();
            System.out.println("Total " + bytesWritten + " bytes are written to stream.");



            dos.close();

        }
        catch (FileNotFoundException fnfe) {
            System.out.println("File not found" + fnfe);
        }
        catch (IOException ioe) {
            System.out.println("Error while writing to file" + ioe);
        }
        finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            }
            catch (Exception e) {
                System.out.println("Error while closing streams" + e);
            }
        }


        int idA = 1;
        String nameA = "City";
        int populationA = 5;
        float tempA = 1.0f;

        int idB = 2;
        String nameB = "S";
        int populationB = 2;
        float tempB = 1.45f;

        fos = new FileOutputStream("/Users/ashok.agarwal/dev/github/MavenSample1/byteoutput/cities.dat");
        dos = new DataOutputStream(fos);

        dos.writeInt(idA);
        dos.writeUTF(nameA);
        dos.writeInt(populationA);
        dos.writeFloat(tempA);

        dos.writeInt(idB);
        dos.writeUTF(nameB);
        dos.writeInt(populationB);
        dos.writeFloat(tempB);

        dos.flush();
        dos.close();

        FileInputStream fis = new FileInputStream("/Users/ashok.agarwal/dev/github/MavenSample1/byteoutput/cities.dat");
        DataInputStream dis = new DataInputStream(fis);

        int cityId = dis.readInt();
        System.out.println("City Id: " + cityId);
        String cityName = dis.readUTF();
        System.out.println("City Name: " + cityName);

        // Note: the data was written in some order and when you mess with order you get garbage.

//        cityId = dis.readInt();
//        System.out.println("City Id: " + cityId);

        int cityPopulation = dis.readInt();
        System.out.println("City Population: " + cityPopulation);
        float cityTemperature = dis.readFloat();
        System.out.println("City Temperature: " + cityTemperature);
    }
}
