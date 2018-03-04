package model;

import java.util.UUID;

public class Article {

    private final UUID uuid;
    private final double price;

    public Article(UUID uuid, double price) {
        this.uuid = uuid;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (Double.compare(article.price, price) != 0) return false;
        return uuid != null ? uuid.equals(article.uuid) : article.uuid == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = uuid != null ? uuid.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "uuid=" + uuid +
                ", price=" + price +
                '}';
    }
}
