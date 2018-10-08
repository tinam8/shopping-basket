package models;

import java.util.List;

public interface IBasketItem {

  public double getTotalPrice();

  public Item getItem();

  public double getQuantity();

  public String describeDiscount();

  public IBasketItem applyDiscountResult(DiscountResult discountResult);

  public List<DiscountResult> getAppliedDiscountResults();
}
