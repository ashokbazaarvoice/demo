package com.abc;

//import com.jbksoft.consus.file.FileUtilService;
//import com.jbksoft.magpie.hadoop.hdfs.FileSystems;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 5/19/14
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class FSTest {
    public static void main(String[] args)
            throws Exception {
//        String inputPath = "/Users/ashok.agarwal/dev/MavenSample1/output";
//        Configuration config = new Configuration();
//        FileSystem inputFs = FileSystems.forBasePath(config, inputPath);
//        PathFilter pathFilter = FileUtilService.createFileNamePathFilter("[^_].*");
//        FileStatus[] inputFileStatuses = inputFs.listStatus(new Path(inputPath), pathFilter);
//
//        for (FileStatus status : inputFileStatuses) {
//
//            Path path = status.getPath();
//
//            // for non time based file selection, entry made for the file in the list on the file writer.
//            System.out.println("File : " + path.toUri().toString() + " added to list for processing.");
//
//        }

    }

}
