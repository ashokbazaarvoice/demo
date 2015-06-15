package com.bazaarvoice.hadoop;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 6/12/14
 * Time: 8:19 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
public class PutMerge {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem hdfsOut  = FileSystem.get(conf);
        FileSystem hdfsIn = FileSystem.getLocal(conf);
        Path inputDir = new Path(args[0]);
        Path hdfsFile = new Path(args[1]);

        try {
            FileStatus[] inputFiles = hdfsIn.listStatus(inputDir);
            FSDataOutputStream out = hdfsOut.create(hdfsFile);

            for (int i=0; i<inputFiles.length; i++) {
                System.out.println(inputFiles[i].getPath().getName());
                FSDataInputStream in = hdfsIn.open(inputFiles[i].getPath());
                byte buffer[] = new byte[256];
                int bytesRead = 0;
                while( (bytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, bytesRead);
                }
                in.close();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}