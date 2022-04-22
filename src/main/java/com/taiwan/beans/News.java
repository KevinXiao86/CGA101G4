package com.taiwan.beans;

import java.sql.Timestamp;

public class News {
	private Integer newsId;
	private String title;
	private String content;
	private Timestamp createDate;
	private String img;
	public News() {
		super();
	}
	public News(Integer newsId, String title, String content, Timestamp createDate, String img) {
		super();
		this.newsId = newsId;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.img = img;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", title=" + title + ", content=" + content + ", createDate=" + createDate + ", img="
				+ img + "]";
	}
	
}
