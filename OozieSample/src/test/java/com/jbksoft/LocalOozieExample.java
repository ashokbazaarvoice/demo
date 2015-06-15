package com.jbksoft;
import org.apache.commons.io.FileUtils;
import org.apache.oozie.client.WorkflowJob;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.WorkflowAction;
import org.apache.oozie.local.LocalOozie;
import org.apache.oozie.service.Services;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;
import java.text.MessageFormat;

public class LocalOozieExample {

    public static void main(String... args) throws Exception {
        String arg[] = new String[2];
        arg[0]=new String("hdfs://aagarwal-mbpro.local:8020/apps/OozieSample/");
        arg[1]=new String("/Users/ashok.agarwal/dev/github/OozieSample/src/main/resources/backup/job_bk.properties");
        File oozieHome = new File("target/ooziehome");
        File oozieData = new File(oozieHome, "data");
        System.setProperty(Services.OOZIE_HOME_DIR, oozieHome.getAbsolutePath());
        System.setProperty("oozie.data.dir", oozieData.getAbsolutePath());
        FileUtils.deleteDirectory(oozieHome);
        oozieHome.mkdir();
        System.exit(execute(arg));
    }

    static int execute(String... args) {
        if (args.length != 2) {
            System.out.println();
            System.out.println("Expected parameters: <WF_APP_HDFS_URI> <WF_PROPERTIES>");
            return -1;
        }
        String appUri = args[0];
        String propertiesFile = args[1];
        if (propertiesFile != null) {
            File file = new File(propertiesFile);
            if (!file.exists()) {
                System.out.println();
                System.out.println("Specified Properties file does not exist: " + propertiesFile);
                return -1;
            }
            if (!file.isFile()) {
                System.out.println();
                System.out.println("Specified Properties file is not a file: " + propertiesFile);
                return -1;
            }
        }

        try {

            // start local Oozie
            LocalOozie.start();

            // get a OozieClient for local Oozie
            OozieClient wc = LocalOozie.getClient();

            // create a workflow job configuration and set the workflow application path
            Properties conf = wc.createConfiguration();
            conf.setProperty(OozieClient.APP_PATH, appUri + File.separator + "workflow.xml");
            conf.setProperty("mapreduce.jobtracker.kerberos.principal", "mapred/localhost@LOCALHOST");
            conf.setProperty("dfs.namenode.kerberos.principal", "hdfs/localhost@LOCALHOST");
            // load additional workflow job parameters from properties file
            if (propertiesFile != null) {
                conf.load(new FileInputStream(propertiesFile));
            }

            // submit and start the workflow job
            String jobId = wc.run(conf);
            Thread.sleep(1000);
            System.out.println("Workflow job submitted");

            // wait until the workflow job finishes printing the status every 10 seconds
            while (wc.getJobInfo(jobId).getStatus() == WorkflowJob.Status.RUNNING) {
                System.out.println("Workflow job running ...");
                printWorkflowInfo(wc.getJobInfo(jobId));
                Thread.sleep(10 * 1000);
            }

            // print the final status o the workflow job
            System.out.println("Workflow job completed ...");
            printWorkflowInfo(wc.getJobInfo(jobId));

            return (wc.getJobInfo(jobId).getStatus() == WorkflowJob.Status.SUCCEEDED) ? 0 : -1;
        }
        catch (Exception ex) {
            System.out.println();
            System.out.println(ex.getMessage());
            return -1;
        }
        finally {
            // stop local Oozie
            LocalOozie.stop();
        }
    }

    private static void printWorkflowInfo(WorkflowJob wf) {
        System.out.println("Application Path   : " + wf.getAppPath());
        System.out.println("Application Name   : " + wf.getAppName());
        System.out.println("Application Status : " + wf.getStatus());
        System.out.println("Application Actions:");
        for (WorkflowAction action : wf.getActions()) {
            System.out.println(MessageFormat.format("   Name: {0} Type: {1} Status: {2}", action.getName(),
                    action.getType(), action.getStatus()));
        }
        System.out.println();
    }

}