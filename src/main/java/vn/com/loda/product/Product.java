package vn.com.loda.product;

/**
 * @author loda - namhn1495@gmail.com
 * @date 10/7/2018
 * @description
 */
public class Product implements Item {
  private String name;
  private double price;
  private String color;

  public Product(String name, double price, String color) {
    this.name = name;
    this.price = price;
    this.color = color;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public boolean isColor(String color) {
    return this.color.equals(color);
  }
}