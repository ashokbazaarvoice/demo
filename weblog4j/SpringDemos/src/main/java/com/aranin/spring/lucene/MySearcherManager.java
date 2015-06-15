package com.aranin.spring.lucene;

import com.aranin.spring.vo.BookVO;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 6/17/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class MySearcherManager {
    LuceneUtil luceneUtil = null;
    String indexDir = null;
    public MySearcherManager(String indexDir){
        this.indexDir = indexDir;
        this.luceneUtil = new LuceneUtil(indexDir);
    }
    public static void main(String[] args){
        MySearcherManager mySearcherManager = new MySearcherManager("D:/samayik/mydemoindex");
        try {
            /**
             * create an index searcher. This method creates an IndexSearcher and sets it to instance variable
             * indexSearcher.
             */

             mySearcherManager.luceneUtil.createIndexSearcher();
            IndexSearcher indexSearcher =   mySearcherManager.luceneUtil.getIndexSearcher();

            /**
             * now lets do some searches
             */

            /**
             * field search
             * 1. get all books with author = Issac Asimov
             */

            List<BookVO> bookList =   mySearcherManager.getBooksByField("Issac Asimov", "author", indexSearcher);
            System.out.println("numResults : " + bookList.size());
            for (BookVO bookVO : bookList) {
                System.out.println(bookVO.getBook_name());
                System.out.println(bookVO.getBook_author());
                System.out.println(bookVO.getCategory());
                System.out.println(bookVO.getNumpages());
                System.out.println(bookVO.getPrice());
            }

            /**
             * wildcard search
             * 1. get all books with author conataing Jeffrey
             */

            bookList =   mySearcherManager.getWildBooksByField("Jeffrey*", "author", indexSearcher);
            System.out.println("numResults : " + bookList.size());
            for (BookVO bookVO : bookList) {
                System.out.println(bookVO.getBook_name());
                System.out.println(bookVO.getBook_author());
                System.out.println(bookVO.getCategory());
                System.out.println(bookVO.getNumpages());
                System.out.println(bookVO.getPrice());
            }

            /**
             * phrase search
             * 1. get all books with title containing  moon
             */

            bookList =   mySearcherManager.getPhraseBooksByField("moon", "title", indexSearcher);
            System.out.println("numResults : " + bookList.size());
            for (BookVO bookVO : bookList) {
                System.out.println(bookVO.getBook_name());
                System.out.println(bookVO.getBook_author());
                System.out.println(bookVO.getCategory());
                System.out.println(bookVO.getNumpages());
                System.out.println(bookVO.getPrice());
            }


        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public List<BookVO> getBooksByField(String value, String field, IndexSearcher indexSearcher){
        List<BookVO> bookList = new ArrayList<BookVO>();
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
        QueryParser parser = new QueryParser(Version.LUCENE_43, field, analyzer);

        try {
            BooleanQuery query = new BooleanQuery();
            query.add(new TermQuery(new Term(field, value)), BooleanClause.Occur.MUST);


           //Query query = parser.Query(value);
            /**
             * Get
             */
            int numResults = 100;
            ScoreDoc[] hits =   indexSearcher.search(query,numResults).scoreDocs;
            for (int i = 0; i < hits.length; i++) {
                Document doc = indexSearcher.doc(hits[i].doc);
                bookList.add(getBookVO(doc));
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return bookList;
    }

    public List<BookVO> getWildBooksByField(String value, String field, IndexSearcher indexSearcher){
        List<BookVO> bookList = new ArrayList<BookVO>();
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
        QueryParser parser = new QueryParser(Version.LUCENE_43, field, analyzer);

        try {
            BooleanQuery query = new BooleanQuery();
            query.add(new WildcardQuery(new Term(field, value)), BooleanClause.Occur.SHOULD);


           //Query query = parser.Query(value);
            /**
             * Get
             */
            int numResults = 100;
            ScoreDoc[] hits =   indexSearcher.search(query,numResults).scoreDocs;
            for (int i = 0; i < hits.length; i++) {
                Document doc = indexSearcher.doc(hits[i].doc);
                bookList.add(getBookVO(doc));
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return bookList;
    }


     public List<BookVO> getPhraseBooksByField(String value, String field, IndexSearcher indexSearcher){
        List<BookVO> bookList = new ArrayList<BookVO>();
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
        QueryParser parser = new QueryParser(Version.LUCENE_43, field, analyzer);

        try {
            PhraseQuery query = new PhraseQuery();
            query.add(new Term(field,value));


           //Query query = parser.Query(value);
            /**
             * Get
             */
            int numResults = 100;
            ScoreDoc[] hits =   indexSearcher.search(query,numResults).scoreDocs;
            for (int i = 0; i < hits.length; i++) {
                Document doc = indexSearcher.doc(hits[i].doc);
                bookList.add(getBookVO(doc));
            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return bookList;
    }

    public BookVO getBookVO(Document doc){
        BookVO bookVO = new BookVO();
        bookVO.setBook_name(doc.get("title"));
        bookVO.setBook_author(doc.get("author"));
        bookVO.setCategory(doc.get("category"));
        bookVO.setNumpages(Integer.parseInt(doc.get("numpage")));
        bookVO.setPrice(Float.parseFloat(doc.get("price")));

        return bookVO;
    }
}
