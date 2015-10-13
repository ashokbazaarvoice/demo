package com.abc.copy.streams;

import org.apache.hadoop.fs.FileSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 9/24/14
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class CopyBytes {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("xanadu.txt");
            out = new FileOutputStream("outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
