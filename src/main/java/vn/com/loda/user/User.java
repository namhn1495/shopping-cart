package vn.com.loda.user;

import vn.com.loda.cart.ICart;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public class User {
  private String name;
  private String email;
  private UserGroup userGroup;
  private ICart cart = null;

  public User(String name, String email, UserGroup userGroup) {
    this.name = name;
    this.email = email;
    this.userGroup = userGroup;
  }

  public ICart getCart() {
    return cart;
  }

  public void setCart(ICart shoppingCart) {
    this.cart = shoppingCart;
    this.cart.setUser(this);
  }

  public boolean inGroup(UserGroup userGroup) {
    return this.userGroup == userGroup;
  }
}
