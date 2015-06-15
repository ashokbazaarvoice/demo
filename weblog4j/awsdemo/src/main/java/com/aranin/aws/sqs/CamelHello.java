package com.aranin.aws.sqs;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 4/16/13
 * Time: 11:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class CamelHello {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
        public void configure() {
            from("file:d:/vids")
            .to("file:d:/temp");
            }
        });
        context.start();
        Thread.sleep(100000);
        context.stop();
    }
}
