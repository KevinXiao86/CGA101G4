package com.taiwan.beans;

public class UseFriendVO {
	private Integer useFriendId;
	private Integer custId;
	private String friend;
	public UseFriendVO(Integer useFriendId, Integer custId, String friend) {
		super();
		this.useFriendId = useFriendId;
		this.custId = custId;
		this.friend = friend;
	}
	public UseFriendVO() {
		super();
	}
	public Integer getUseFriendId() {
		return useFriendId;
	}
	public void setUseFriendId(Integer useFriendId) {
		this.useFriendId = useFriendId;
	}
	public Integer getCustId() {
		return custId;
	}
	public void setCustId(Integer custId) {
		this.custId = custId;
	}
	public String getFriend() {
		return friend;
	}
	public void setFriend(String friend) {
		this.friend = friend;
	}
	@Override
	public String toString() {
		return "UseFriendVO [useFriendId=" + useFriendId + ", custId=" + custId + ", friend=" + friend + "]";
	}
}
