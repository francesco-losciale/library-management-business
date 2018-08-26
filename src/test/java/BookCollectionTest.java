import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookCollectionTest {

    @Test
    public void testCollectBooksTogether() {
        Book book = new Book();
        book.setTitle("test");
        book.setAuthor("test");
        book.setIsbn("test");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        assertTrue(bookCollection.contains(book));
    }

    @Test
    public void testSeekBookInCollection() {
        Book book = new Book();
        book.setTitle("test");
        book.setAuthor("test");
        book.setIsbn("test");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        Book result = bookCollection.seek(book);
        assertEquals(result, book);
    }

    @Test
    public void testSeekBookInCollections() {
        Book book = new Book();
        book.setTitle("test");
        book.setAuthor("test");
        book.setIsbn("test");
        BookCollection bookCollection1 = new BookCollection();
        BookCollection bookCollection2 = new BookCollection();
        bookCollection1.add(book);
        Book result = BookCollection.seek(book, bookCollection1, bookCollection2);
        assertEquals(result, book);
    }

    @Test
    public void testMoveBookToDifferentCollection() {
        Book book = new Book();
        book.setTitle("test");
        book.setAuthor("test");
        book.setIsbn("test");
        BookCollection bookCollection1 = new BookCollection();
        bookCollection1.add(book);
        BookCollection bookCollection2 = new BookCollection();
        bookCollection1.move(book, bookCollection2);
        assertFalse(bookCollection1.contains(book));
        assertTrue(bookCollection2.contains(book));
    }

    @Test
    public void testMakeBookOutOfOrder() {
        Book book = new Book();
        book.setTitle("test");
        book.setAuthor("test");
        book.setIsbn("test");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        Order.setBookAsOutOfOrder(book);
        assertTrue(book.isOutOfOrder());
    }

    @Test
    public void testMakeBookAvailable() {
        Book book = new Book();
        book.setTitle("test");
        book.setAuthor("test");
        book.setIsbn("test");
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        Order.setBookAvailable(book);
        assertFalse(book.isOutOfOrder());
    }
}
