import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookCollectionTest {

    @Test
    public void testCollectBooksTogether() {
        String book = "test";
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        assertTrue(bookCollection.contains(book));
    }


    @Test
    public void testSeekBookInCollection() {
        String book = "test";
        BookCollection bookCollection = new BookCollection();
        bookCollection.add(book);
        String result = bookCollection.seek(book);
        assertEquals(result, book);
    }

}
