package com.jbksoft;
import junit.framework.TestCase;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MiniMRCluster;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.oozie.service.Services;
import org.apache.oozie.service.WorkflowAppService;

import org.apache.oozie.util.IOUtils;
import org.apache.oozie.util.ParamChecker;
import org.apache.oozie.util.XLog;
import org.apache.oozie.util.XConfiguration;
//import org.apache.oozie.action.hadoop.DoAs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

import static org.junit.Assert.*;

public class TestLocalOozieExample {
//    private String oozieLocalLog;
//    private String testDir;
//    private FileSystem fileSystem;
//    private Path fsTestDir;
//
//    protected void setUp() throws Exception {
//        super.setUp();
//        File dir = new File(System.getProperty("oozie.test.dir", "/tmp"));
//        dir = new File(dir, "oozietests");
//        dir = new File(dir, getClass().getName());
//        dir = new File(dir, getName());
//        dir.mkdirs();
//        testDir = dir.getAbsolutePath();
//        final Configuration conf = new Configuration();
//        conf.set(WorkflowAppService.HADOOP_USER, getTestUser());
//        conf.set("mapred.job.tracker", getJobTrackerUri());
//        conf.set("fs.default.name", getNameNodeUri());
//        injectKerberosInfo(conf);
//
//// TODO restore this when getting rid of DoAs trick
//
////        if (System.getProperty("oozie.test.kerberos", "off").equals("on")) {
////            Configuration c = new Configuration();
////            c.set("hadoop.security.authentication", "kerberos");
////            UserGroupInformation.setConfiguration(c);
////            String principal = System.getProperty("oozie.test.kerberos.oozie.principal",
////                                                  System.getProperty("user.name") + "/localhost") + "@"
////                    + System.getProperty("oozie.test.kerberos.realm", "LOCALHOST");
////            String defaultFile = new File(System.getProperty("user.home"), "oozie.keytab").getAbsolutePath();
////            String keytabFile = System.getProperty("oozie.test.kerberos.keytab.file", defaultFile);
////            UserGroupInformation.loginUserFromKeytab(principal, keytabFile);
//////            System.setProperty("oozie.service.HadoopAccessorService.kerberos.enabled", "kerberos");
////        }
//
//        Class klass;
//        try {
//            klass = Class.forName("org.apache.oozie.action.hadoop.KerberosDoAs");
//        }
//        catch (ClassNotFoundException ex) {
//            klass = DoAs.class;
//        }
//        DoAs doAs = (DoAs) klass.newInstance();
//        final FileSystem[] fs = new FileSystem[1];
//        doAs.setCallable(new Callable<Void>() {
//            public Void call() throws Exception {
//                Configuration defaultConf = new Configuration();
//                XConfiguration.copy(conf, defaultConf);
//                fs[0] = FileSystem.get(defaultConf);
//                return null;
//            }
//        });
//        doAs.setUser(getTestUser());
//        doAs.call();
//        fileSystem = fs[0];
//
//        Path path = new Path(fileSystem.getWorkingDirectory(), "oozietests/" + getClass().getName() + "/" + getName());
//        fsTestDir = fileSystem.makeQualified(path);
//        System.out.println(XLog.format("Setting FS testcase work dir[{0}]", fsTestDir));
//        fileSystem.delete(fsTestDir, true);
//        if (!fileSystem.mkdirs(path)) {
//            throw new IOException(XLog.format("Could not create FS testcase dir [{0}]", fsTestDir));
//        }
//        oozieLocalLog = System.getProperty("oozielocal.log");
//        System.setProperty("oozielocal.log", "/tmp/oozielocal.log");
//
//        String testCaseDir = getTestCaseDirInternal(this);
//        File file = new File(testCaseDir);
//        delete(file);
//        if (!file.mkdir()) {
//            throw new RuntimeException(XLog.format("could not create path [{0}]", file.getAbsolutePath()));
//        }
//        file = new File(file, "conf");
//        if (!file.mkdir()) {
//            throw new RuntimeException(XLog.format("could not create path [{0}]", file.getAbsolutePath()));
//        }
//        //setting up Oozie HOME and an empty conf directory
//        System.setProperty(Services.OOZIE_HOME_DIR, testCaseDir);
//    }
//
//    protected void delete(File file) throws IOException {
//        ParamChecker.notNull(file, "file");
//        if (file.getAbsolutePath().length() < 5) {
//            throw new RuntimeException(XLog.format("path [{0}] is too short, not deleting", file.getAbsolutePath()));
//        }
//        if (file.exists()) {
//            if (file.isDirectory()) {
//                File[] children = file.listFiles();
//                if (children != null) {
//                    for (File child : children) {
//                        delete(child);
//                    }
//                }
//            }
//            if (!file.delete()) {
//                throw new RuntimeException(XLog.format("could not delete path [{0}]", file.getAbsolutePath()));
//            }
//        }
//    }
//
//    private String getTestCaseDirInternal(TestCase testCase) {
//        ParamChecker.notNull(testCase, "testCase");
//        File dir = new File(System.getProperty("oozie.test.dir", "/tmp"));
//        dir = new File(dir, "oozietests");
//        dir = new File(dir, testCase.getClass().getName());
//        dir = new File(dir, testCase.getName());
//        return dir.getAbsolutePath();
//    }
//
//    protected void tearDown() throws Exception {
//        fileSystem = null;
//        fsTestDir = null;
//        if (oozieLocalLog != null) {
//            System.setProperty("oozielocal.log", oozieLocalLog);
//        }
//        else {
//            System.getProperties().remove("oozielocal.log");
//        }
//        System.getProperties().remove(Services.OOZIE_HOME_DIR);
//    }
//
//    public void testLocalOozieExampleEnd() throws IOException {
//        Path app = new Path(fsTestDir, "app");
//        File props = new File(testDir, "job.properties");
//        IOUtils.copyStream(IOUtils.getResourceAsStream("localoozieexample-wf.xml", -1),
//                fileSystem.create(new Path(app, "workflow.xml")));
//        IOUtils.copyStream(IOUtils.getResourceAsStream("localoozieexample-end.properties", -1),
//                new FileOutputStream(props));
//        assertEquals(0, LocalOozieExample.execute(app.toString(), props.toString()));
//    }
//
//    public void testLocalOozieExampleKill() throws IOException {
//        Path app = new Path(fsTestDir, "app");
//        File props = new File(testDir, "job.properties");
//        IOUtils.copyStream(IOUtils.getResourceAsStream("localoozieexample-wf.xml", -1),
//                fileSystem.create(new Path(app, "workflow.xml")));
//        IOUtils.copyStream(IOUtils.getResourceAsStream("localoozieexample-kill.properties", -1),
//                new FileOutputStream(props));
//        assertEquals(-1, LocalOozieExample.execute(app.toString(), props.toString()));
//    }
//
//    private static MiniDFSCluster dfsCluster = null;
//    private static MiniMRCluster mrCluster = null;
//
//    private static void setUpEmbeddedHadoop() throws Exception {
//        if (dfsCluster == null && mrCluster == null) {
//            if (System.getProperty("hadoop.log.dir") == null) {
//                System.setProperty("hadoop.log.dir", "/tmp");
//            }
//            int taskTrackers = 2;
//            int dataNodes = 2;
//            String oozieUser = getOozieUser();
//            JobConf conf = new JobConf();
//            conf.set("dfs.block.access.token.enable", "false");
//            conf.set("dfs.permissions", "true");
//            conf.set("hadoop.security.authentication", "simple");
//            conf.set("hadoop.proxyuser." + oozieUser + ".hosts", "localhost");
//            conf.set("hadoop.proxyuser." + oozieUser + ".groups", getTestGroup());
//            conf.set("mapred.tasktracker.map.tasks.maximum", "4");
//            conf.set("mapred.tasktracker.reduce.tasks.maximum", "4");
//
//            String [] userGroups = new String[] { getTestGroup() };
//            UserGroupInformation.createUserForTesting(oozieUser, userGroups);
//            UserGroupInformation.createUserForTesting(getTestUser(), userGroups);
//            UserGroupInformation.createUserForTesting(getTestUser2(), userGroups);
//            UserGroupInformation.createUserForTesting(getTestUser3(), new String[] { "users" } );
//
//            dfsCluster = new MiniDFSCluster(conf, dataNodes, true, null);
//            FileSystem fileSystem = dfsCluster.getFileSystem();
//            fileSystem.mkdirs(new Path("/tmp"));
//            fileSystem.mkdirs(new Path("/user"));
//            fileSystem.mkdirs(new Path("/hadoop/mapred/system"));
//            fileSystem.setPermission(new Path("/tmp"), FsPermission.valueOf("-rwxrwxrwx"));
//            fileSystem.setPermission(new Path("/user"), FsPermission.valueOf("-rwxrwxrwx"));
//            fileSystem.setPermission(new Path("/hadoop/mapred/system"), FsPermission.valueOf("-rwx------"));
//            String nnURI = fileSystem.getUri().toString();
//            int numDirs = 1;
//            String[] racks = null;
//            String[] hosts = null;
////            UserGroupInformation ugi = null;
//            mrCluster = new MiniMRCluster(0, 0, taskTrackers, nnURI, numDirs, racks, hosts, null, conf);
//            JobConf jobConf = mrCluster.createJobConf();
//            System.setProperty(OOZIE_TEST_JOB_TRACKER, jobConf.get("mapred.job.tracker"));
//            System.setProperty(OOZIE_TEST_NAME_NODE, jobConf.get("fs.default.name"));
//            Runtime.getRuntime().addShutdownHook(new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        if (mrCluster != null) {
//                            mrCluster.shutdown();
//                        }
//                    }
//                    catch (Exception ex) {
//                        System.out.println(ex);
//                    }
//                    try {
//                        if (dfsCluster != null) {
//                            dfsCluster.shutdown();
//                        }
//                    }
//                    catch (Exception ex) {
//                        System.out.println(ex);
//                    }
//                }
//            });
//        }
//    }

}
