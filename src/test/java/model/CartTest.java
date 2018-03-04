package model;

import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CartTest {

    @Test
    public void cart_is_empty_when_created() {
        //WHEN
        Cart cart = Cart.empty();
        //THEN
        assertThat(cart.content()).isEmpty();
    }

    @Test
    public void an_user_may_add_an_article_in_a_cart() {
        //GIVEN
        Article article = new Article(UUID.randomUUID(), 5.0);
        //WHEN
        Cart cart = Cart.empty().addArticle(article);
        //THEN
        assertThat(cart.content()).contains(article);
    }

    @Test
    public void an_user_cant_add_a_null_article() {
        //GIVEN
        Cart cart = Cart.empty();
        //WHEN
        Throwable thrown = catchThrowable(() -> cart.addArticle(null));
        //THEN
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void an_user_can_remove_an_added_article() {
        //GIVEN
        Article article = new Article(UUID.randomUUID(), 5.0);
        Cart cart = Cart.empty().addArticle(article);
        //WHEN
        Cart tested = cart.removeArticle(article);
        //THEN
        assertThat(tested.content()).isEmpty();
    }

    @Test
    public void removing_an_article_not_in_the_cart_has_no_effect_on_empty_cart() {
        //GIVEN
        Article article = new Article(UUID.randomUUID(), 5.0);
        Cart cart = Cart.empty();
        //WHEN
        Cart tested = cart.removeArticle(article);
        //THEN
        assertThat(tested.content()).isEmpty();
    }

    @Test
    public void removing_an_absent_article_has_no_effect_on_the_cart() {
        //GIVEN
        Article article = new Article(UUID.randomUUID(), 5.0);
        Cart cart = Cart.empty().addArticle(article);
        //WHEN
        Cart tested = cart.removeArticle(new Article(UUID.randomUUID(), 5.0));
        //THEN
        assertThat(tested.content()).contains(article);
    }

    @Test
    public void an_user_cant_remove_a_null_article() {
        //GIVEN
        Cart cart = Cart.empty();
        //WHEN
        Throwable thrown = catchThrowable(() -> cart.removeArticle(null));
        //THEN
        assertThat(thrown).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void an_empty_list_is_returned_if_the_cart_is_empty() {
        //GIVEN
        Cart cart = Cart.empty();
        //WHEN
        List<Article> articles = cart.content();
        //THEN
        assertThat(articles).isEmpty();
    }

    @Test
    public void an_user_can_see_the_content_of_the_cart() {
        //GIVEN
        Article article = new Article(UUID.randomUUID(), 5.0);
        Cart cart = Cart.empty().addArticle(article);
        //WHEN
        List<Article> articles = cart.content();
        //THEN
        assertThat(articles).contains(article);
    }

    @Test
    public void total_price_is_0_on_empty_cart() {
        //GIVEN
        Cart cart = Cart.empty();
        //WHEN
        double total = cart.totalPrice();
        //THEN
        assertThat(total).isEqualTo(0.0);
    }

    @Test
    public void total_price_is_the_sum_of_the_prices_of_all_articles() {
        //GIVEN
        Article article1 = new Article(UUID.randomUUID(), 5.0);
        Article article2 = new Article(UUID.randomUUID(), 2.0);
        Cart cart = Cart.empty().addArticle(article1).addArticle(article2);
        //WHEN
        double total = cart.totalPrice();
        //THEN
        assertThat(total).isEqualTo(article1.getPrice() + article2.getPrice());
    }
}
