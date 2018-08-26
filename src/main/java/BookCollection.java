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
}
