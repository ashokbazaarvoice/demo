package com.aranin.aws.sqs;

import com.aranin.aws.s3.PhotoProcessor;
import org.apache.camel.Exchange;

import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 5/14/13
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class BeanProcessor {

    public void processSQSMessage(Exchange exchange){
        System.out.println("processSQSMessage");
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
}
