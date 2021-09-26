package project.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import project.controllers.model.Product;
import project.controllers.service.MenuService;

@Repository
public class ProductDAO extends AbstractDAO<Product>{
	
	@Autowired
	private MenuService menuService;
	
	public List<Product> getAll() {
		String sql = "SELECT * FROM product";
		return jdbcTemplate.query(sql, getList());
	}
	
	public List<Product> getAll(int offset) {
		String sql = "SELECT * FROM product LIMIT ?,3";
		return jdbcTemplate.query(sql, getList(), offset);
	}
	
	public List<Product> getProductSameCategory(int product_id, int menu_id) {
		String sql = "SELECT * FROM product WHERE menu_id = ? AND product_id != ? ORDER BY product_id DESC LIMIT 3";
		return jdbcTemplate.query(sql, getList(), menu_id, product_id);
	}
	
	public List<Product> getAll(int offset, int rowCount) {
		String sql = "SELECT * FROM product LIMIT ?,?";
		return jdbcTemplate.query(sql, getList(), offset, rowCount);
	}
	
	@Override
	public List<Product> getAll(int menu_id, int offset, int rowCount) {
		final String SQL = "SELECT * FROM product WHERE menu_id = ? LIMIT ?,?";
		return jdbcTemplate.query(SQL, getList(), menu_id, offset, rowCount);
	}
	
	@Override
	public int save(Product p) {
		final String SQL = "INSERT INTO product (product_name,product_price,product_promotion,product_description,product_image, menu_id, menu_name) VALUES(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(SQL, p.getProduct_name(), p.getProduct_price(), p.getProduct_promotion(), p.getProduct_description(), p.getProduct_image(), p.getMenu_id(), menuService.findById(p.getMenu_id()).getMenu_name() + " - " + menuService.findNameMenuByParentId(p.getParent_id()).getMenu_name());
	}
	
	@Override
	public int update(Product p) {
		String sql = "UPDATE product SET product_name = ?, product_price = ?, product_promotion = ?, product_description = ?, product_image = ?, menu_id = ?, menu_name = ? WHERE product_id = ?";
		return jdbcTemplate.update(sql, p.getProduct_name(), p.getProduct_price(), p.getProduct_promotion(), p.getProduct_description(), p.getProduct_image(), p.getMenu_id(), p.getMenu_name(), p.getProduct_id());
	}
	
	@Override
	public int del(int id) {
		final String SQL = "DELETE FROM product WHERE product_id = ?";
		return jdbcTemplate.update(SQL, id);
	}
	
	public List<Product> search(String key, int offset, int rowCount) {
		String sql = "SELECT * FROM product WHERE 	product_name LIKE ? LIMIT ?,?";
		return jdbcTemplate.query(sql, getList(), "%"+key+"%", offset, rowCount);
	}
	
	public List<Product> newProductThree() {
		String sql = "SELECT * FROM product ORDER BY product_id DESC LIMIT 3";
		return jdbcTemplate.query(sql, getList());
	}
	
	public int totalRowForSearch(String key) {
		String sql = "SELECT COUNT(*) totalRow FROM product WHERE product_name LIKE ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, "%"+key+"%");
	}
	
	@Override
	public int totalRowOfPage() {
		final String SQL = "SELECT COUNT(*) totalRow" + " FROM product";
		return jdbcTemplate.queryForObject(SQL, Integer.class);
	}
	
	@Override
	public int totalRow() {
		final String SQL = "SELECT COUNT(*) totalRow" + " FROM product";
		return jdbcTemplate.queryForObject(SQL, Integer.class);
	}
	
	@Override
	public int totalRow(int menu_id) {
		final String SQL = "SELECT COUNT(*) totalRow" + " FROM product WHERE menu_id = ?";
		return jdbcTemplate.queryForObject(SQL, Integer.class, menu_id);
	}
	
	private ResultSetExtractor<List<Product>> getList(){
		return new ResultSetExtractor<List<Product>>() {
			
			List<Product> listProduct = new ArrayList<Product>();
			
			@Override
			public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException{
				while(rs.next()) {
					listProduct.add(new Product(
																		rs.getInt("product_id"),
																		rs.getString("product_name"),
																		rs.getDouble("product_price"),
																		rs.getDouble("product_promotion"),
																		rs.getString("product_description"),
																		rs.getString("product_image"),
																		rs.getInt("menu_id"),
																		rs.getString("menu_name")));
				}
				return listProduct;
			}
		};
	}
	
	public Product getProductByProductId(int id) {
		String sql = "SELECT * FROM product WHERE product_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapperGetProductByProductId(), id);
	}
	
	private RowMapper<Product> rowMapperGetProductByProductId(){
		return new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					Product product = new Product(
																			rs.getInt("product_id"),
																			rs.getString("product_name"),
																			rs.getDouble("product_price"),
																			rs.getDouble("product_promotion"),
																			rs.getString("product_description"),
																			rs.getString("product_image"),
																			rs.getInt("menu_id"),
																			rs.getString("menu_name")
																		);
				return product;
			}	
		};
	}
	
	@Override
	public int getNumber() {
		String sql = "SELECT COUNT(*) number FROM product";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
}
