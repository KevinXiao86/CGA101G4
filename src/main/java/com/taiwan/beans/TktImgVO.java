package com.taiwan.beans;

import java.io.Serializable;

public class TktImgVO implements Serializable{
	private Integer tktImgId;
	private String img;
	private Integer tktId;
	
	public TktImgVO(Integer tktImgId, String img, Integer tktId) {
		super();
		this.tktImgId = tktImgId;
		this.img = img;
		this.tktId = tktId; 
	}
	
	public TktImgVO() {
		super();
	}

	public Integer getTktImgId() {
		return tktImgId;
	}

	public void setTktImgId(Integer tktImgId) {
		this.tktImgId = tktImgId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getTktId() {
		return tktId;
	}

	public void setTktId(Integer tktId) {
		this.tktId = tktId;
	}

	@Override
	public String toString() {
		return "TktImg [tktImgId=" + tktImgId + ", img=" + img + ", tktId=" + tktId + "]";
	}
	
}
