package com.aranin.aws.s3;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 3/13/13
 * Time: 10:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class AWSStorageUtil {
    private BasicAWSCredentials credentials;
    private AmazonS3 s3;
    private String bucketName;
    private static volatile AWSStorageUtil  awsstorageUtil = new  AWSStorageUtil();
    private   AWSStorageUtil(){
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("D:/samayik/adkonnection/src/main/resources/AwsCredentials.properties"));
            this.credentials = new   BasicAWSCredentials(properties.getProperty("accessKey"),
                                                         properties.getProperty("secretKey"));
            this.bucketName = "aranin";
            this.s3 = new AmazonS3Client(this.credentials);

            /*
               You can use this in your web app where    AwsCredentials.properties is stored in web-inf/classes
             */
            //AmazonS3 s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());

        }catch(Exception e){
            System.out.println("exception while creating awss3client : " + e);
        }
    }

    public static AWSStorageUtil getInstance(){
        return awsstorageUtil;
    }

    public static AmazonS3 getAWSClient(){
         return awsstorageUtil.s3;
    }

    public static AmazonS3 getBucketName(){
         return awsstorageUtil.s3;
    }

    public void upload(File file){
        /**
         * key should be unique. an whatever key you set will be used to in url path to access the pic.
         */
        String key = "ads/" + file.getName();
        s3.putObject(this.bucketName, key, file);
    }

    public List<Bucket> listBuckets(){
        for (Bucket bucket : s3.listBuckets()) {
                System.out.println(" bucket name - " + bucket.getName());
        }

        return s3.listBuckets();
    }

    public void getObjectList(){
        System.out.println("Listing objects");
        ObjectListing objectListing = s3.listObjects(new ListObjectsRequest()
                .withBucketName(bucketName)
                .withPrefix("ads"));
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                               "(size = " + objectSummary.getSize() + ")");
        }
    }

    public void getFileFromS3(String key){
        System.out.println("Downloading an object");
        S3Object object = s3.getObject(new GetObjectRequest(this.bucketName, key));
        System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());

    }

    public static void main(String args[]){
        File file = new File("D:/pics/cam/IMG_1201.jpg");
        AWSStorageUtil aWSStorageUtil = AWSStorageUtil.getInstance();
        //aWSStorageUtil.upload(file);
        aWSStorageUtil.getObjectList();

    }




}
