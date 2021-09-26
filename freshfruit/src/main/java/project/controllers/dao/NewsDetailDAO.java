package project.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import project.controllers.model.NewsDetail;
import project.controllers.service.NewsService;

@Repository
public class NewsDetailDAO extends AbstractDAO<NewsDetail>{

	@Autowired
	private NewsService newsService;
	
	@Override
	public NewsDetail findByIdId(int news_id,int news_detail_id){
		String sql = "SELECT * FROM news_detail INNER JOIN news ON news_detail.news_id = news.news_id WHERE news_detail.news_id = ? AND news_detail.news_detail_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper(), news_id, news_detail_id);
	}
	
	public List<NewsDetail> search(String key, int offset, int rowCount) {
		String sql = "SELECT * FROM news_detail WHERE 	news_detail_name LIKE ? LIMIT ?,?";
		return jdbcTemplate.query(sql, getList(), "%"+key+"%", offset, rowCount);
	}
	
	public int totalRowForSearch(String key) {
		String sql = "SELECT COUNT(*) totalRow FROM news_detail WHERE news_detail_name LIKE ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, "%"+key+"%");
	}
	
	public List<NewsDetail> getAll(int offset, int rowCount) {
		String sql = "SELECT * FROM news_detail LIMIT ?,?";
		return jdbcTemplate.query(sql, getList(), offset, rowCount);
	}
	
	public List<NewsDetail> getAllOffset(int offset) {
		String sql = "SELECT * FROM news_detail LIMIT ?,3";
		return jdbcTemplate.query(sql, getList(), offset);
	}
	
	@Override
	public int save(NewsDetail nd) {
		final String SQL = "INSERT INTO news_detail (news_detail_name,news_detail_date,news_detail_image,news_detail_description,news_id, news_name) VALUES(?,?,?,?,?,?)";
		return jdbcTemplate.update(SQL, nd.getNews_detail_name(), new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()), nd.getNews_detail_image(), nd.getNews_detail_description(), nd.getNews_id(), newsService.findById(nd.getNews_id()).getNews_name());
	}
	
	@Override
	public int update(NewsDetail nd) {
		String sql = "UPDATE news_detail SET news_detail_name = ?, news_detail_date = ?, news_detail_image = ?, news_detail_description = ?, news_id = ?, news_name = ? WHERE news_detail_id = ?";
		return jdbcTemplate.update(sql, nd.getNews_detail_name(), nd.getNews_detail_date(), nd.getNews_detail_image(), nd.getNews_detail_description(), nd.getNews_id(), nd.getNews_name(), nd.getNews_detail_id());
	}
	
	@Override
	public int del(int id) {
		final String SQL = "DELETE FROM news_detail WHERE news_detail_id = ?";
		return jdbcTemplate.update(SQL, id);
	}
	
	public List<NewsDetail> newDetailThree() {
		String sql = "SELECT * FROM news_detail ORDER BY news_detail_id DESC LIMIT 3";
		return jdbcTemplate.query(sql, getList());
	}
	
	public NewsDetail getNewsDetailByNewsDetailId(int id) {
		String sql = "SELECT * FROM news_detail WHERE news_detail_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper(), id);
	}
	
	private RowMapper<NewsDetail> rowMapper(){
		return new RowMapper<NewsDetail>() {

			@Override
			public NewsDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				NewsDetail detail = new NewsDetail(
															rs.getInt("news_detail_id"),
															rs.getString("news_detail_name"),
															rs.getString("news_detail_date"),
															rs.getString("news_detail_image"),
															rs.getString("news_detail_description"),
															rs.getInt("news_id"),
															rs.getString("news_name")
															);
				return detail;
			}
			
		};
	}
	
	@Override
	public List<NewsDetail> getAll(int news_id){
		String sql = "SELECT news_detail_id, news_detail_name, news_detail_date, news_detail_image, news_detail_description, news_detail.news_id FROM news INNER JOIN news_detail ON news.news_id = news_detail.news_id WHERE news_detail.news_id = ?";
		return jdbcTemplate.query(sql, getList(), news_id);
	}
	
	public List<NewsDetail> getAll(int news_id, int offset, int rowCount) {
		String sql = "SELECT * FROM news_detail WHERE news_id  = ? LIMIT ?,?";
		return jdbcTemplate.query(sql, getList(),news_id, offset, rowCount);
	}
	
	private ResultSetExtractor<List<NewsDetail>> getList(){
		return new ResultSetExtractor<List<NewsDetail>>() {
			List<NewsDetail> listDetail = new ArrayList<NewsDetail>();
			@Override
			public List<NewsDetail> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					listDetail.add(new NewsDetail(
																rs.getInt("news_detail_id"),
																rs.getString("news_detail_name"),
																rs.getString("news_detail_date"),
																rs.getString("news_detail_image"),
																rs.getString("news_detail_description"),
																rs.getInt("news_id"),
																rs.getString("news_name")
																));
				}
				return listDetail;
			}
		};
	}
	
	public List<NewsDetail> news_detail_id(){
		String sql = "SELECT DISTINCT news_detail_id FROM comment";
		return jdbcTemplate.query(sql, getListNewsDetailId());
	}
	
	public List<NewsDetail> news_id(){
		String sql = "SELECT DISTINCT news_detail_id FROM comment";
		return jdbcTemplate.query(sql, getListNewsDetailId());
	}
	
	public NewsDetail newsDetailName(int news_detail_id) {
		String sql = "SELECT news_detail.news_detail_name FROM news INNER JOIN news_detail ON news.news_id = news_detail.news_id AND news_detail.news_detail_id = ?";
		return jdbcTemplate.queryForObject(sql, getNewsDetailName(), news_detail_id);
	}
	
	private RowMapper<NewsDetail> getNewsDetailName(){
		return new RowMapper<NewsDetail>() {

			@Override
			public NewsDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				NewsDetail detail = new NewsDetail(
																				rs.getString("news_detail_name")
																				);
				return detail;
			}
			
		};
	}
	
	private ResultSetExtractor<List<NewsDetail>> getListNewsDetailId(){
		return new ResultSetExtractor<List<NewsDetail>>() {
			List<NewsDetail> listDetail = new ArrayList<NewsDetail>();
			@Override
			public List<NewsDetail> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					listDetail.add(new NewsDetail(
																	rs.getInt("news_detail_id")
																));
				}
				return listDetail;
			}
		};
	}
	
	public int totalRow() {
		final String SQL = "SELECT COUNT(*) totalRow" + " FROM news_detail";
		return jdbcTemplate.queryForObject(SQL, Integer.class);
	}
	
	public int totalRow(int news_id) {
		final String SQL = "SELECT COUNT(*) totalRow" + " FROM news_detail WHERE news_id = ?";
		return jdbcTemplate.queryForObject(SQL, Integer.class, news_id);
	}
	
	@Override
	public int getNumber() {
		String sql = "SELECT COUNT(*) number FROM news_detail";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
}
