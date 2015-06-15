package com.aranin.spring;

import com.aranin.spring.dao.BookDAO;
import com.aranin.spring.dao.CachedBookDAO;
import com.aranin.spring.dao.impl.BookDAOImpl;
import com.aranin.spring.vo.BookVO;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 5/27/13
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpringMemcachedDemo {
    public static Properties props = new Properties();
    public static void main(String[] args){
        try {

            PropertyConfigurator.configure("D:/samayik/SpringDemos/src/main/resources/log4j.properties");
		    SpringMemcachedDemo.loadConfigProperties("D:/samayik/SpringDemos/src/main/resources/log4j.properties");
            ApplicationContext springcontext = new FileSystemXmlApplicationContext("D:/samayik/SpringDemos/src/main/resources/springcache.xml");
            //non cached dao
            BookDAO bookDAO = (BookDAOImpl)springcontext.getBean("bookDAO");

            // cache enabled dao
            CachedBookDAO cachedBookDAO =  (CachedBookDAO)springcontext.getBean("cachedBookDAO");

            // insert books - Call once to generate some data and put it in cache
            //SpringMemcachedDemo.insertBooks(cachedBookDAO);

            BookVO bookVO = null;
            //get from DB   - note hibernate query getting called in the system out
            bookVO = bookDAO.get(1);

            System.out.println(bookVO.getBook_name());
            System.out.println(bookVO.getBook_author());

            //get from cache - please note that on first hit you will get the value from db

            bookVO = cachedBookDAO.get(6);

            System.out.println(bookVO.getBook_name());
            System.out.println(bookVO.getBook_author());


            //cacheevict demo
            bookVO = cachedBookDAO.get(6);
            System.out.println(bookVO.getBook_name());
            System.out.println(bookVO.getBook_author());
            bookVO.setBook_name("Foundation & Earth");
            cachedBookDAO.update(bookVO);

            //first hit after cacheevict - notice query printed in console

            bookVO = cachedBookDAO.get(6);
            System.out.println(bookVO.getBook_name());
            System.out.println(bookVO.getBook_author());

            //second hit query not fired.

            bookVO = cachedBookDAO.get(6);
            System.out.println(bookVO.getBook_name());
            System.out.println(bookVO.getBook_author());



        } catch ( Exception e ) {
            System.out.println(e);
        }finally {
            System.exit(0);
        }
    }

    public static void loadConfigProperties(String configFilePath){
		try {
			FileInputStream in = new FileInputStream(configFilePath);
			props.load(in);
			in.close();
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

    public static void insertBooks(CachedBookDAO cachedBookDAO) throws Exception {
           BookVO bookVO = new BookVO();
           bookVO.setBook_name("Foundation");
           bookVO.setBook_author("Issac Asimov");
           bookVO.setCategory("Science Fiction");
           bookVO.setNumpages(300);
           bookVO.setPrice(200);

           cachedBookDAO.create(bookVO);

           bookVO.setBook_name("Foundation and Empire");
           bookVO.setBook_author("Issac Asimov");
           bookVO.setCategory("Science Fiction");
           bookVO.setNumpages(350);
           bookVO.setPrice(220);

           cachedBookDAO.create(bookVO);

           bookVO.setBook_name("Second Foundation");
           bookVO.setBook_author("Issac Asimov");
           bookVO.setCategory("Science Fiction");
           bookVO.setNumpages(400);
           bookVO.setPrice(240);

           cachedBookDAO.create(bookVO);


           bookVO.setBook_name("Foundation at Bay");
           bookVO.setBook_author("Issac Asimov");
           bookVO.setCategory("Science Fiction");
           bookVO.setNumpages(680);
           bookVO.setPrice(280);

           cachedBookDAO.create(bookVO);


           bookVO.setBook_name("Forward the Foundation");
           bookVO.setBook_author("Issac Asimov");
           bookVO.setCategory("Science Fiction");
           bookVO.setNumpages(550);
           bookVO.setPrice(260);

           cachedBookDAO.create(bookVO);

           bookVO.setBook_name("Foundation and Earth");
           bookVO.setBook_author("Issac Asimov");
           bookVO.setCategory("Science Fiction");
           bookVO.setNumpages(590);
           bookVO.setPrice(300);

           cachedBookDAO.create(bookVO);
    }
}
