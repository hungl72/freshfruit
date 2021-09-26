package project.controllers.model;

public class Comment {
	private int comment_id;
	private String comment_content;
	private String comment_datecreated;
	private int id_user;
	private String name_user;
	private int news_detail_id;
	
	public Comment() {
		super();
	}

	public Comment(int comment_id, String comment_content, String comment_datecreated, int id_user, String name_user,
			int news_detail_id) {
		super();
		this.comment_id = comment_id;
		this.comment_content = comment_content;
		this.comment_datecreated = comment_datecreated;
		this.id_user = id_user;
		this.name_user = name_user;
		this.news_detail_id = news_detail_id;
	}

	public Comment(int news_detail_id) {
		super();
		this.news_detail_id = news_detail_id;
	}
	
	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_datecreated() {
		return comment_datecreated;
	}

	public void setComment_datecreated(String comment_datecreated) {
		this.comment_datecreated = comment_datecreated;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getName_user() {
		return name_user;
	}

	public void setName_user(String name_user) {
		this.name_user = name_user;
	}

	public int getNews_detail_id() {
		return news_detail_id;
	}

	public void setNews_detail_id(int news_detail_id) {
		this.news_detail_id = news_detail_id;
	}
	
	

}
