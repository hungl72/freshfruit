package project.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import project.controllers.model.Menu;

@Repository
public class MenuDAO extends AbstractDAO<Menu>{
	
	@Override
	public List<Menu> getAll() {
		String sql = "SELECT * FROM menu WHERE parent_id = 0";
		return jdbcTemplate.query(sql, getList());
	}
	
	@Override
	public List<Menu> getAll(int id) {
		String sql = "SELECT * FROM menu WHERE parent_id != 0";
		return jdbcTemplate.query(sql, getList());
	}
	
	private ResultSetExtractor<List<Menu>> getList(){
		return new ResultSetExtractor<List<Menu>>() {
			List<Menu> listMenu = new ArrayList<Menu>();
			@Override
			public List<Menu> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					listMenu.add(
											new Menu(
																rs.getInt("menu_id"),
																rs.getString("menu_name"),
																rs.getInt("parent_id")
														     )
											);
				}
				return listMenu;
			}
		};
	}

	
	public Menu findById(int id) {
		String sql = "SELECT * FROM menu WHERE menu_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapperGetMenuName(), id);
	}

	public Menu findNameMenuByParentId(int id) {
		String sql = "SELECT * FROM menu WHERE menu_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapperGetMenuName(), id);
	}
	
	private RowMapper<Menu> rowMapperGetMenuName(){
		return new RowMapper<Menu>() {
			@Override
			public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
				Menu menu = new Menu(
															rs.getInt("menu_id"),
															rs.getString("menu_name"),
															rs.getInt("parent_id")
														);
				return menu;
			}	
		};
	}
	
	@Override
	public int save(Menu m) {
		String sql = "INSERT INTO menu(menu_name,parent_id) VALUES(?,?)";
		return jdbcTemplate.update(sql,m.getMenu_name(),m.getParent_id());
	}
	
	@Override
	public int del(int id) {
		String sql = "DELETE FROM menu WHERE menu_id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	public Menu getMenuName(int menu_id) {
		String sql = "SELECT * FROM menu WHERE menu_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapperGetMenuName(), menu_id);
	}
}
