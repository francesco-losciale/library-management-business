package com.frank.context.book;

import com.frank.capabilities.Dehydrator;
import com.frank.capabilities.Hydratable;
import com.frank.capabilities.Hydrator;

import java.math.BigDecimal;

public class Book implements Hydratable {

    private String title;
    private String author;
    private String isbn;
    private BookGenre genre;
    private BigDecimal actualPrice;

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

    @Override
    public void hydrate(Hydrator hydrator) {
        hydrator.hydrate(this);
    }

    @Override
    public void dehydrate(Dehydrator dehydrator) {
        dehydrator.dehydrate(this);
    }
}
