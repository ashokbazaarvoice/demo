package com.aranin.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.aranin.aws.s3.PhotoFile;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 4/12/13
 * Time: 3:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class CamelPhotoManager {
    public void asyncProcess() {
    try {
    // create CamelContext
        SimpleRegistry registry = new SimpleRegistry();
        AWSSimpleQueueServiceUtil awssqsUtil =   AWSSimpleQueueServiceUtil.getInstance();
        AmazonSQS sqsClient =  awssqsUtil.getAWSSQSClient();
        registry.put("amazonSQSClient" , sqsClient);
        CamelContext context = new DefaultCamelContext(registry);

        // add our route to the CamelContext
        context.addRoutes(new MySQSRouterBuilder());


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
        CamelPhotoManager camelPhotoManager = new CamelPhotoManager();

         /**
         * send a message
         */

        camelPhotoManager.sendMessage();



         /**
         * start camel as standalone and keep on receiving and processing messages asynchrounously
         */

         camelPhotoManager.asyncProcess();

    }

}
