package project.controllers.service;

import java.util.List;

public interface ICRUDService<T> {
	List<T> getAll();
	
	List<T> getAll(int id);
	
	List<T> search(String key);
	
	List<T> pagination(int offset, int rowCount);
	
	int update(T t);
	
	int save(T t);
	
	int del(int id);
	
	int getNumber();
	
	T findOne(T t);
	
	T findById(int id);
	
	T findByIdId(int id1,int id2);

	List<T> getAll(int offset, int rowCount);
	
	List<T> getAll(int menu_id, int offset, int rowCount);
}
