package com.taiwan.beans;

import com.taiwan.service.TicketService;
import com.taiwan.service.impl.TicketServiceImpl;
import com.taiwan.utils.ControllerUtil;

public class TktItem {
	private Integer tktId;
	private Integer tktOrderId;
	private Integer amount;
	private Integer used;
	private Integer score;
	private String content;

	public TktItem() {
	}

	public TktItem(Integer tktId, Integer tktOrderId, Integer amount, Integer used, Integer score, String content) {
		super();
		this.tktId = tktId;
		this.tktOrderId = tktOrderId;
		this.amount = amount;
		this.used = used;
		this.score = score;
		this.content = content;
	}

	public Integer getTktId() {
		return tktId;
	}

	public void setTktId(Integer tktId) {
		this.tktId = tktId;
	}

	public Integer getTktOrderId() {
		return tktOrderId;
	}

	public void setTktOrderId(Integer tktOrderId) {
		this.tktOrderId = tktOrderId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "TktItem [tktId=" + tktId + ", tktOrderId=" + tktOrderId + ", amount=" + amount + ", used=" + used
				+ ", score=" + score + ", content=" + content + "]";
	}

}
