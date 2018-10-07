package vn.com.loda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import vn.com.loda.cart.ICart;
import vn.com.loda.cart.ShoppingCart;
import vn.com.loda.product.Item;
import vn.com.loda.product.Product;
import vn.com.loda.promotion.BlackGoldPromotion;
import vn.com.loda.promotion.IPromotion;
import vn.com.loda.promotion.PromotionService;
import vn.com.loda.user.User;
import vn.com.loda.user.UserGroup;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public class TestCase {
  private ICart cart;

  @Before
  public void setUp() {
    List<Item> products = Collections.synchronizedList(new ArrayList<>());
    cart = new ShoppingCart(products);

    User user = new User("John Doe 1", "john.doe@example.com", UserGroup.GOLD);
    user.setCart(cart);

    user.getCart().add(new Product("Iphone Silver", 999, "Silver"));
    user.getCart().add(new Product("Iphone Silver", 999, "Silver"));
    user.getCart().add(new Product("Iphone Black", 899, "Black"));
  }

  @Test
  public void testCartTotalPrice() {
    assertEquals(2897.0, cart.totalPrice(),0.0);
  }

  @Test
  public void testCartTotalPriceAfterApplyPromotion() {
    List<IPromotion> promotions = getFakePromotions();
    PromotionService promotionService = new PromotionService(promotions);
    assertEquals(2897.0, promotionService.totalPriceAfterApplyRules(cart),0.0);

  }

  public List<IPromotion> getFakePromotions(){
    long current = Calendar.getInstance().getTimeInMillis();
    Date fromDate = new Date(current - TimeUnit.DAYS.toMillis(1));
    Date toDate = new Date(current + TimeUnit.DAYS.toMillis(1));

    List<IPromotion> promotions = new ArrayList<>();
    promotions.add(new BlackGoldPromotion(fromDate,toDate,"Black", UserGroup.GOLD, 1500, 50));
    return promotions;
  }
}
