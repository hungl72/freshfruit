package project.controllers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controllers.dao.UserDAO;
import project.controllers.model.User;

@Service
public class UserService implements ICRUDService<User>{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<User> getAll() {
		List<User> listUser = userDAO.getAll();
		return listUser;
	}

	@Override
	public List<User> getAll(int offset) {
		List<User> listUser = userDAO.getAll(offset);
		return listUser;
	}

	@Override
	public int update(User t) {
		return userDAO.update(t);
	}

	@Override
	public int save(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(int id) {
		return userDAO.del(id);
	}
	
	@Override
	public int getNumber() {
		return userDAO.getNumber();
	}
	
	@Override
	public User findOne(User u) {
		User user;
		try {
			user = userDAO.findByNamePass(u.getUsername(), u.getPassword());
		}catch(Exception e) {
			user = null;
		}
		return user;
	}

	@Override
	public User findById(int id) {
		User u = userDAO.findById(id);
		return u;
	}

	@Override
	public User findByIdId(int id1, int id2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll(int menu_id, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public int signup(String username, String fullname, String password) {
		return userDAO.signup(username, fullname, password);
	}
	
	public int totalRow() {
		return userDAO.totalRow();
	}
	
	@Override
	public List<User> pagination(int offset, int rowCount) {
		List<User> listUser = userDAO.getAll(offset, rowCount);
		return listUser;
	}

	public int  updateStatus(int status, int id) {
		return userDAO.updateStatus(status, id);
	}
	
	public User checkStatus(int id) {
		return userDAO.checkStatus(id);
	}
	
	public User check(int id) {
		return userDAO.check(id);
	}
	
}
