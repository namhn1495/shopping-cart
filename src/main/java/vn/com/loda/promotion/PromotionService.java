package vn.com.loda.promotion;

import vn.com.loda.cart.ICart;

import java.util.List;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public class PromotionService implements IPromotion {
  List<IPromotion> promotions;

  public PromotionService(List<IPromotion> promotions) {
    this.promotions = promotions;
  }

  @Override
  public double apply(ICart cart) {
    return promotions.stream().mapToDouble(value -> value.apply(cart)).sum();
  }

  /**
   * return total price of the cart after apply all the promotions.
   * @param cart
   * @return cart.totalPrice() - discounted price
   */
  public double totalPriceAfterApplyRules(ICart cart){
    return cart.totalPrice() - this.apply(cart);
  }
}
