package Artikel;

public class DVD extends Article {

    private final double VAT = 0.19;
    private String name;
    private int duration;
    private COUNTRYCODE countryCode;

    public DVD(double articleNumber, double price, String name, int duration, COUNTRYCODE countryCode) {
        super(articleNumber, price);
        this.name = name;
        this.duration = duration;
        this.countryCode = countryCode;
    }

    public double getPrice() {
        return super.getPrice() + super.getPrice() * VAT;
    }

    @Override
    public String toString() {
        return String.format("DVD  - %40s (%3s)  %10.2f", this.name, this.countryCode,this.getPrice());
    }

    @Override
    public boolean equals(Object obj) {
        DVD d = (DVD) obj;
        return super.equals(obj) && d.duration == this.duration;
    }
}

