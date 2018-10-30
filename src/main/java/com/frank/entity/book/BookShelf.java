package com.frank.entity.book;

import java.util.ArrayList;
import java.util.List;

public class BookShelf {

    final private List<Book> bookInShelf;

    final private int bookPlaceCount;

    private BookGenre genre;

    public BookShelf(int bookPlaceCount) {
        this.bookPlaceCount = bookPlaceCount;
        this.bookInShelf = new ArrayList<>(bookPlaceCount);
    }

    public BookShelf(int bookPlaceCount, BookGenre genre) {
        this.bookPlaceCount = bookPlaceCount;
        this.bookInShelf = new ArrayList<>(bookPlaceCount);
        this.genre = genre;
    }

    public void place(BookCollection bookCollection) {
        if (bookPlaceCount - bookInShelf.size() < 0) {
            throw new RuntimeException("No space in shelf " + this + " to store the collection " + bookCollection);
        }
        bookCollection.stream().forEach( book -> {
            bookInShelf.add(book);
            book.setGenre(this.genre);
            book.setShelf(this);
        });
    }

    public void remove(BookCollection bookCollection) {
        bookCollection.stream().forEach((book) -> {
            this.bookInShelf.remove(book.getId());
            book.setShelf(null);
        });
    }

    public boolean contains(Book book) {
        return bookInShelf.contains(book);
    }

    public boolean contains(BookCollection bookCollection) {
        return bookCollection.stream().allMatch((book) -> bookInShelf.contains(book) );
    }

}
