package discount;

import java.util.List;

import main.ShoppingCart;
import product.Category;
import product.Product;

public class Campaign extends Discount{
	
	private Double totalDiscount;
	private Category campaignCategory;
	private double discountRate;
	private int minQuantity;
	private String discountType;
	
	public Campaign() {		
	}
	
	public Campaign (Category campaignCategory, double discountRate, int minQuantity, String discountType) {
		this.campaignCategory = campaignCategory;
		this.discountRate = discountRate;
		this.minQuantity = minQuantity;
		this.discountType = discountType;
	}
	
	public Campaign getDiscountToApplied(List<Campaign> campaigns) 
	{
		if(campaigns != null)
		{
			campaigns.sort(new DiscountSorter()); 
			return campaigns.get(0);//maximum discount on the cart
		}
		return new Campaign();
	}
	
	@Override
	public void applyDiscount(ShoppingCart shoppingCart, Campaign campaign) 
	{
		shoppingCart.setCampaignDiscount(campaign.getTotalDiscount());
		shoppingCart.setDiscount(shoppingCart.getDiscount() + campaign.getTotalDiscount());
	}

	public void applyDiscounts(List<Campaign> campaigns, ShoppingCart shoppingCart) 
	{
		calculateDiscounts(shoppingCart, campaigns);
		Campaign c = getDiscountToApplied(campaigns);
		applyDiscount(shoppingCart, c);
	}

	private void calculateDiscounts(ShoppingCart shoppingCart, List<Campaign> campaigns)
	{
		for(Campaign campaign : campaigns)
		{
			campaign.totalDiscount = 0.0;
			List<Product> subList = shoppingCart.getProductsByCategory(campaign.getCampaignCategory());
			ShoppingCart tempCart = new ShoppingCart();
			int categoryQuantity = 0;
			for(Product p : subList)
			{
				tempCart.addItem(p, p.getQuantity());
				categoryQuantity = categoryQuantity + p.getQuantity();
				if(campaign.minQuantity <= categoryQuantity)
				{
					if(campaign.getDiscountType() == DiscountType.Rate)
						campaign.totalDiscount = tempCart.getSubtotal() * campaign.discountRate / 100;

					if(campaign.getDiscountType() == DiscountType.Amount)
						campaign.totalDiscount = campaign.discountRate;//actually amount
				}	
			}
		}
	}

	public Double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(Double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Category getCampaignCategory() {
		return campaignCategory;
	}

	public void setCampaignCategory(Category campaignCategory) {
		this.campaignCategory = campaignCategory;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public int getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
}


