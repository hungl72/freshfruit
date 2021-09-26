package project.controllers.model;

import javax.validation.constraints.NotEmpty;

public class Contact {
	private int contact_id;
	
	@NotEmpty
	private String contact_phone;
	
	@NotEmpty
	private String contact_subject;
	
	@NotEmpty
	private String contact_message;
	
	public Contact() {
		super();
	}

	public Contact(int contact_id, String contact_phone, String contact_subject,
			String contact_message) {
		super();
		this.contact_id = contact_id;
		this.contact_phone = contact_phone;
		this.contact_subject = contact_subject;
		this.contact_message = contact_message;
	}

	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getContact_subject() {
		return contact_subject;
	}

	public void setContact_subject(String contact_subject) {
		this.contact_subject = contact_subject;
	}

	public String getContact_message() {
		return contact_message;
	}

	public void setContact_message(String contact_message) {
		this.contact_message = contact_message;
	}
	
	
}
