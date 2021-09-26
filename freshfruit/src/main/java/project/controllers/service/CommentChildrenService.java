package project.controllers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controllers.dao.CommentsChildrenDAO;
import project.controllers.model.CommentChildren;

@Service
public class CommentChildrenService implements ICRUDService<CommentChildren>{

	@Autowired
	private CommentsChildrenDAO commentChildrenDAO;
	
	@Override
	public List<CommentChildren> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentChildren> getAll(int id) {
		List<CommentChildren> listComments = commentChildrenDAO.getAll();
		return listComments;
	}

	@Override
	public int update(CommentChildren t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(CommentChildren t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(int id) {
		return commentChildrenDAO.del(id);
	}

	@Override
	public CommentChildren findOne(CommentChildren t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentChildren findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentChildren findByIdId(int id1, int id2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentChildren> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentChildren> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentChildren> getAll(int menu_id, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentChildren> pagination(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int updateCommentC(String cmt, int id) {
		return commentChildrenDAO.updateCommentC(cmt, id);
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

}
