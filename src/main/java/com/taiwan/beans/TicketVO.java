package com.taiwan.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class TicketVO implements Serializable {
	private Integer tktId;
	private String tktName;
	private Integer originalAmount;
	private Integer price;
	private Timestamp startdate;
	private Timestamp enddate;
	private Integer ttlScore;  // join
	private Integer ttlPeople; // join
	private String location;
	private String instruction;
	private String address;
	private String notice;
	private String howuse;
	private String canxpolicy;
	private String status;
	private Integer soldAmount;
	private String kind;

	public TicketVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketVO(Integer tktId, String tktName, Integer originalAmount, Integer price, Timestamp startdate,
			Timestamp enddate, Integer ttlScore, Integer ttlPeople, String location, String instruction, String address,
			String notice, String howuse, String canxpolicy, String status, Integer soldAmount, String kind) {
		super();
		this.tktId = tktId;
		this.tktName = tktName;
		this.originalAmount = originalAmount;
		this.price = price;
		this.startdate = startdate;
		this.enddate = enddate;
		this.ttlScore = ttlScore;
		this.ttlPeople = ttlPeople;
		this.location = location;
		this.instruction = instruction;
		this.address = address;
		this.notice = notice;
		this.howuse = howuse;
		this.canxpolicy = canxpolicy;
		this.status = status;
		this.soldAmount = soldAmount;
		this.kind = kind;
	}

	public Integer getTktId() {
		return tktId;
	}

	public void setTktId(Integer tktId) {
		this.tktId = tktId;
	}

	public String getTktName() {
		return tktName;
	}

	public void setTktName(String tktName) {
		this.tktName = tktName;
	}

	public Integer getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(Integer originalAmount) {
		this.originalAmount = originalAmount;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Timestamp getStartdate() {
		return startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getEnddate() {
		return enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

	public Integer getTtlScore() {
		return ttlScore;
	}

	public void setTtlScore(Integer ttlScore) {
		this.ttlScore = ttlScore;
	}

	public Integer getTtlPeople() {
		return ttlPeople;
	}

	public void setTtlPeople(Integer ttlPeople) {
		this.ttlPeople = ttlPeople;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getHowuse() {
		return howuse;
	}

	public void setHowuse(String howuse) {
		this.howuse = howuse;
	}

	public String getCanxpolicy() {
		return canxpolicy;
	}

	public void setCanxpolicy(String canxpolicy) {
		this.canxpolicy = canxpolicy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSoldAmount() {
		return soldAmount;
	}

	public void setSoldAmount(Integer soldAmount) {
		this.soldAmount = soldAmount;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "TicketVO [tktId=" + tktId + ", tktName=" + tktName + ", originalAmount=" + originalAmount + ", price="
				+ price + ", startdate=" + startdate + ", enddate=" + enddate + ", ttlScore=" + ttlScore
				+ ", ttlPeople=" + ttlPeople + ", location=" + location + ", instruction=" + instruction + ", address="
				+ address + ", notice=" + notice + ", howuse=" + howuse + ", canxpolicy=" + canxpolicy + ", status="
				+ status + ", soldAmount=" + soldAmount + ", kind=" + kind + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(tktName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketVO other = (TicketVO) obj;
		return Objects.equals(tktName, other.tktName);
	}
	
}
