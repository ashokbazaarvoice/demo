package com.aranin.aws.sqs;

import com.aranin.aws.s3.PhotoProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.StringTokenizer;


/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 4/12/13
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class MySQSRouterBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        try{
            //Properties properties = new Properties();
            //properties.load(new FileInputStream("D:/samayik/adkonnection/src/main/resources/AwsCredentials.properties"));
            String sqs = "aws-sqs://PhotoQueue?amazonSQSClient=#amazonSQSClient";
            from( sqs).process(new Processor() {
                public void process(Exchange exchange)
                        throws Exception {

                    String messagestring = exchange.getIn().toString();
                    System.out.println("messagestring : " + messagestring);
                    StringTokenizer photoTokenizer = new StringTokenizer(messagestring, ",");
                    String source = null;
                    String target = null;
                    String path = null;

                    source = photoTokenizer.nextToken();
                    source = source.substring("Message: ".length());
                    System.out.println("source : " + source);
                    target = photoTokenizer.nextToken();
                    path = photoTokenizer.nextToken();
                    System.out.println("source : " + source);
                    System.out.println("target : " + target);
                    System.out.println("path : " + path);
                    /**
                     * generate thumbmail within 150*150 container
                     */
                    PhotoProcessor.generateImage(path, source, target, 150);

                }
            });

        }catch(Exception e){

        }
    }


}
