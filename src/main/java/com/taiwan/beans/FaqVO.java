package com.taiwan.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class FaqVO implements Serializable{
	private Integer faqId;
	private String title;
	private String content;
	private Timestamp createDate;
	
	public FaqVO(Integer faqId, String title, String content, Timestamp createDate) {
		super();
		this.faqId = faqId;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
	}
	
	public FaqVO() {
		super();
	}

	public Integer getFaqId() {
		return faqId;
	}

	public void setFaqId(Integer faqId) {
		this.faqId = faqId;
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

	@Override
	public String toString() {
		return "Faq [faqId=" + faqId + ", title=" + title + ", content=" + content + ", createDatedate=" + createDate + "]";
	}
	
	
}
