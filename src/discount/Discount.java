package discount;

import main.ShoppingCart;

public abstract class Discount {
	
	public abstract void applyDiscount(ShoppingCart shoppingCart, Campaign campaign);

}
