package Artikel;

public class DigitalBook extends Book {

    private int size;

    public DigitalBook(double articleNumber, double price, String author, String title, int year, int size) {
        super(articleNumber, price, author, title, year);
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return String.format("Digitales Buch - %30s (%4d) %10.2f", super.getTitle() + " : " + super.getAuthor(), super.getYear(), super.getPrice());
    }

    
}

