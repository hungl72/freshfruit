package project.controllers.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import project.controllers.model.CommentChildren;

@Repository
public class CommentsChildrenDAO extends AbstractDAO<CommentChildren>{
	
	@Override
	public int insert(int idc, int id, String name, String content, int detail_id) {
		String sql = "INSERT INTO " +
							 "commentchildren "+
							 "(comment_children_content "+ "," +
							 "comment_children_datecreated " + "," +
							 "id_user " + "," +
							 "name_user " + "," +
							 "news_detail_id " + "," +
							 "comment_id) " +
							 "VALUES(?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, content, new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()), id, name, detail_id, idc);
	}
	
	@Override
	public List<CommentChildren> getAll(int id){
		String sql = "SELECT " +
							 "cc.comment_children_id " + "," +
							 "cc.comment_children_content" + "," +
							 "cc.comment_children_datecreated" + "," +
							 "cc.id_user" + "," +
							 "cc.name_user" + "," +
							 "cc.news_detail_id" + "," +
							 "cc.comment_id " +
							 "FROM " +
							 "comment c " +
							 "INNER JOIN " +
							 "commentchildren cc " +
							 "ON c.comment_id = cc.comment_id " +
							 "WHERE " +
							 "cc.comment_id = ?";
		return jdbcTemplate.query(sql, getList(), id);
	}
	
	public int del(int id) {
		String sql = "DELETE FROM commentchildren WHERE comment_children_id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	public List<CommentChildren> findAllCommentChildrennn(int id){
		String sql = "SELECT commentchildren.comment_children_id,commentchildren.comment_children_content,commentchildren.comment_children_datecreated,commentchildren.id_user,commentchildren.name_user,commentchildren.news_detail_id,commentchildren.comment_id FROM commentchildren INNER JOIN comment ON commentchildren.comment_id = comment.comment_id WHERE commentchildren.comment_id = ?";
		return jdbcTemplate.query(sql, getList(), id);
	}

	public int updateCommentC(String cmt, int id) {
		String sql = "UPDATE commentchildren SET comment_children_content = ? WHERE comment_children_id = ?";
		return jdbcTemplate.update(sql, cmt, id);
	}
	
	public int delCommentC( int id) {
		String sql = "DELETE FROM commentchildren WHERE comment_children_id = ?";
		return jdbcTemplate.update(sql, id);
	}
	
	private ResultSetExtractor<List<CommentChildren>> getList(){
		return new ResultSetExtractor<List<CommentChildren>>() {
			
			List<CommentChildren> listComments = new ArrayList<CommentChildren>();
			
			@Override
			public List<CommentChildren> extractData(ResultSet rs) throws SQLException, DataAccessException{
				while(rs.next()) {
				listComments.add(
												 new CommentChildren(rs.getInt("comment_children_id"),
														 rs.getString("comment_children_content"),
														 rs.getString("comment_children_datecreated"),
														 rs.getInt("id_user"), 
														 rs.getString("name_user"),
														 rs.getInt("news_detail_id"),
														 rs.getInt("comment_id"))
												);
			}
				return listComments;
			}
		};
	}
}
