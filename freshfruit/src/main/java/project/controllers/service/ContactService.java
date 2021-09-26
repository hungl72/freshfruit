package project.controllers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controllers.dao.ContactDAO;
import project.controllers.model.Contact;

@Service
public class ContactService implements ICRUDService<Contact>{

	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public List<Contact> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Contact t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Contact t) {
		return contactDAO.save(t);
	}

	@Override
	public int del(int id) {
		return contactDAO.del(id);
	}
	
	@Override
	public int getNumber() {
		return contactDAO.getNumber();
	}

	@Override
	public Contact findOne(Contact t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact findByIdId(int id1, int id2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getAll(int offset) {
		List<Contact> listContact = contactDAO.getAll(offset);
		return listContact;
	}

	@Override
	public List<Contact> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getAll(int menu_id, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> pagination(int offset, int rowCount) {
		List<Contact> listContact = contactDAO.getAll(offset, rowCount);
		return listContact;
	}
	public int totalRow() {
		return contactDAO.totalRow();
	}
	

}
