package com.frank.entity.book;

import java.math.BigDecimal;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private BookGenre genre;
    private BigDecimal actualPrice;
    private BookShelf shelf;

    public Object getId() {
        return getIsbn();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BookShelf getShelf() {
        return shelf;
    }

    public void setShelf(BookShelf shelf) {
        this.shelf = shelf;
    }
}
