package com.jbksoft.oozie.client;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.WorkflowAction;
import org.apache.oozie.client.WorkflowJob;
import org.apache.oozie.local.LocalOozie;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/15/15
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LocalOozieExample {
    public static void main(String[] args) {
        LocalOozieExample example = new LocalOozieExample();
        System.exit(example.execute(args));
    }

    private int execute(String... args) {
        if (args.length != 2) {
            System.out.println();
            System.out.println("Expected parameters: <WF_APP_HDFS_URI> <WF_PROPERTIES>");
            return -1;
        }

        String appUri = args[0];
        String propertiesFilePath = args[1];
        if (propertiesFilePath != null) {
            URL defaultPropsFile = getClass().getResource("workflow.xml");
            File propFile = new File(propertiesFilePath);
            if (!propFile.exists()) {
                System.out.println();
                System.out.println("Specified properties file don't exists: " + propFile);
                System.out.println("Use default workflow.xml instead.");
                propertiesFilePath = defaultPropsFile.toString();
            }

            if (!propFile.isFile()) {
                System.out.println();
                System.out.println("Specified properties file is not a file: " + propFile);
                System.out.println("Use default workflow.xml instead.");
                propertiesFilePath = defaultPropsFile.toString();
            }
        }

        try {
            // Start local oozie
            LocalOozie.start();
            // Get a OozieClient for local Oozie
            OozieClient oozieClient = LocalOozie.getClient();

            // Create a workflow job configuration and set the workflow application path
            Properties conf = oozieClient.createConfiguration();
            conf.setProperty(OozieClient.APP_PATH, appUri + File.separator + "workflow.xml");
            // Load additional workflow job parameters from properties file
            if (propertiesFilePath != null) {
                conf.load(new FileInputStream(propertiesFilePath));
            }

            // Submit and start workflow job
            String jobId = oozieClient.run(conf);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Workflow job submitted");

            // Wait until the workflow job finishes printing the status every 10 seconds
            while (oozieClient.getJobInfo(jobId).getStatus() == WorkflowJob.Status.RUNNING) {
                System.out.println("Workflow job running...");
                printWorkflowInfo(oozieClient.getJobInfo(jobId));
                TimeUnit.SECONDS.sleep(10);
            }

            // Print the final status of the workflow job
            System.out.println("Workflow job completed.");
            printWorkflowInfo(oozieClient.getJobInfo(jobId));

            return (oozieClient.getJobInfo(jobId).getStatus() == WorkflowJob.Status.SUCCEEDED) ? 0 : -1;
        } catch (Exception e) {
            System.err.println("Job executing error: " + Arrays.toString(e.getStackTrace()));
            return -1;
        } finally {
            LocalOozie.stop();
        }
    }

    private static void printWorkflowInfo(WorkflowJob job) {
        System.out.println("=============START of WORKFLOW INFO============");
        System.out.println("Application Path: " + job.getAppPath());
        System.out.println("Application Name: " + job.getAppName());
        System.out.println("Application Status: " + job.getStatus());
        System.out.println("Application Actions: ");
        for (WorkflowAction action : job.getActions()) {
            System.out.println(MessageFormat.format("\tName: {0}\tType: {1}\tStatus: {2}",
                    action.getName(), action.getType(), action.getStatus()));
        }
        System.out.println("==============END of WORKFLOW INFO=============");
    }
}
