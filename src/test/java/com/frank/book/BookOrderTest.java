package com.frank.book;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BookOrderTest {

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
    public void testPlaceAnOrder() {
        Book book = bookRegister.get("isbn");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        Order order = new Order();
        order.add(bookCollection);
        assertTrue(bookCollection.isOrdered());
    }
}
