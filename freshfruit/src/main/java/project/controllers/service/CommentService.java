package project.controllers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controllers.dao.CommentsDAO;
import project.controllers.model.Comment;

@Service
public class CommentService implements ICRUDService<Comment>{

	@Autowired
	CommentsDAO commentsDAO;
	
	@Override
	public List<Comment> getAll() {
		List<Comment> listComments = new ArrayList<Comment>();
		listComments = commentsDAO.getAll();
		return listComments;
	}

	@Override
	public List<Comment> getAll(int id) {
		List<Comment> listComments = new ArrayList<Comment>();
		listComments = commentsDAO.getAll(id);
		return listComments;
	}

	@Override
	public int update(Comment t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Comment t) {
		
		return 0;
	}

	@Override
	public int del(int id) {
		return commentsDAO.del(id);
	}

	@Override
	public Comment findOne(Comment t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment findById(int id) {
		Comment comment = new Comment();
		comment = commentsDAO.findById(id);
		return comment;
	}

	@Override
	public Comment findByIdId(int id1, int id2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getAll(int menu_id, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> pagination(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateCommentP(String cmt, int id) {
		return commentsDAO.updateCommentP(cmt, id);
	}
	
	public int delCommentP(int id) {
		return commentsDAO.delCommentP(id);
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
