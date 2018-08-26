public class Order {

    public static void setBookAsOutOfOrder(Book book) {
        book.setOutOfOrder();
    }

    public static void setBookAvailable(Book book) {
        book.addCopy();
    }
}
