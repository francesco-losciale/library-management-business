public class Book {

    private String title;
    private String author;
    private String isbn;
    private int availability;

    public Object getId() {
        return getIsbn();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isOutOfOrder() {
        return availability == 0;
    }

    void setOutOfOrder() {
        availability = 0;
    }

    public void addAvailability() {
        availability += 1;
    }

    public void addAvailability(int availability) {
        this.availability += availability;
    }
}
