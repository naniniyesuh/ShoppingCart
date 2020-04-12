package delivery;

import main.ShoppingCart;

public class DeliveryCostCalculator {

	private double costPerDelivery;
	private double costPerProduct;
	private double fixedCost;
	
	public DeliveryCostCalculator (double costPerDelivery, double costPerProduct, double fixedCost)
	{
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
		this.fixedCost = fixedCost;
	}
	
	public void calculateFor (ShoppingCart cart)
	{
		int numberOfDeliveries = cart.getCategoryList().size();
		int numberOfProducts = cart.getProductList().size();
		cart.setDeliveryCost((costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost);
	}
}
