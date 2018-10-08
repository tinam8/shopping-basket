package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingBasket {
  private List<AddedItem> addedItems = new ArrayList<>();

  public List<AddedItem> getAddedItems() {
    return addedItems;
  }

  public void addItem(Item item, double quantity) {
    int index = addedItems.indexOf(item);

    if (index == -1) {
      addedItems.add(new AddedItem(item, quantity));
      return;
    }

    addedItems.get(index).addQuantity(quantity);
  }

  public double getTotalSum(List<IBasketItem> resultList) {
    return resultList.stream().mapToDouble(r -> r.getTotalPrice()).sum();
  }

}
