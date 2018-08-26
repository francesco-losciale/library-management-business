public class Order {

    public static void setBookAsOutOfOrder(Book book) {
        book.setOutOfOrder(true);
    }

    public static void setBookAvailable(Book book) {
        book.setOutOfOrder(false);
    }
}
