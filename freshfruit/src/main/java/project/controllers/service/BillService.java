package project.controllers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controllers.dao.BillDAO;
import project.controllers.model.Bill;

@Service
public class BillService implements ICRUDService<Bill>{
	
	@Autowired
	private BillDAO billDAO;

	@Override
	public List<Bill> getAll() {
		List<Bill> listBill = billDAO.getAll();
		return listBill;
	}

	@Override
	public List<Bill> getAll(int id) {
		List<Bill> listBill = billDAO.getAll(id);
		return listBill;
	}

	@Override
	public List<Bill> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Bill b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Bill b) {
		return billDAO.insert(b.getBill_date(), b.getBill_product(), b.getRoot_price(), b.getBill_price(), b.getBill_information(), b.getId_user(), b.getName_user());
	}

	@Override
	public int del(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bill findOne(Bill t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill findByIdId(int id1, int id2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> getAll(int menu_id, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> pagination(int offset, int rowCount) {
		List<Bill> listBill = billDAO.getAll(offset, rowCount);
		return listBill;
	}
	
	public int totalRow() {
		return billDAO.totalRow();
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
