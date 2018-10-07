package vn.com.loda.promotion;

import vn.com.loda.cart.ICart;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public interface IPromotion {
  /**
   * Applying this promotion rule to the specific cart
   * @param cart
   * @return the discounted value
   */
  public double apply(ICart cart);
}
