package model;

import java.util.UUID;

public class Article {

    private final UUID uuid;
    private final double price;

    public Article(UUID uuid, double price) {
        this.uuid = uuid;
        this.price = price;
    }

    public Article(double price) {
        this(UUID.randomUUID(), price);
    }

    public UUID getUuid() {
        return uuid;
    }

    public double getPrice() {
        return price;
    }


}
