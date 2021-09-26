package project.controllers.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractDAO<T>{
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public List<T> getAll() {
		return null;
	}
	
	public List<T> getAll(int id) {
		return null;
	}
	
	public List<T> getAll(int offset, int rowCount) {
		return null;
	}
	
	public List<T> getAll(int menu_id, int offset, int rowCount) {
		return null;
	}
	
	public int save(T t) {
		return 0;
	}

	public int update(T t) {
		return 0;
	}
	
	public int del(int id) {
		return 0;
	}
	
	public int getNumber() {
		return 0;
	}
	
	public List<T> search(String key) {
		return null;
	}
	
	public int insert(int id, String str, String str2, int id2) {
		return 0;
	}
	
	public int insert(int id1, int id2, String str, String str2, int id3) {
		return 0;
	}
	
	public int totalRowOfPage() {
		return 0;
	}

	public int totalRow() {
		return 0;
	}

	public int totalRow(int menu_id) {
		return 0;
	}

	public T findOne(T t) {
		return null;
	}

	public T findById(int id) {
		return null;
	}
	
	public T findByIdId(int id1,int id2) {
		return null;
	}
	
	
}
