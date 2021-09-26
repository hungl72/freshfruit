package project.controllers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.controllers.dao.NewsDetailDAO;
import project.controllers.model.NewsDetail;

@Service
public class NewsDetailService implements ICRUDService<NewsDetail>{

	@Autowired
	private NewsDetailDAO newsDetailDAO;
	
	@Override
	public List<NewsDetail> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NewsDetail> getAll(int id) {
		List<NewsDetail> list = new ArrayList<NewsDetail>();
		list = newsDetailDAO.getAll(id);
		return list;
	}
	
	public List<NewsDetail> getAllOffset(int offset) {
		List<NewsDetail> listDetail = newsDetailDAO.getAllOffset(offset);
		return listDetail;
	}
	
	@Override
	public int update(NewsDetail t) {
		return newsDetailDAO.update(t);
	}

	@Override
	public int save(NewsDetail t) {
		return newsDetailDAO.save(t);
	}

	@Override
	public int del(int id) {
		return newsDetailDAO.del(id);
	}
	
	@Override
	public int getNumber() {
		return newsDetailDAO.getNumber();
	}
	
	@Override
	public List<NewsDetail> pagination(int offset, int rowCount) {
		List<NewsDetail> listDetail = newsDetailDAO.getAll(offset, rowCount);
		return listDetail;
	}

	@Override
	public NewsDetail findOne(NewsDetail t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsDetail findById(int id) {
		NewsDetail newsDetail = newsDetailDAO.getNewsDetailByNewsDetailId(id);
		return newsDetail;
	}
	
	@Override
	public NewsDetail findByIdId(int id1,int id2) {
		NewsDetail detail = new NewsDetail();
		detail = newsDetailDAO.findByIdId(id1, id2);
		return detail;
	}

	public List<NewsDetail> newDetailThree(){
		List<NewsDetail> listNewsDetail = newsDetailDAO.newDetailThree();
		return listNewsDetail;
	}
	
	@Override
	public List<NewsDetail> getAll(int offset, int rowCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<NewsDetail> search(String key, int offset, int rowCount) {
		List<NewsDetail> listDetail = newsDetailDAO.search(key, offset, rowCount);
		return listDetail;
	}
	
	public int totalRowForSearch(String key) {
		return newsDetailDAO.totalRowForSearch(key);
	}

	@Override
	public List<NewsDetail> getAll(int news_id, int offset, int rowCount) {
		List<NewsDetail> list = new ArrayList<NewsDetail>();
		list = newsDetailDAO.getAll(news_id, offset, rowCount);
		return list;
	}
	
	public List<NewsDetail> news_detail_id() {
		return newsDetailDAO.news_detail_id();
	}
	
	public int totalRow() {
		return newsDetailDAO.totalRow();
	}
	
	public int totalRow(int menu_id) {
		return newsDetailDAO.totalRow(menu_id);
	}

	@Override
	public List<NewsDetail> search(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
