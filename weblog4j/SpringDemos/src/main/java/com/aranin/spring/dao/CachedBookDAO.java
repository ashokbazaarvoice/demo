package com.aranin.spring.dao;

import com.aranin.spring.vo.BookVO;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 5/27/13
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CachedBookDAO {

    public BookVO get(int book_id) throws Exception;
    public BookVO create(BookVO bookVO) throws Exception;
    public BookVO update(BookVO bookVO) throws Exception;
    public boolean delete(BookVO bookVO) throws Exception;
    public List<BookVO> getList(String author) throws Exception;
}
