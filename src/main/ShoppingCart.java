package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import delivery.DeliveryCostCalculator;
import discount.Campaign;
import discount.Coupon;
import product.Category;
import product.Product;

public class ShoppingCart {
	
	private List<Product> productList;
	private List<Category> categoryList;
	private double subtotal;
	private double discount;
	private double deliveryCost;
	private double totalAmount;
	private double couponDiscount;
	private double campaignDiscount;
	

	public ShoppingCart()
	{
		this.productList = new LinkedList<Product>();
		this.categoryList = new LinkedList<Category>();
		this.subtotal = 0;
	}
	
	public void addItem (Product p, int quantity) {
		p.setQuantity(quantity);
		this.productList.add(p);
		
		if(!this.categoryList.contains(p.getCategory()))
			this.categoryList.add(p.getCategory());
		
		this.subtotal = this.subtotal + (p.getPrice() * quantity);
	}
	
	public List<Product> getProductsByCategory (Category category)
	{
		HashMap<Category, List<Product>> hashMap = new HashMap<Category, List<Product>>();

		for(Product p : this.productList)
		{
			if(p.getCategory() == category)
			{
				if (!hashMap.containsKey(category)) {
					List<Product> list = new ArrayList<Product>();
					list.add(p);

					hashMap.put(category, list);
				} else {
					hashMap.get(category).add(p);
				}
			}
		}
		return hashMap.get(category);
	}
	
	public void applyDiscounts(List<Campaign> campaigns) 
	{
		new Campaign().applyDiscounts(campaigns, this);
	}
		
	public void applyCoupon(Coupon c) 
	{
		new Coupon().applyCoupon(c,this);
	}
	
	public void updateSubtotal() 
	{
		this.setSubtotal(this.getSubtotal() - this.getDiscount());
	}

	public double getTotalAmountAfterDiscounts() {
		return this.getTotalPayment();
	}
	
	public double getCouponDiscount() {
		return this.couponDiscount;
	}
	
	public double getCampaignDiscount() {
		return this.campaignDiscount;
	}
	
	public void print() 
	{	
		sortProducts();
		finalCalculations();
		for(Product p : this.getProductList())
		{
			System.out.println("Category Name: "+ p.getCategory().getCategoryTitle());
			System.out.println("Product Name:  "+ p.getTitle());
			System.out.println("Quantity:      "+ p.getQuantity());
			System.out.println("Unit Price:    "+ p.getPrice());
			System.out.println("Total Price:   "+ p.getPrice()*p.getQuantity() );
			System.out.println("__________");
		}
		System.out.println("___________________");
		System.out.println("Subtotal:	"+ this.getSubtotal());
		System.out.println("Campaign Discount: "+ this.getCampaignDiscount());
		System.out.println("Coupon Discount: "+ this.getCouponDiscount());
		System.out.println("Total Discount: "+ this.getDiscount());
		System.out.println("Delivery Cost:	"+ this.getDeliveryCost());
		System.out.println("Total Amount:	"+ this.getTotalPayment());
	}
	
	private void sortProducts()
	{
		ShoppingCart tempCart = new ShoppingCart();
		for(Category c : this.getCategoryList())
		{
			var tempList = getProductsByCategory(c);
			for(Product temp : tempList)
			{
				tempCart.addItem(temp, temp.getQuantity());
			}
		}
		this.setProductList(null);
		this.setProductList(tempCart.getProductList());
	}
	
	private void finalCalculations()
	{
		DeliveryCostCalculator delivery = new DeliveryCostCalculator(5, 0, 2.99);
		delivery.calculateFor(this);
		this.setTotalPayment(this.getSubtotal() - this.getDiscount() + this.getDeliveryCost());
	}
	
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public double getTotalPayment() {
		return totalAmount;
	}

	public void setTotalPayment(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setCouponDiscount(double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public void setCampaignDiscount(double campaignDiscount) {
		this.campaignDiscount = campaignDiscount;
	}

}
