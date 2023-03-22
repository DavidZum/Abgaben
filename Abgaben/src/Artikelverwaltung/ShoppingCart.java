import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Article> articles = new ArrayList<>();

    public ShoppingCart() {

    }

    public void addArticle(Article a) {
        articles.add(a);
    }

    public void removeArticle(Article a) {
        articles.remove(a);
    }

    public double showBill() {
        double sum = 0;
        for (Article article : articles) {
            sum += article.getPrice();
        }
        return sum;
    }

    public String toString() {
        String str = "";
        for (Article article : articles) {
            str += article.toString();
            str = String.format("%s \n", str);
        }
        str = String.format("%s%54s %10.2f", str, "Summe:", showBill());
        return str;
    }
}
