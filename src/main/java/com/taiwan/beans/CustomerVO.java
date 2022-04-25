package com.taiwan.beans;

import java.sql.Date;

public class CustomerVO extends Core {

	private Integer custId;
	private String name;
	private String sex;
	private String tel;
	private String email;
	private String address;
	private String idCard;
	private Date birth;
	private String account;
	private String password;
	private String img;
	private String custUse;
	private String card;
	private String custRight;

	public CustomerVO(Integer custId, String name, String sex, String tel, String email, String address, String idCard,
			Date birth, String account, String password, String img, String custUse, String card, String custRight) {
		super();
		this.custId = custId;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.idCard = idCard;
		this.birth = birth;
		this.account = account;
		this.password = password;
		this.img = img;
		this.custUse = custUse;
		this.card = card;
		this.custRight = custRight;
	}

	public CustomerVO() {
		super();
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCustUse() {
		return custUse;
	}

	public void setCustUse(String custUse) {
		this.custUse = custUse;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getCustRight() {
		return custRight;
	}

	public void setCustRight(String custRight) {
		this.custRight = custRight;
	}

	@Override
	public String toString() {
		return "CustomerVO [custId=" + custId + ", name=" + name + ", sex=" + sex + ", tel=" + tel + ", email=" + email
				+ ", address=" + address + ", idCard=" + idCard + ", birth=" + birth + ", account=" + account
				+ ", password=" + password + ", img=" + img + ", custUse=" + custUse + ", card=" + card + ", custRight="
				+ custRight + "]";
	}

}
