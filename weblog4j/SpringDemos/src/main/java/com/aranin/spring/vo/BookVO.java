package com.aranin.spring.vo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by IntelliJ IDEA.
 * User: Niraj Singh
 * Date: 7/16/12
 * Time: 2:37 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "books")
public class BookVO implements java.io.Serializable{

    private int book_id;
    private String book_name;
    private String book_author;
    private String category;
    private int numpages;
    private float price;


    @Id
    @GeneratedValue(strategy = IDENTITY)
	@Column(name = "book_id", unique = true, nullable = false)
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    @Column(name="book_name")
    public String getBook_name() {
        return book_name;
    }


    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    @Column(name="book_author")
    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    @Column(name="category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name="numpages")
    public int getNumpages() {
        return numpages;
    }

    public void setNumpages(int numpages) {
        this.numpages = numpages;
    }

    @Column(name="price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
