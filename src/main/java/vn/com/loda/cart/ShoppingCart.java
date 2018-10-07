package vn.com.loda.cart;

import vn.com.loda.product.Item;
import vn.com.loda.user.User;

import java.util.List;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public class ShoppingCart implements ICart {
  private List<Item> items;
  private User user;

  public ShoppingCart(List<Item> items){
    this.items = items;
  }

  @Override
  public void setUser(User user){
    this.user = user;
  }

  @Override
  public User getUser() {
    return this.user;
  }

  @Override
  public double totalPrice() {
    return items.stream().mapToDouble(Item::getPrice).sum();
  }

  @Override
  public void add(Item item) {
    if (null != this.items) {
      this.items.add(item);
    }
  }

  @Override
  public List<Item> getItems() {
    return items;
  }
}
