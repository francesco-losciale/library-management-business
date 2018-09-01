package com.frank.book;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Book> bookList = new ArrayList<>();

    public void add(BookCollection bookCollection) {
        bookCollection.stream().forEach((book) -> bookList.add(book));
        bookCollection.setOrdered(true);
    }
}
