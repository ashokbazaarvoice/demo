package com.aranin.aws.sqs;

import org.apache.camel.builder.RouteBuilder;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 5/14/13
 * Time: 11:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class SQSBeanRouterBuilder extends RouteBuilder{
     @Override
    public void configure() throws Exception {
        try{

            System.out.println("SQSBeanRouterBuilder");
            Properties properties = new Properties();
            properties.load(new FileInputStream("D:/samayik/awsdemo/src/main/resources/AwsCredentials.properties"));
            String sqs = "aws-sqs://PhotoQueue?accessKey="+properties.getProperty("accessKey") + "&secretKey=" + properties.getProperty("secretKey") + "&amazonSQSEndpoint=https://sqs.ap-southeast-1.amazonaws.com";
            //SimpleRegistry registry = new SimpleRegistry();
            //AWSSimpleQueueServiceUtil awssqsUtil =   AWSSimpleQueueServiceUtil.getInstance();
            //AmazonSQS sqsClient =  awssqsUtil.getAWSSQSClient();
            //registry.put("sqsClient" , sqsClient);
            //String sqs = "aws-sqs://PhotoQueue?amazonSQSClient=#sqsClient";

            from( sqs).to("bean:sqsBeanProcessor?method=processSQSMessage");


        }catch(Exception e){

        }
     }
}
