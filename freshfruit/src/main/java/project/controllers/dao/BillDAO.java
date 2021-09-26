package project.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import project.controllers.model.Bill;

@Repository
public class BillDAO extends AbstractDAO<Bill>{

	public int insert(String bill_date,String bill_product, double root_price, double bill_price, String bill_information, int id_user, String name_user) {
		String sql = "INSERT INTO bill(bill_date,bill_product,root_price,bill_price,bill_information, id_user, name_user) VALUES(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,bill_date,bill_product,root_price,bill_price,bill_information,id_user,name_user);
	}
	
	@Override
	public List<Bill> getAll() {
		String sql = "SELECT * FROM bill";
		return jdbcTemplate.query(sql, getList());
	}
	
	@Override
	public List<Bill> getAll(int id_user) {
		String sql = "SELECT * FROM bill WHERE id_user = ?";
		return jdbcTemplate.query(sql, getList(), id_user);
	}
	
	public List<Bill> getAll(int offset, int rowCount) {
		String sql = "SELECT * FROM bill LIMIT ?,?";
		return jdbcTemplate.query(sql, getList(), offset, rowCount);
	}
	
	private ResultSetExtractor<List<Bill>> getList(){
		return new ResultSetExtractor<List<Bill>>() {
			List<Bill> listBill = new ArrayList<Bill>();
			@Override
			public List<Bill> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					listBill.add(new Bill(
														rs.getInt("bill_id"),
														rs.getString("bill_date"),
														rs.getString("bill_product"),
														rs.getDouble("root_price"),
														rs.getDouble("bill_price"),
														rs.getString("bill_information"),
														rs.getInt("id_user"),
														rs.getString("name_user")
													  )
									   );
				}
				return listBill;
			}
		};
	}
	
	@Override
	public int totalRow() {
		final String SQL = "SELECT COUNT(*) totalRow" + " FROM bill";
		return jdbcTemplate.queryForObject(SQL, Integer.class);
	}
	
}
