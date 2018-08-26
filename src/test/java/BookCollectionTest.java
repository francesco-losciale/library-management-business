import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookCollectionTest {

    @Test
    public void testCollectBooksTogheter() {
        String book = "test";
        List<String> collection = new ArrayList<String>();
        collection.add(book);
        assertTrue(collection.contains(book));
    }

}
