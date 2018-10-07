
# Shopping Cart
This is my home-test when interviewing the backend position at tiki.vn

The point of this exercise is the `Promotion` function. Requires the ability to add new rules without affecting the `cart` and its information. To do that, I created an interface `IPromotion`. Initially, I was going to do this with the `Chain of Responsibility` pattern, but it was not really necessary in this test, so I made it as simple as possible.
```java
public interface IPromotion {
  /**
   * Applying this promotion rule to the specific cart
   * @param cart
   * @return the discounted value
   */
  public double apply(ICart cart);
}
```
And this is one of its implementations 
```java
  
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
```
 
This project follows the S.O.L.I.D principle except for the User class, it's not abstraction because I want to simplify this section.

See details in  [src/main/java](https://github.com/namhn1495/shopping-cart/tree/master/src/main/java/vn/com/loda)
# Test

```java
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

```

# Build
This project only contains test cases without main class.

To run the unit tests:

    mvn test
