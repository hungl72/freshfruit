package project.controllers.model;

import javax.validation.constraints.NotEmpty;

public class Payment {
	
	private int bill_id;
	
	private String bill_date;	
	
	@NotEmpty
	private String bill_address;
	
	@NotEmpty
	private String bill_phone;
	
	@NotEmpty
	private String description;
	
	private double bill_price;
	
	public Payment() {
		super();
	}
	public Payment(int bill_id, String bill_date, String bill_address, String bill_phone, String description,
			double bill_price) {
		super();
		this.bill_id = bill_id;
		this.bill_date = bill_date;
		this.bill_address = bill_address;
		this.bill_phone = bill_phone;
		this.description = description;
		this.bill_price = bill_price;
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
	public String getBill_address() {
		return bill_address;
	}
	public void setBill_address(String bill_address) {
		this.bill_address = bill_address;
	}
	public String getBill_phone() {
		return bill_phone;
	}
	public void setBill_phone(String bill_phone) {
		this.bill_phone = bill_phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getBill_price() {
		return bill_price;
	}
	public void setBill_price(double bill_price) {
		this.bill_price = bill_price;
	}
	
	
}
