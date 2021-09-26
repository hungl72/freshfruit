package project.controllers.model;

public class News {
	private int news_id;
	private String news_name;
	public News() {
		super();
	}
	public News(int news_id, String news_name) {
		super();
		this.news_id = news_id;
		this.news_name = news_name;
	}
	
	public News(String news_name) {
		super();
		this.news_name = news_name;
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
