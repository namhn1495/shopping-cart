package vn.com.loda.cart;

import vn.com.loda.product.Item;
import vn.com.loda.user.User;

import java.util.List;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public interface ICart {
  /**
   *  total price of all products in cart
   * @return
   */
  public double totalPrice();

  /**
   * Add item to cart
   * @param item
   */
  public void add(Item item);

  public void setUser(User user);
  public User getUser();

  /**
   * get all items as List<Item> in this cart
   * @return
   */
  public List<Item> getItems();
}
