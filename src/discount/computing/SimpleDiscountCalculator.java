package discount.computing;

import java.util.ArrayList;
import java.util.List;

import models.AddedItem;
import models.DiscountResult;
import models.IBasketItem;

/**
 * Implements calculation logic. If more discounts can be applied to one item
 * all of them are applied. <br>
 * If there are two discounts that imply that one unit of an item "x" is for
 * free, only one unit is for free and not two units
 * 
 * @author tina
 *
 */
public class SimpleDiscountCalculator {

  private boolean resultIsAppliable(IBasketItem item, DiscountResult discountResult, boolean applied) {
    return (item.getItem() != discountResult.getItem() || applied
        || item.getAppliedDiscountResults().contains(discountResult) || item.getTotalPrice() == 0);
  }

  /**
   * Method that applies discount result on items in the basket. It creates new
   * list of IBasketItem instances <br>
   * - items that are not affected by discount result are added into the new list
   * unchanged <br>
   * - for affected item ,one instance of DiscountedItem (contains information
   * about discount) and one instance of AddedItem (rest of units that are not on
   * the discount) are created .
   * 
   * @param basketItems
   *          list of IBasketItem instances
   * @param discountResult
   *          information about concrete application of discount
   * @return list of basket items with applied discount result
   */
  private List<IBasketItem> applyDiscountResult(List<IBasketItem> basketItems, DiscountResult discountResult) {
    List<IBasketItem> resultItems = new ArrayList<>();

    boolean applied = false;

    for (IBasketItem item : basketItems) {
      if (resultIsAppliable(item, discountResult, applied)) {
        resultItems.add(item);
        continue;
      }

      applied = true;
      resultItems.add(item.applyDiscountResult(discountResult));
      int remainingItemsCount = (int) (item.getQuantity() - 1);

      if (remainingItemsCount > 0) {
        resultItems.add(new AddedItem(item.getItem(), item.getQuantity() - 1));
      }
    }

    return resultItems;
  }

  /**
   * Method that iteratively applies all discount results on original basket
   * 
   * @param items
   * @param discountResults
   * @resultItems list of IBasketItems that represents items with all discounts
   *              applied
   */
  public List<IBasketItem> applyDiscountResults(List<IBasketItem> items, List<DiscountResult> discountResults) {
    List<IBasketItem> resultItems = items;

    for (DiscountResult discountResult : discountResults) {
      resultItems = applyDiscountResult(resultItems, discountResult);
    }

    return resultItems;
  }
}
