package project.controllers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controllers.dao.MenuDAO;
import project.controllers.model.Menu;

@Service
public class MenuService implements ICRUDService<Menu>{

	@Autowired
	private MenuDAO menuDAO;
	
	@Override
	public List<Menu> getAll() {
		List<Menu> listMenu = menuDAO.getAll();
		return listMenu;
	}
	
	@Override
	public List<Menu> getAll(int id) {
		List<Menu> listMenu = menuDAO.getAll(id);
		return listMenu;
	}

	@Override
	public int update(Menu t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Menu t) {
		menuDAO.save(t);
		return 0;
	}

	@Override
	public int del(int id) {
		menuDAO.del(id);
		return 0;
	}
	
	@Override
	public Menu findOne(Menu t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu findById(int id) {
		Menu menu = menuDAO.findById(id);
		return menu;
	}

	public Menu findNameMenuByParentId(int id) {
		Menu menu = menuDAO.findNameMenuByParentId(id);
		return menu;
	}
	
	@Override
	public Menu findByIdId(int id1, int id2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getAll(int menu_id, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> pagination(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
