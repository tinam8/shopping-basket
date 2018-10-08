import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import discount.computing.DiscountResultResolver;
import discount.computing.SimpleDiscountCalculator;
import models.Discount;
import models.DiscountCondition;
import models.DiscountResult;
import models.IBasketItem;
import models.Item;
import models.ShoppingBasket;

public class Main {
	public static void main(String[] args) {
	  SimpleDiscountCalculator calculator = new SimpleDiscountCalculator();

		Item butter = new Item("Butter", 0.80);
		Item milk = new Item("Milk", 1.15);
		Item bread = new Item("Bread", 1);
		
		
		Discount discount1 = new Discount(
      Arrays.asList(
        new DiscountCondition(butter, 2), 
        new DiscountCondition(bread, 1)
      ),
      Arrays.asList(
        new DiscountResult(bread, 0.5)
      )
    );
	    
	    
    Discount discount2 = new Discount(
      Arrays.asList(
        new DiscountCondition(milk, 4)
      ),
      Arrays.asList(
        new DiscountResult(milk, 1)
      )
    );
	    
    Discount discount3 = new Discount(
        Arrays.asList(
          new DiscountCondition(milk, 1),
          new DiscountCondition(butter, 1)
        ),
        Arrays.asList(
          new DiscountResult(bread, 0.4)
        )
      );
    
    Discount discount4 = new Discount(
      Arrays.asList(
        new DiscountCondition(butter, 1),
        new DiscountCondition(milk, 1)
      ),
      Arrays.asList(
        new DiscountResult(milk, 1)
      )
    );
		
		ShoppingBasket shoppingBasket = new ShoppingBasket();
		shoppingBasket.addItem(milk, 8);
		shoppingBasket.addItem(butter, 2);
		shoppingBasket.addItem(bread, 1);
	
		
		
//		List<Discount> discounts = Arrays.asList(discount1, discount2, discount4);
		List<Discount> discounts = Arrays.asList(discount1, discount2);
//		List<Discount> discounts = Arrays.asList(discount1, discount2, discount3);
		List<DiscountResult> discountResults = DiscountResultResolver.computeApplicableDiscountResults(shoppingBasket.getAddedItems(), discounts);

		discountResults.forEach(r -> System.out.println(r.getDescription()));
		System.out.println();
		
		List<IBasketItem> items = shoppingBasket.getAddedItems().stream().map(item -> (IBasketItem)item).collect(Collectors.toList());
		List<IBasketItem> discountedItems = calculator.applyDiscountResults(items, discountResults);
		discountedItems.forEach(i -> System.out.println(i.describeDiscount()));
		System.out.printf("Total price: %.2f", shoppingBasket.getTotalSum(discountedItems));

	}
}
