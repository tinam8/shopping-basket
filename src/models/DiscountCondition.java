package models;

import java.util.List;

public class DiscountCondition {
  private Item item;
  private double count;

  public DiscountCondition(Item item, double count) {
    this.item = item;
    this.count = count;
  }

  public int timesApplicable(List<AddedItem> basketItems) {
    for (IBasketItem basketItem : basketItems) {
      if (basketItem.getItem() == item && basketItem.getQuantity() >= count) {
        return (int) (basketItem.getQuantity() / count);
      }
    }

    return 0;
  }
}
