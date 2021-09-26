package project.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import project.controllers.model.Comment;

@Repository
public class CommentsDAO extends AbstractDAO<Comment> {
	
	@Override
	public int insert(int id, String name, String content, int detail_id) {
		String sql = "INSERT INTO " +
							 "comment	"+
							 "(comment_content "+ "," +
							 "comment_datecreated " + "," +
							 "id_user " + "," +
							 "name_user " + "," +
							 "news_detail_id) " +
							 "VALUES(?,?,?,?,?)";
		return jdbcTemplate.update(sql, content, new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()), id, name, detail_id);
	}
	


	@Override
	public List<Comment> getAll(){
		String sql = "SELECT * FROM comment";
		return jdbcTemplate.query(sql, getListComment());
	}
	
	@Override
	public List<Comment> getAll(int news_detail_id){
		String sql = "SELECT * FROM comment WHERE news_detail_id = ?";
		return jdbcTemplate.query(sql, getListComment(), news_detail_id);
	}
	
	public List<Comment> newsDetailId(){
		String sql = "SELECT DISTINCT news_detail_id FROM comment";
		return jdbcTemplate.query(sql, getListNewsDetailId());
	}
	
	public List<Comment> findAllCommentByNewsDetailId(int news_detail_id){
		String sql = "SELECT * FROM comment WHERE news_detail_id = ?";
		return jdbcTemplate.query(sql, getListComment(), news_detail_id);
	}
	
	public int del(int id) {
		String sql = "DELETE FROM comment WHERE comment_id = ?";
		return jdbcTemplate.update(sql, id);
	}
		
	public int updateCommentP(String cmt, int id) {
		String sql = "UPDATE comment SET comment_content = ? WHERE comment_id = ?";
		return jdbcTemplate.update(sql, cmt, id);
	}
	
	public int delCommentP(int id) {
		String sql = "DELETE FROM comment WHERE comment_id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	private ResultSetExtractor<List<Comment>> getListComment(){
		return new ResultSetExtractor<List<Comment>>() {
			List<Comment> listComments = new ArrayList<Comment>();
			@Override
			public List<Comment> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					listComments.add(
													 new Comment(
																				rs.getInt("comment_id"),
																				rs.getString("comment_content"),
																				rs.getString("comment_datecreated"),
																				rs.getInt("id_user"),
																				rs.getString("name_user"),
																				rs.getInt("news_detail_id")
																			  )
													);
				}
				
				return listComments;
			}
		};
	}
	
	private ResultSetExtractor<List<Comment>> getListNewsDetailId(){
		return new ResultSetExtractor<List<Comment>>() {
			List<Comment> listComments = new ArrayList<Comment>();
			@Override
			public List<Comment> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					listComments.add(
													 new Comment(
																				rs.getInt("news_detail_id")
																			  )
													);
				}
				return listComments;
			}
		};
	}
	
	@Override
	public Comment findById(int id){
		String sql = "SELECT * FROM comment WHERE comment_id = ?";
		return jdbcTemplate.queryForObject(sql, rowMapper(), id);
	}

	private RowMapper<Comment> rowMapper(){
		return new RowMapper<Comment>() {

			@Override
			public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comment comment = new Comment(
																				rs.getInt("comment_id"),
																				rs.getString("comment_content"),
																				rs.getString("comment_datecreated"),
																				rs.getInt("id_user"),
																				rs.getString("name_user"),
																				rs.getInt("news_detail_id")
																			  );
				return comment;
			}
			
		};
	}
	
	public int totalRow() {
		final String SQL = "SELECT comment_id FROM comment ORDER BY comment_id DESC LIMIT 1";
		return jdbcTemplate.queryForObject(SQL, Integer.class);
	}
}
