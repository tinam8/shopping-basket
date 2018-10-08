package models;

import java.util.ArrayList;
import java.util.List;

public class AddedItem implements IBasketItem {
  private Item item;
  private double quantity;

  public AddedItem(Item item, double quantity) {
    this.item = item;
    this.quantity = quantity;
  }

  @Override
  public Item getItem() {
    return item;
  }

  @Override
  public double getQuantity() {
    return quantity;
  }

  public void addQuantity(double quantity) {
    this.quantity += quantity;
  }

  @Override
  public double getTotalPrice() {
    return item.getPrice() * quantity;
  }

  @Override
  public String describeDiscount() {
    return String.format("%.0f %s, Price: %.2f", quantity, item.getName(), getTotalPrice());
  }

  @Override
  public IBasketItem applyDiscountResult(DiscountResult discountResult) {
    return new DiscountedItem(new AddedItem(item, 1), discountResult);
  }

  @Override
  public List<DiscountResult> getAppliedDiscountResults() {
    return new ArrayList<DiscountResult>();
  }
}
