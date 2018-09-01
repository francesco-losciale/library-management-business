package com.frank.book;

import java.util.ArrayList;
import java.util.List;

class BookShelf {

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
        });
    }

    public boolean contains(Book book) {
        return bookInShelf.contains(book);
    }

    public boolean contains(BookCollection bookCollection) {
        return bookCollection.stream().allMatch((book) -> bookInShelf.contains(book) );
    }

    public void move(BookCollection bookCollection, BookShelf bookShelfDest) {
        bookCollection.stream().forEach((book) -> {
            this.bookInShelf.remove(book.getId());
            bookShelfDest.place(book);
        });
    }

    private void place(Book book) {
        this.bookInShelf.add(book);
    }
}
