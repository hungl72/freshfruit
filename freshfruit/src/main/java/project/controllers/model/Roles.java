package project.controllers.model;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class Roles {

	private int id;

	@NotEmpty
	@Length(min = 5, max = 50)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Roles(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Roles() {
		super();
	}
}
