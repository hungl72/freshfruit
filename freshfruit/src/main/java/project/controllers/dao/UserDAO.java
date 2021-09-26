package project.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import project.controllers.model.User;

@Repository
public class UserDAO extends AbstractDAO<User>{
	
	@Override
	public List<User> getAll(){
		String sql = "SELECT * FROM users";
		return jdbcTemplate.query(sql, getList());
	}
	
	public List<User> getAll(int offset) {
		String sql = "SELECT * FROM users LIMIT ?,3";
		return jdbcTemplate.query(sql, getList(), offset);
	}
	
	public List<User> getAll(int offset, int rowCount) {
		String sql = "SELECT * FROM users LIMIT ?,?";
		return jdbcTemplate.query(sql, getList(), offset, rowCount);
	}
	
	public User findByNamePass(String username, String userpass) {
		String sql = "SELECT * FROM users WHERE username = ? AND role_id = 3";
		return jdbcTemplate.queryForObject(sql, rowMapper(), username);
	}
	
	@Override
	public User findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper(), id);
	}
	
	private RowMapper<User> rowMapper(){
		return new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User(
															rs.getInt("id"),
															rs.getString("username"),
															rs.getString("fullname"),
															rs.getString("password"),
															rs.getString("information"),
															rs.getString("image"),
															rs.getString("email"),
															rs.getInt("role_id"),
															rs.getInt("enabled")
														 );
				return user;
			}	
		};
	}
	
	private ResultSetExtractor<List<User>> getList(){
		return new ResultSetExtractor<List<User>>() {
			List<User> listUser = new ArrayList<User>();
			@Override
			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					listUser.add(
											new User(
															rs.getInt("id"),
															rs.getString("username"),
															rs.getString("fullname"),
															rs.getString("password"),
															rs.getString("information"),
															rs.getString("image"),
															rs.getString("email"),
															rs.getInt("role_id"),
															rs.getInt("enabled")
															)
										);
				}
				
				return listUser;
			}
		};
	}
	
	public int signup(String username, String fullname, String password) {
		String sql = "INSERT INTO users (username, fullname, password, information, image, email, role_id, enabled) VALUES (?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, username, fullname, password, "", "", "",3, 1);
	}
	
	public int update(User user) {
		String sql = "UPDATE users SET fullname = ?, password = ?, information = ?, image = ?, email = ? WHERE id = ?";
		return jdbcTemplate.update(sql, user.getFullname(), user.getPassword(), user.getInformation(), user.getImage(), user.getEmail(), user.getId());
	}
	
	public int updateStatus(int status, int id) {
		String sql = "UPDATE users SET enabled = ? WHERE id = ?";
		return jdbcTemplate.update(sql, status, id);
	}
	
	public User checkStatus(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper(), id);
	}
	
	@Override
	public int del(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	@Override
	public int totalRow() {
		final String SQL = "SELECT COUNT(*) totalRow" + " FROM users";
		return jdbcTemplate.queryForObject(SQL, Integer.class);
	}
	
	public User check(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper(),id);
	}
	
	@Override
	public int getNumber() {
		String sql = "SELECT COUNT(*) number FROM users";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
}
