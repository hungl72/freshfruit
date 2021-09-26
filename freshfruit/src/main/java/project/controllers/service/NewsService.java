package project.controllers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controllers.dao.NewsDAO;
import project.controllers.model.News;

@Service
public class NewsService implements ICRUDService<News>{

	@Autowired
	private NewsDAO newsDAO;
	
	@Override
	public List<News> getAll() {
		List<News> listNews = newsDAO.getAll();
		return listNews;
	}

	@Override
	public List<News> pagination(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int update(News t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(News t) {
		newsDAO.save(t);
		return 0;
	}

	@Override
	public int del(int id) {
		newsDAO.del(id);
		return 0;
	}

	@Override
	public News findOne(News t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public News findById(int id) {
		News news = newsDAO.findById(id);
		return news;
	}

	@Override
	public News findByIdId(int id1, int id2) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<News> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<News> getAll(int id) {
		List<News> listNews = newsDAO.getAll(0);
		return listNews;
	}

	@Override
	public List<News> search(String key) {
		return null;
	}

	@Override
	public List<News> getAll(int menu_id, int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
