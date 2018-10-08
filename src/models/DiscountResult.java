package models;

public class DiscountResult {
  private Item item;
  private double count = 1;
  private double dicountRatio;

  public DiscountResult(Item item, double dicountRatio) {
    this.item = item;
    this.dicountRatio = dicountRatio;
  }

  public Item getItem() {
    return item;
  }

  public double getCount() {
    return count;
  }

  public double discountRatio() {
    return dicountRatio;
  }

  public String getDescription() {
    return (dicountRatio * 100) + "% discount for item " + item.getName();
  }
}
