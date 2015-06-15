package com.aranin.spring.lucene;

import com.aranin.spring.vo.BookVO;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 6/17/13
 * Time: 10:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyWriterManager {
    LuceneUtil luceneUtil = null;
    String indexDir = null;
    public MyWriterManager(String indexDir){
        this.indexDir = indexDir;
        this.luceneUtil = new LuceneUtil(indexDir);
    }
    public static void main(String[] args){
        MyWriterManager myWriterManager = new MyWriterManager("D:/samayik/mydemoindex");
        try {

            /**
             * Create the index
             */
            myWriterManager.luceneUtil.createIndex();

            /**
             * Create a writer. Below method creates a writer and sets the writer variable of the class
             */

            myWriterManager.luceneUtil.createIndexWriter();
            /**
             * add data to index.
             */
           List<BookVO> bookList = getBookList();
            for (BookVO bookVO : bookList) {
              myWriterManager.addBookToIndex(bookVO);
            }


            myWriterManager.luceneUtil.closeWriter();



        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private static List<BookVO> getBookList() {
        List<BookVO> bookList = new ArrayList<BookVO>();

        BookVO bookVO = new BookVO();
        bookVO.setBook_name("Foundation");
        bookVO.setBook_author("Issac Asimov");
        bookVO.setCategory("Science Fiction");
        bookVO.setNumpages(300);
        bookVO.setPrice(200);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("Foundation and Empire");
        bookVO.setBook_author("Issac Asimov");
        bookVO.setCategory("Science Fiction");
        bookVO.setNumpages(350);
        bookVO.setPrice(220);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("Second Foundation");
        bookVO.setBook_author("Issac Asimov");
        bookVO.setCategory("Science Fiction");
        bookVO.setNumpages(400);
        bookVO.setPrice(240);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("Foundation at Bay");
        bookVO.setBook_author("Issac Asimov");
        bookVO.setCategory("Science Fiction");
        bookVO.setNumpages(680);
        bookVO.setPrice(280);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("Forward the Foundation");
        bookVO.setBook_author("Issac Asimov");
        bookVO.setCategory("Science Fiction");
        bookVO.setNumpages(550);
        bookVO.setPrice(260);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("Foundation and Earth");
        bookVO.setBook_author("Issac Asimov");
        bookVO.setCategory("Science Fiction");
        bookVO.setNumpages(590);
        bookVO.setPrice(300);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("False Impression");
        bookVO.setBook_author("Jeffrey Archer");
        bookVO.setCategory("Detective");
        bookVO.setNumpages(600);
        bookVO.setPrice(100);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("Shall We Tell The President?");
        bookVO.setBook_author("Jeffrey Archer");
        bookVO.setCategory("Detective");
        bookVO.setNumpages(450);
        bookVO.setPrice(120);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("The Eleventh Commandment");
        bookVO.setBook_author("Jeffrey Archer");
        bookVO.setCategory("Detective");
        bookVO.setNumpages(305);
        bookVO.setPrice(110);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("The Sky is Falling");
        bookVO.setBook_author("Sidney Sheldon");
        bookVO.setCategory("Detective");
        bookVO.setNumpages(550);
        bookVO.setPrice(260);
        bookList.add(bookVO);

        bookVO = new BookVO();
        bookVO.setBook_name("Dark Moon");
        bookVO.setBook_author("David Gemmel");
        bookVO.setCategory("Fantasy");
        bookVO.setNumpages(590);
        bookVO.setPrice(600);
        bookList.add(bookVO);


        return bookList;
    }

    public void addBookToIndex(BookVO bookVO) throws Exception {
        Document document = new Document();
        document.add(new StringField("title", bookVO.getBook_name(), Field.Store.YES));
        document.add(new StringField("author", bookVO.getBook_author(), Field.Store.YES));
        document.add(new StringField("category", bookVO.getCategory(), Field.Store.YES));
        document.add(new IntField("numpage", bookVO.getNumpages(), Field.Store.YES));
        document.add(new FloatField("price", bookVO.getPrice(), Field.Store.YES));
        IndexWriter writer =  this.luceneUtil.getIndexWriter();
        writer.addDocument(document);
        writer.commit();
    }
}
