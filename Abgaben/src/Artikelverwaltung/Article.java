public class Article {
    private double articleNumber;
    private double price;

    public Article(double articleNumber, double price) {
        this.articleNumber = articleNumber;
        this.price = price;
    }

    public double getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(double articleNumber) {
        this.articleNumber = articleNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Article - %44.0f %10.2f", this.articleNumber, this.price);
    }

    @Override
    public boolean equals(Object obj) {
        Article art = (Article) obj;
        return this.articleNumber == art.getArticleNumber();
    }    
}
