package main;
import java.util.Arrays;

import discount.Campaign;
import discount.Coupon;
import discount.DiscountType;
import product.Category;
import product.Product;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Category food = new Category("Food");
		Category monitors = new Category("Monitors", "Electronics");
		Category shampooCategory = new Category("Shampoo");
		Category fruit = new Category("Fruit",food);

		Product apple = new Product("Apple", 10.0, fruit);
		Product pasta = new Product("Pasta", 5.0, food);
		Product monitor = new Product("IPS Monitor", 1000.0, monitors);
		Product shampoo = new Product("Shampoo", 20.0, shampooCategory);

		ShoppingCart cart = new ShoppingCart();
		cart.addItem(apple, 1);
		cart.addItem(pasta, 10);
		cart.addItem(monitor, 1);
		cart.addItem(shampoo, 30);

		Campaign c1 = new Campaign(fruit, 10.0, 20, DiscountType.Rate);
		Campaign c2 = new Campaign(monitors, 20.0, 1, DiscountType.Rate);
		Campaign c3 = new Campaign(shampooCategory, 50.0, 2, DiscountType.Rate);
		Campaign c4 = new Campaign(food, 10.0, 10, DiscountType.Amount);
		cart.applyDiscounts(Arrays.asList(c1, c2, c3, c4));

		Coupon coupon = new Coupon(1000, 100, DiscountType.Amount);
		cart.applyCoupon(coupon);

		cart.print();
	}

}
