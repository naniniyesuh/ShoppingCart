package discount;

import main.ShoppingCart;

public class Coupon {

	private double minAmount;
	private double discountRate;
	
	public Coupon() {
		
	}
	
	public Coupon (double minAmount, double discountRate, String discountType)
	{
		this.minAmount = minAmount;
		this.discountRate = discountRate;
	}

	public void applyCoupon(Coupon c, ShoppingCart shoppingCart) 
	{
		if(shoppingCart.getSubtotal() > c.getMinAmount())
		{
			shoppingCart.setDiscount(shoppingCart.getDiscount() + c.getDiscountRate());
		}
	}
	
	public double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

}
