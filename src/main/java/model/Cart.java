package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cart {

    private final List<Article> articles;

    private Cart(List<Article> articles) {
        this.articles = articles;
    }

    public static Cart empty() {
        return new Cart(new ArrayList<>());
    }

    public List<Article> content() {
        return Collections.unmodifiableList(articles);
    }

    public Cart addArticle(Article article) {
        Objects.requireNonNull(article, "Article can't be null");
        articles.add(article);
        return this;
    }

    public Cart removeArticle(Article article) {
        Objects.requireNonNull(article, "Article can't be null");
        articles.remove(article);
        return this;
    }

    public double totalPrice() {
        return articles.stream().mapToDouble(Article::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "articles=" + articles +
                '}';
    }
}
