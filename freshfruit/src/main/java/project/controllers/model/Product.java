package project.controllers.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Product {
	private int product_id;
	@NotEmpty
	private String product_name;
	@Min(value = 0, message = "Chỉ có thể >= 0")
	private double product_price;
	@Min(value = 0, message = "Chỉ có thể >= 0")
	private double product_promotion;
	@NotEmpty
	private String product_description;
	private String product_image;
	private float product_mass;
	private int menu_id;
	private String menu_name;
	private Menu menu;
	private String picture;
	private int parent_id;
	public Product() {
		super();
	}
	public Product(int product_id, String product_name, double product_price, double product_promotion,
			String product_description, String product_image, int menu_id) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_promotion = product_promotion;
		this.product_description = product_description;
		this.product_image = product_image;
		this.menu_id = menu_id;
	}
	
	public Product(int product_id, String product_name, double product_price, double product_promotion,
			String product_description, String product_image,float product_mass, int menu_id) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_promotion = product_promotion;
		this.product_description = product_description;
		this.product_image = product_image;
		this.product_mass = product_mass;
		this.menu_id = menu_id;
	}
	
	public Product(int product_id, String product_name, double product_price, double product_promotion,
			String product_description, String product_image, int menu_id, Menu menu, String picture) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_promotion = product_promotion;
		this.product_description = product_description;
		this.product_image = product_image;
		this.menu_id = menu_id;
		this.menu = menu;
		this.picture = picture;
	}
	
	public Product(int product_id, String product_name, double product_price, double product_promotion,
			String product_description, String product_image, int menu_id, String menu_name) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_promotion = product_promotion;
		this.product_description = product_description;
		this.product_image = product_image;
		this.menu_id = menu_id;
		this.menu_name = menu_name;
	}

	public Product(int product_id, String product_name, double product_price, double product_promotion,
			String product_description, String product_image, int menu_id, String menu_name, int parent_id) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_promotion = product_promotion;
		this.product_description = product_description;
		this.product_image = product_image;
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.parent_id = parent_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getProduct_price() {
		return product_price;
	}
	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}
	public double getProduct_promotion() {
		return product_promotion;
	}
	public void setProduct_promotion(double product_promotion) {
		this.product_promotion = product_promotion;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public float getProduct_mass() {
		return product_mass;
	}
	public void setProduct_mass(float product_mass) {
		this.product_mass = product_mass;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
}
