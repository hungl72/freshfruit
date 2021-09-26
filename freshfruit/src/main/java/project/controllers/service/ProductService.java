package project.controllers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controllers.dao.MenuDAO;
import project.controllers.dao.ProductDAO;
import project.controllers.model.Product;

@Service
public class ProductService implements ICRUDService<Product>{

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private MenuDAO menuDAO;
	
	@Override
	public List<Product> getAll() {
		List<Product> listProduct = productDAO.getAll();
		return listProduct;
	}

	@Override
	public int update(Product t) {
		return productDAO.update(t);
	}

	@Override
	public int save(Product t) {
		return productDAO.save(t);
	}

	@Override
	public int del(int id) {
		return productDAO.del(id);
	}
	
	@Override
	public int getNumber() {
		return productDAO.getNumber();
	}
	
	public List<Product> pagination(int offset, int rowCount) {
		List<Product> listProduct = productDAO.getAll(offset, rowCount);
		return listProduct;
	}
	
	public List<Product> listProductSameCategory(int product_id, int menu_id){
		List<Product> listProduct = productDAO.getProductSameCategory(product_id, menu_id);
		return listProduct;
	}

	@Override
	public Product findOne(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findById(int id) {
		Product p = productDAO.getProductByProductId(id);
		return p;
	}
	
	@Override
	public Product findByIdId(int id1, int id2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Product getProductByProductId(int id) {
		Product product = productDAO.getProductByProductId(id);
		return product;
	}
	
	@Override
	public List<Product> getAll(int offset, int rowCount) {
		return null;
	}

	@Override
	public List<Product> getAll(int menu_id, int offset, int rowCount) {
		List<Product> listProduct = productDAO.getAll(menu_id, offset, rowCount);
		return listProduct;
	}
	
	@Override
	public List<Product> getAll(int offset) {
		List<Product> listProduct = productDAO.getAll(offset);
		return listProduct;
	}

	public List<Product> search(String key, int offset, int rowCount){
		List<Product> listProduct = productDAO.search(key, offset, rowCount);
		return listProduct;
	}
	
	public String findMenuName(int menu_id) {
		return menuDAO.getMenuName(menu_id).getMenu_name();
	}
	
	public List<Product> newProductThree(){
		List<Product> listProduct = productDAO.newProductThree();
		return listProduct;
	}
	
	public int totalRowNotMenuId() {
		return productDAO.totalRow();
	}
	
	public int totalRowForSearch(String key) {
		return productDAO.totalRowForSearch(key);
	}
	
	public int totalRowOfPage(int page) {
		return totalRowNotMenuId() - (page - 1) * 3;
	}
	
	public int totalRow(int menu_id) {
		return productDAO.totalRow(menu_id);
	}

	@Override
	public List<Product> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}
}
