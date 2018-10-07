package vn.com.loda.promotion;

import vn.com.loda.cart.ICart;
import vn.com.loda.product.Item;
import vn.com.loda.user.UserGroup;

import java.util.Date;
import java.util.Objects;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public class BlackGoldPromotion extends AbstractPromotion {

  public BlackGoldPromotion(Date fromDate, Date toDate, String color, UserGroup userGroup, double subTotal, double discount) {
    super(fromDate, toDate, color, userGroup, subTotal, discount);
  }

  @Override
  public double apply(ICart cart) {
    Objects.requireNonNull(cart);
    // Not apply this promotion if it has expired or the user is not in the group
    if (isExpired() || !cart.getUser().inGroup(this.userGroup)) {
      return 0;
    }
    // calculate total price of products with the same color with this promotion
    double totalDiscountedPrice =
        cart.getItems()
            .stream()
            .filter(item -> item.isColor(this.color))
            .mapToDouble(Item::getPrice)
            .sum();
    // Returns the value of the discount if the condition is met
    return totalDiscountedPrice > this.subTotal ? discount : 0;
  }
}
