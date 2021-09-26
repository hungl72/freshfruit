package project.controllers.model;

public class CommentChildren {
	private int comment_children_id;
	private String comment_children_content;
	private String comment_children_datecreated;
	private int id_user;
	private String name_user;
	private int news_detail_id;
	private int comment_id;
	public CommentChildren() {
		super();
	}
	public CommentChildren(int comment_children_id, String comment_children_content,
			String comment_children_datecreated, int id_user, String name_user, int news_detail_id, int comment_id) {
		super();
		this.comment_children_id = comment_children_id;
		this.comment_children_content = comment_children_content;
		this.comment_children_datecreated = comment_children_datecreated;
		this.id_user = id_user;
		this.name_user = name_user;
		this.news_detail_id = news_detail_id;
		this.comment_id = comment_id;
	}
	public int getComment_children_id() {
		return comment_children_id;
	}
	public void setComment_children_id(int comment_children_id) {
		this.comment_children_id = comment_children_id;
	}
	public String getComment_children_content() {
		return comment_children_content;
	}
	public void setComment_children_content(String comment_children_content) {
		this.comment_children_content = comment_children_content;
	}
	public String getComment_children_datecreated() {
		return comment_children_datecreated;
	}
	public void setComment_children_datecreated(String comment_children_datecreated) {
		this.comment_children_datecreated = comment_children_datecreated;
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
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	
	
}
