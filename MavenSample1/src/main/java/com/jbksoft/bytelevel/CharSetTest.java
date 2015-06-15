package com.jbksoft.bytelevel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Created by ashok.agarwal on 6/6/15.
 */
public class CharSetTest {
    public static void main(String[] args) throws IOException {
        System.out.println("\u0910");
        for (int i = 2304; i < 2400; i++) {
            String hex = Integer.toHexString(i);
            //PrintStream out = new PrintStream(System.out, true, "UTF-8");
            System.out.print(hex + " = " + (char) i+" ");
        }
        System.out.println("Default Charset=" + Charset.defaultCharset());
        //System.setProperty("file.encoding", "Latin-1");
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("Default Charset in Use=" + getDefaultCharSet());
    }

    private static String getDefaultCharSet() throws IOException{
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        writer.close();
        return enc;
    }
}
