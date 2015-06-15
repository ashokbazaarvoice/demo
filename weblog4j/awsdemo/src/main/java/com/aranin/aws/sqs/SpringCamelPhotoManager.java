package com.aranin.aws.sqs;

import com.aranin.aws.s3.PhotoFile;
import org.apache.camel.CamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 5/14/13
 * Time: 11:30 AM
 * To change this template use File | Settings | File Templates.
 */

public class SpringCamelPhotoManager {
    public void startCamelServer() {
    try {
        ApplicationContext springcontext = new FileSystemXmlApplicationContext("D:/samayik/awsdemo/src/main/resources/camelconfig.xml");
        CamelContext context = springcontext.getBean("sqsContext", CamelContext.class);
        context.start();
        Thread.sleep(10000);
        context.stop();

    } catch ( Exception e ) {
        System.out.println(e);
    }

    }

    public void sendMessage(){
        AWSSimpleQueueServiceUtil awssqsUtil =   AWSSimpleQueueServiceUtil.getInstance();
        /**
         * 1. get the url for your photo queue
         */
        String queueUrl  = awssqsUtil.getQueueUrl(awssqsUtil.getQueueName());
        System.out.println("queueUrl : " + queueUrl);

        /**
         * 2. Add a photo to the queue to be processed
         */

        PhotoFile photo = new PhotoFile();
        photo.setImagePath("d:/vids");
        photo.setOrigName("Dock.jpg");
        photo.setTargetName("dock_thumb.jpg");

        /**
         * 3. set the photofile in queue for processing
         */

         awssqsUtil.sendMessageToQueue(queueUrl, photo.toString());
    }

    public static void main(String[] args){
        SpringCamelPhotoManager springCamelPhotoManager = new SpringCamelPhotoManager();

         /**
         * send a message
         */

        springCamelPhotoManager.sendMessage();



         /**
         * start camel as standalone and keep on receiving and processing messages asynchrounously
         */

         springCamelPhotoManager.startCamelServer();

    }

}

