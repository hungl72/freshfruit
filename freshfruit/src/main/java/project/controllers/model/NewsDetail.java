package project.controllers.model;

import javax.validation.constraints.NotEmpty;

public class NewsDetail {
	private int news_detail_id;
	@NotEmpty
	private String news_detail_name;
	private String news_detail_date;
	private String news_detail_image;
	@NotEmpty
	private String news_detail_description;
	private int news_id;
	private String news_name;
	public NewsDetail() {
		super();
	}
	public NewsDetail(int news_detail_id, String news_detail_name, String news_detail_date, String news_detail_image,
			String news_detail_description, int news_id) {
		super();
		this.news_detail_id = news_detail_id;
		this.news_detail_name = news_detail_name;
		this.news_detail_date = news_detail_date;
		this.news_detail_image = news_detail_image;
		this.news_detail_description = news_detail_description;
		this.news_id = news_id;
	}
	
	public NewsDetail(int news_detail_id, String news_detail_name, String news_detail_date, String news_detail_image,
			String news_detail_description, int news_id, String news_name) {
		super();
		this.news_detail_id = news_detail_id;
		this.news_detail_name = news_detail_name;
		this.news_detail_date = news_detail_date;
		this.news_detail_image = news_detail_image;
		this.news_detail_description = news_detail_description;
		this.news_id = news_id;
		this.news_name = news_name;
	}
	
	public NewsDetail(int news_id, int news_detail_id) {
		super();
		this.news_id = news_id;
		this.news_detail_id = news_detail_id;	
	}
	
	public NewsDetail(String news_detail_name) {
		super();
		this.news_detail_name = news_detail_name;
	}
	
	public NewsDetail(int news_detail_id) {
		super();
		this.news_detail_id = news_detail_id;
	}
	
	public int getNews_detail_id() {
		return news_detail_id;
	}
	public void setNews_detail_id(int news_detail_id) {
		this.news_detail_id = news_detail_id;
	}
	public String getNews_detail_name() {
		return news_detail_name;
	}
	public void setNews_detail_name(String news_detail_name) {
		this.news_detail_name = news_detail_name;
	}
	public String getNews_detail_date() {
		return news_detail_date;
	}
	public void setNews_detail_date(String news_detail_date) {
		this.news_detail_date = news_detail_date;
	}
	public String getNews_detail_image() {
		return news_detail_image;
	}
	public void setNews_detail_image(String news_detail_image) {
		this.news_detail_image = news_detail_image;
	}
	public String getNews_detail_description() {
		return news_detail_description;
	}
	public void setNews_detail_description(String news_detail_description) {
		this.news_detail_description = news_detail_description;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public String getNews_name() {
		return news_name;
	}
	public void setNews_name(String news_name) {
		this.news_name = news_name;
	}
	
	
	
}
