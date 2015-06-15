package com.aranin.spring.dao.impl;


import com.aranin.spring.dao.CachedBookDAO;
import com.aranin.spring.vo.BookVO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 7/16/12
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class CachedBookDAOImpl extends HibernateDaoSupport implements CachedBookDAO {
    protected final Logger log = LoggerFactory.getLogger(CachedBookDAO.class);

    SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

    @Override
    @Cacheable(value = "defaultCache", key = "new Integer(#book_id).toString().concat('.BookVO')")
    public BookVO get(int book_id) throws Exception {
        BookVO bookVO = null;
		try{
			Query query = getSession().createQuery("from BookVO bookVO where bookVO.book_id=:book_id");
			query.setLong("book_id", book_id);
			bookVO =  (BookVO)query.uniqueResult();
		}catch(HibernateException he){
			log.error("Error in finding a bookVO : " + he);
            throw new Exception("Error in finding adPicVO by book_id for book_id : " + bookVO, he);
		}
		return bookVO;
    }

    @Override
    @CachePut(value = "defaultCache", key = "new Integer(#bookVO.book_id).toString().concat('.BookVO')")
    public BookVO create(BookVO bookVO) throws Exception {
        try{
			getSession().save(bookVO);
			getSession().flush();
		}catch(HibernateException he){
			log.error("Error in inserting bookVO : " + he);
            throw new Exception("Error in inserting bookVO", he);
		}

		return bookVO;
    }

    @Override
    @CacheEvict(value = "defaultCache", key = "new Integer(#bookVO.book_id).toString().concat('.BookVO')")
    public BookVO update(BookVO bookVO) throws Exception {
        try{
            Query query = getSession().createQuery("update BookVO bookVO set bookVO.book_name=:book_name, bookVO.book_author=:book_author,bookVO.category=:category,bookVO.numpages=:numpages,bookVO.price=:price " +
                                                   "where bookVO.book_id=:book_id");
            query.setString("book_name", bookVO.getBook_name());
            query.setString("book_author", bookVO.getBook_author());
            query.setString("category", bookVO.getCategory());
            query.setInteger("numpages", bookVO.getNumpages());
            query.setFloat("price", bookVO.getPrice());
			query.setLong("book_id", bookVO.getBook_id());
            query.executeUpdate();
		}catch(HibernateException he){
			log.error("Error in updating bookVO : " + he);
            throw new Exception("Error in updating bookVO", he);
		}

		return bookVO;
    }

    @Override
    @CacheEvict(value = "defaultCache", key = "new Integer(#bookVO.book_id).toString().concat('.BookVO')")
    public boolean delete(BookVO bookVO) throws Exception {
        boolean deleteStatus = true;
        try{
			Query query = getSession().createQuery("delete from BookVO bookVO " +
                                                   "where bookVO.book_id=:book_id");
            query.setLong("book_id", bookVO.getBook_id());
            int status = query.executeUpdate();
            if(status == 0){
              deleteStatus = false;
            }
		}catch(HibernateException he){
            deleteStatus = false;
			log.error("Error in deleting BookVO : " + he);
            throw new Exception("Error in deleting BookVO", he);
		}
        return deleteStatus;
    }

    @Override
    @Cacheable(value = "defaultCache", key = "#author.concat('.BookVOList')")
    public List<BookVO> getList(String author) throws Exception {
        List<BookVO> bookList = null;
		try{
			Query query = getSession().createQuery("from BookVO bookVO where bookVO.book_author=:author");
            query.setString("author", author);
			bookList =  (List<BookVO>)query.list();
		}catch(HibernateException he){
			log.error("Error in finding a bookList : " + he);
            throw new Exception("Error in finding a bookList ", he);
		}
		return bookList;
    }
}
