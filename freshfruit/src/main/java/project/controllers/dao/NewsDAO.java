package project.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import project.controllers.model.News;

@Repository
public class NewsDAO extends AbstractDAO<News>{
	
	@Override
	public List<News> getAll(){
		String sql = "SELECT * FROM news";
		return jdbcTemplate.query(sql, getList());
	}
	
	public News getNewsName(int news_id) {
		String sql = "SELECT * FROM news WHERE news_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapperGetNewsName(), news_id);
	}
	
	@Override
	public News findById(int id) {
		String sql = "SELECT * FROM news WHERE news_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapperGetNewsName(), id);
	}
	
	public News news_name(int news_detail_id) {
		String sql = "SELECT DISTINCT news.news_name FROM news INNER JOIN news_detail ON news.news_id = news_detail.news_id WHERE news_detail_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapperGetNewsName(), news_detail_id);
	}		
	
	private RowMapper<News> rowMapperGetNewsName(){
		return new RowMapper<News>() {
			@Override
			public News mapRow(ResultSet rs, int rowNum) throws SQLException {
				News menu = new News(
															rs.getString("news_name")
														  );
				return menu;
			}	
		};
	}
	
	private ResultSetExtractor<List<News>> getList(){
		return new ResultSetExtractor<List<News>>() {
			List<News> listNews = new ArrayList<News>();
			@Override
			public List<News> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					listNews.add(new News(
																rs.getInt("news_id"),
																rs.getString("news_name")
															 )
										);
				}
				return listNews;
			}
		};
	}

	@Override
	public int save(News n) {
		String sql = "INSERT INTO news (news_name) VALUES(?)";
		return jdbcTemplate.update(sql,n.getNews_name());
	}
	
	@Override
	public int del(int id) {
		String sql = "DELETE FROM news WHERE news_id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	/*
	public News findById(int news_id) {
		String sql = "SELECT * FROM news WHERE news_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper(), news_id);
	}
	
	private RowMapper<News> rowMapper(){
		return new RowMapper<News>() {
			@Override
			public News mapRow(ResultSet rs, int rowNum) throws SQLException {
					News news = new News(
																rs.getInt("news_id"),
																rs.getString("news_name")
																);
				return news;
			}	
		};
	}
	
	@Override
	public News findById(int news_id) {
		final String sql = "SELECT * FROM news WHERE news_id = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(News.class), news_id);
	}*/
	
}
