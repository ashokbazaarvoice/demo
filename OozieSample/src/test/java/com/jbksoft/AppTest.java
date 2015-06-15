package com.jbksoft;

import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.WorkflowJob;
import org.apache.oozie.local.LocalOozie;

import java.io.File;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest

{
    public static void main(String[] args)
            throws Exception {
        try {
            LocalOozie.start();
            final OozieClient wc = LocalOozie.getClient();
            Properties conf = wc.createConfiguration();
//            conf.setProperty(OozieClient.APP_PATH, appPath.toString() + File.separator + "workflow.xml");
//            conf.setProperty(OozieClient.USER_NAME, getTestUser());
//            conf.setProperty(OozieClient.GROUP_NAME, getTestGroup());
//            injectKerberosInfo(conf);
//
//            final String jobId = wc.submit(conf);
//            assertNotNull(jobId);
//
//            WorkflowJob wf = wc.getJobInfo(jobId);
//            assertNotNull(wf);
//            assertEquals(WorkflowJob.Status.PREP, wf.getStatus());
//
//            wc.start(jobId);
//
//            waitFor(1000, new Predicate() {
//                public boolean evaluate() throws Exception {
//                    WorkflowJob wf = wc.getJobInfo(jobId);
//                    return wf.getStatus() == WorkflowJob.Status.SUCCEEDED;
//                }
//            });
//
//            wf = wc.getJobInfo(jobId);
//            assertNotNull(wf);
//            assertEquals(WorkflowJob.Status.SUCCEEDED, wf.getStatus());
        }
        finally {
            LocalOozie.stop();
        }
    }
}
