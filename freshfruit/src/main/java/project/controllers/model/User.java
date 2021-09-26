package project.controllers.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class User {

	private int id;
	
	@NotEmpty
	@Length(min = 4, max = 255)
	private String username;
	
	@NotEmpty
	private String fullname;
	
	private String password;
	
	private String information;
	
	private String image;
	
	private String email;
	
	private int role_id;
	
	private int enabled;

	public User() {
		
	}
	
	public User(int id, @NotEmpty @Length(min = 4, max = 255) String username, @NotEmpty String fullname,
			String password, String information, String image, String email, int role_id, int enabled) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.information = information;
		this.image = image;
		this.email = email;
		this.role_id = role_id;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	
}
