package project.controllers.model;

public class Menu {
	private int menu_id;
	private String menu_name;
	private int parent_id;
	
	public Menu() {
		super();
	}

	public Menu(int menu_id, String menu_name, int parent_id) {
		super();
		this.menu_id = menu_id;
		this.menu_name = menu_name;
		this.parent_id = parent_id;
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

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	
	
}
