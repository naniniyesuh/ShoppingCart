package product;

public class Category {
	
	private String categoryTitle;
	private Category parentCategory;
	
	public Category(String title)
	{
		this.categoryTitle = title;
	}
	
	public Category(String title, String parent)
	{
		this.categoryTitle = title;
		this.setParentCategory(new Category(parent));
	}
	
	public Category(String title, Category parent)
	{
		this.categoryTitle = title;
		this.setParentCategory(parent);
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	
}
