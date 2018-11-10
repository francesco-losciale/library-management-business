package com.frank.entity.book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BookCollection {

    private List<Book> collection = new ArrayList<Book>();

    public BookCollection(){
    }

    public BookCollection(Book... collection){
        Arrays.stream(collection).forEach((book) -> this.collection.add(book));
    }

    public void add(Book book) {
        collection.add(book);
    }

    public boolean contains(Book book) {
        return collection.contains(book);
    }

    public Book seek(Book book) {
        for (Book currentBook : collection) {
            if (currentBook.equals(book)) {
                return book;
            }
        }
        throw new RuntimeException("Book not found");
    }

    public void move(Book book, BookCollection bookCollection) {
        final boolean remove = this.collection.remove(book);
        if (remove) {
            bookCollection.add(book);
        } else {
            throw new RuntimeException("No occurrence of " + book + " was found");
        }
    }

    public static Book seek(Book book, BookCollection... collections) {
        for (BookCollection bookCollection : collections) {
            if (bookCollection.seek(book) != null) {
                return bookCollection.seek(book);
            }
        }
        throw new RuntimeException("Book not found");
    }

    public List<Book> asList() {
        return new ArrayList<>(collection);
    }
}
