package vn.com.loda.promotion;

import vn.com.loda.user.UserGroup;

import java.util.Calendar;
import java.util.Date;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public abstract class AbstractPromotion implements IPromotion{
  protected Date fromDate;
  protected Date toDate;
  protected String color;
  protected UserGroup userGroup;
  protected double subTotal;
  protected double discount;

  public AbstractPromotion(Date fromDate, Date toDate, String color, UserGroup userGroup, double subTotal, double discount) {
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.color = color;
    this.userGroup = userGroup;
    this.subTotal = subTotal;
    this.discount = discount;
  }

  public boolean isExpired() {
    // get current date
    Date currentDate = Calendar.getInstance().getTime();
    // Check the current time outside the promotion period
    return toDate.compareTo(currentDate) <= 0 || fromDate.compareTo(currentDate) > 0;
  }
}
