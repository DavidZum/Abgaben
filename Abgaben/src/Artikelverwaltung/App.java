public class App {
    public static void main(String[] args) throws Exception {
        DVD dvd = new DVD(1, 1, "irgendwas", 10, COUNTRYCODE.AUT);
        Book book = new Book(2, 2, "irgenwer", "irgendwas", 2000);
        DigitalBook db = new DigitalBook(3, 3, "irgendwer", "irgendwas", 2010, 2);
        ShoppingCart sc = new ShoppingCart();
        sc.addArticle(dvd);
        sc.addArticle(book);
        sc.addArticle(db);
        System.out.println(sc);
    }
}
