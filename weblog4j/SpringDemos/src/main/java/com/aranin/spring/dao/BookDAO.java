package com.aranin.spring.dao;


import com.aranin.spring.vo.BookVO;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 7/16/12
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BookDAO {

    public BookVO get(int book_id) throws Exception;
    public BookVO create(BookVO bookVO) throws Exception;
    public BookVO update(BookVO bookVO) throws Exception;
    public boolean delete(BookVO bookVO) throws Exception;
    public List<BookVO> getList(String author) throws Exception;
}
