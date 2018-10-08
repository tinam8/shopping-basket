package models;

import java.util.List;

public class Discount {
  private List<DiscountCondition> discountConditions;
  private List<DiscountResult> discountResults;

  public Discount(List<DiscountCondition> discountConditions, List<DiscountResult> discountResults) {
    this.discountConditions = discountConditions;
    this.discountResults = discountResults;
  }

  public List<DiscountResult> getDiscountResults() {
    return discountResults;
  }

  public int timesApplicable(List<AddedItem> basketItems) {
    return discountConditions.stream().map(c -> c.timesApplicable(basketItems)).min(Integer::compare).get();
  }
}
