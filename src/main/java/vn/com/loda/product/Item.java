package vn.com.loda.product;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public interface Item {
  /**
   * get this item price
   * @return double
   */
  public double getPrice();

  /**
   * Check if item is the same color as @color
   * @param color the color want to compare to
   * @return
   */
  public boolean isColor(String color);
}
