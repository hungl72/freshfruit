package project.controllers.model;

public class Item {
	private Product product;
	private float mass;
	public Item() {
		super();
	}
	public Item(Product product, float mass) {
		super();
		this.product = product;
		this.mass = mass;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public float getMass() {
		return mass;
	}
	public void setMass(float mass) {
		this.mass = mass;
	}
	
	
}
