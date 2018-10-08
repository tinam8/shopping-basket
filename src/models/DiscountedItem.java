package models;

import java.util.List;

public class DiscountedItem implements IBasketItem {
  private IBasketItem addedItem;
  private DiscountResult discountResult;
  // currently only one unit can be discounted
  private int applyCount;

  public DiscountedItem(IBasketItem addedItem, DiscountResult discountResult) {
    this.addedItem = addedItem;
    this.applyCount = 1;
    this.discountResult = discountResult;
  }

  @Override
  public double getTotalPrice() {
    return addedItem.getTotalPrice() * (1 - discountResult.discountRatio());
  }

  @Override
  public Item getItem() {
    return addedItem.getItem();
  }

  @Override
  public double getQuantity() {
    return 1;
  }

  @Override
  public String describeDiscount() {
    return String.format("(%s - %.2f%%) Price: %.2f", addedItem.describeDiscount(),
        discountResult.discountRatio() * 100, getTotalPrice());
  }

  @Override
  public IBasketItem applyDiscountResult(DiscountResult discountResult) {
    return new DiscountedItem(this, discountResult);
  }

  @Override
  public List<DiscountResult> getAppliedDiscountResults() {
    List<DiscountResult> appliedResults = addedItem.getAppliedDiscountResults();
    appliedResults.add(discountResult);

    return appliedResults;
  }
}
