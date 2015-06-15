package com.aranin.spring.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 6/14/13
 * Time: 11:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class LuceneUtil {
    private IndexWriter writer = null;
    private String indexDir = null;
    IndexSearcher indexSearcher = null;

    public LuceneUtil(String indexDir) {
        this.indexDir = indexDir;
    }

    public void createIndex() throws Exception {

        boolean create = true;
        File indexDirFile = new File(this.indexDir);
        if (indexDirFile.exists() && indexDirFile.isDirectory()) {
            create = false;
        }

        Directory dir = FSDirectory.open(indexDirFile);
        /**
         * Use by certain classes to match version compatibility across releases of Lucene.
         * WARNING: When changing the version parameter that you supply to components in Lucene,
         * do not simply change the version at search-time, but instead also adjust your
         * indexing code to match, and re-index.
         */

        /**
         *
         */
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

        /**
         * Holds all the configuration that is used to create an IndexWriter.
         * Once IndexWriter has been created with this object, changes to this
         * object will not affect the IndexWriter instance. For that, use
         * LiveIndexWriterConfig that is returned from IndexWriter.getConfig().
         */
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_43, analyzer);


        if (create) {
            // Create a new index in the directory, removing any
            // previously indexed documents:
            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        }

        IndexWriter writer = new IndexWriter(dir, iwc);
        writer.commit();
        writer.close(true);
    }

    public void createIndexWriter() throws Exception {

        boolean create = true;
        File indexDirFile = new File(this.indexDir);

        Directory dir = FSDirectory.open(indexDirFile);
        /**
         * Use by certain classes to match version compatibility across releases of Lucene.
         * WARNING: When changing the version parameter that you supply to components in Lucene,
         * do not simply change the version at search-time, but instead also adjust your
         * indexing code to match, and re-index.
         */

        /**
         *
         */
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);

        /**
         * Holds all the configuration that is used to create an IndexWriter.
         * Once IndexWriter has been created with this object, changes to this
         * object will not affect the IndexWriter instance. For that, use
         * LiveIndexWriterConfig that is returned from IndexWriter.getConfig().
         */
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_43, analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        this.writer = new IndexWriter(dir, iwc);

    }

    public synchronized IndexWriter getIndexWriter() throws Exception {
        if (this.writer == null) {
            createIndexWriter();
        }
        return this.writer;
    }

    public synchronized void closeWriter() throws IOException {
        if (this.writer != null) {
            this.writer.close(true);
        }
    }

    public void createIndexSearcher(){
       IndexReader indexReader = null;
       IndexSearcher indexSearcher = null;
       try{
            File indexDirFile = new File(this.indexDir);
            Directory dir = FSDirectory.open(indexDirFile);
            indexReader  = DirectoryReader.open(dir);
            indexSearcher = new IndexSearcher(indexReader);
       }catch(IOException ioe){
           ioe.printStackTrace();
       }

       this.indexSearcher = indexSearcher;
    }

    public synchronized IndexSearcher getIndexSearcher() throws Exception {
        if (this.indexSearcher == null) {
            createIndexSearcher();
        }
        return this.indexSearcher;
    }


}
