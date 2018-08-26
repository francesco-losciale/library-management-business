import java.util.ArrayList;
import java.util.List;

public class BookCollection {

    private List<String> collection = new ArrayList<String>();

    public void add(String book) {
        collection.add(book);
    }

    public boolean contains(String book) {
        return collection.contains(book);
    }

    public String seek(String book) {
        for (String currentBook : collection) {
            if (currentBook.equals(book)) {
                return book;
            }
        }
        return null;
    }
}
