package com.frank.book;

import com.frank.book.Book;
import com.frank.book.BookCollection;
import com.frank.book.BookRegister;
import com.frank.book.BookShelf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class BookCollectionTest {

    final BookRegister bookRegister = new BookRegister();

    @Before
    public void init() {
        Book book = new Book();
        book.setTitle("title");
        book.setAuthor("author");
        book.setIsbn("isbn");
        bookRegister.add(book);
    }

    @After
    public void end() {
        bookRegister.clean();
    }

    @Test
    public void testCollectBooksTogether() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        assertTrue(bookCollection.contains(book));
    }

    @Test
    public void testSeekBookInCollection() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        Book result = bookCollection.seek(book);
        assertEquals(result, book);
    }

    @Test
    public void testSeekBookInCollections() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection1 = new BookCollection();
        BookCollection bookCollection2 = new BookCollection();
        bookCollection1.add(book);
        Book result = BookCollection.seek(book, bookCollection1, bookCollection2);
        assertEquals(result, book);
    }

    @Test
    public void testMoveBookToDifferentCollection() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection1 = new BookCollection();
        bookCollection1.add(book);
        BookCollection bookCollection2 = new BookCollection();
        bookCollection1.move(book, bookCollection2);
        assertFalse(bookCollection1.contains(book));
        assertTrue(bookCollection2.contains(book));
    }

    @Test
    public void testMakeBookOutOfOrder() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        bookRegister.setBookAsOutOfOrder(book);
        assertFalse(bookRegister.isBookAvailable(book));
    }

    @Test
    public void testMakeBookAvailable() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        bookRegister.setBookAvailable(book);
        assertTrue(bookRegister.isBookAvailable(book));
    }

    @Test
    public void testPlaceCollectionIntoShelf() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        BookShelf bookShelf = new BookShelf(10);
        bookShelf.place(bookCollection);
        List<Book> notFoundBookList = new ArrayList<>();
        bookCollection.stream().forEach(currentBook -> {
            if (!bookShelf.contains(currentBook)) {
                notFoundBookList.add(currentBook);
            }
        });
        assertTrue(notFoundBookList.isEmpty());
    }

    @Test
    public void testMoveCollectionThroughShelves() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        BookShelf bookShelfSrc = new BookShelf(1);
        BookShelf bookShelfDest = new BookShelf(1);
        bookShelfSrc.place(bookCollection);
        bookShelfSrc.move(bookCollection, bookShelfDest);
        assertTrue(bookShelfDest.contains(bookCollection));
    }
}
