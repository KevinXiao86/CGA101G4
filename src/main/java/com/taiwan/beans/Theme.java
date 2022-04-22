package com.taiwan.beans;

import java.sql.Timestamp;

public class Theme {

	private Integer themeId;
	private String title;
	private String content;
	private Timestamp createDate;
	private String img;

	public Theme() {
		super();
	}

	public Theme(Integer themeId, String title, String content, Timestamp createDate, String img) {
		super();
		this.themeId = themeId;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.img = img;
	}

	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
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

	public void setDate(Timestamp createDate) {
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
		return "Theme [themeId=" + themeId + ", title=" + title + ", content=" + content + ", createDate=" + createDate + ", img="
				+ img + "]";
	}

}
