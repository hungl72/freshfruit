package project.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import project.controllers.model.Contact;

@Repository
public class ContactDAO extends AbstractDAO<Contact>{
	
	@Override
	public int save(Contact c) {
		String sql = "INSERT INTO contact(contact_phone,contact_subject,contact_message) VALUES(?,?,?)";
		return jdbcTemplate.update(sql,c.getContact_phone(),c.getContact_subject(),c.getContact_message());
	}
	
	@Override
	public List<Contact> getAll(int offset) {
		String sql = "SELECT * FROM contact LIMIT ?,3";
		return jdbcTemplate.query(sql, getList(), offset);
	}
	
	public List<Contact> getAll(int offset, int rowCount) {
		String sql = "SELECT * FROM contact LIMIT ?,?";
		return jdbcTemplate.query(sql, getList(), offset, rowCount);
	}
	
	private ResultSetExtractor<List<Contact>> getList(){
		return new ResultSetExtractor<List<Contact>>() {
			
			List<Contact> listContact = new ArrayList<Contact>();
			
			@Override
			public List<Contact> extractData(ResultSet rs) throws SQLException, DataAccessException{
				while(rs.next()) {
					listContact.add(new Contact(rs.getInt("contact_id"), rs.getString("contact_phone"), rs.getString("contact_subject"), rs.getString("contact_message")));
				}
				return listContact;
			}
		};
	}
	
	@Override
	public int del(int id) {
		String sql = "DELETE FROM contact WHERE contact_id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	@Override
	public int totalRow() {
		final String SQL = "SELECT COUNT(*) totalRow" + " FROM contact";
		return jdbcTemplate.queryForObject(SQL, Integer.class);
	}
	
	@Override
	public int getNumber() {
		String sql = "SELECT COUNT(*) number FROM contact";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
}
