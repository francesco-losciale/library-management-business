package com.frank.usecase;

import com.frank.entity.book.BookCollection;
import com.frank.entity.book.Book;
import com.frank.entity.book.BookGenre;
import com.frank.entity.book.BookShelf;

public class ShelfUseCase {

    public BookShelf newShelf(BookGenre bookGenre, int placesCount) {
        return new BookShelf(placesCount, bookGenre);
    }

    public BookShelf newShelf(int placesCount) {
        return newShelf(null, placesCount);
    }

    public void addBookToShelf(Book book, BookShelf bookShelf) {
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        bookShelf.place(bookCollection);
    }

    public void moveCollectionFromShelfToShelf(BookCollection bookCollection, BookShelf src, BookShelf dest) {
        src.remove(bookCollection);
        dest.place(bookCollection);
    }
}
