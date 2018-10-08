package discount.computing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import models.AddedItem;
import models.Discount;
import models.DiscountResult;

public class DiscountResultResolver {

  private static List<DiscountResult> multiplyDiscountResult(DiscountResult discountResult, int count) {
    return IntStream.range(0, count).mapToObj(i -> discountResult).collect(Collectors.toList());
  }

  /**
   * Method that finds all applicable discounts by checking discount conditions
   * and than extracts all corresponding discount results
   * 
   * @param basketItems
   *          items added to the basket
   * @param discounts
   *          all available discounts
   * @return all discount results that can be applied based on the content of the
   *         basket
   */
  public static List<DiscountResult> computeApplicableDiscountResults(List<AddedItem> basketItems,
      List<Discount> discounts) {
    List<DiscountResult> discountResults = new ArrayList<>();

    for (Discount discount : discounts) {
      for (DiscountResult discountResult : discount.getDiscountResults()) {
        List<DiscountResult> repeatedResults = multiplyDiscountResult(discountResult,
            discount.timesApplicable(basketItems));
        discountResults.addAll(repeatedResults);
      }
    }

    return discountResults;
  }
}
