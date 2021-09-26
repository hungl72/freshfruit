package project.controllers.model;

public class Bill {
	private int bill_id;
	private String bill_date;
	private String bill_product;
	private double root_price;
	private double bill_price;
	private String bill_information;
	private int id_user;
	private String name_user;
	public Bill() {
		super();
	}
	public Bill(int bill_id, String bill_date, String bill_product, double root_price, double bill_price,
			String bill_information, int id_user, String name_user) {
		super();
		this.bill_id = bill_id;
		this.bill_date = bill_date;
		this.bill_product = bill_product;
		this.root_price = root_price;
		this.bill_price = bill_price;
		this.bill_information = bill_information;
		this.id_user = id_user;
		this.name_user = name_user;
	}
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public String getBill_product() {
		return bill_product;
	}
	public void setBill_product(String bill_product) {
		this.bill_product = bill_product;
	}
	public double getRoot_price() {
		return root_price;
	}
	public void setRoot_price(double root_price) {
		this.root_price = root_price;
	}
	public double getBill_price() {
		return bill_price;
	}
	public void setBill_price(double bill_price) {
		this.bill_price = bill_price;
	}
	public String getBill_information() {
		return bill_information;
	}
	public void setBill_information(String bill_information) {
		this.bill_information = bill_information;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getName_user() {
		return name_user;
	}
	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

}
