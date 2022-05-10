package com.taiwan.test.company;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.Company;
import com.taiwan.beans.RoomImg;
import com.taiwan.beans.Roomtype;

import mybatis.mapper.CompanyMapper;

public class CompanyMapperTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	CompanyMapper companyMapper = context.getBean(CompanyMapper.class);
	
	
	@Test
	public void test01() {
		int totalCount = companyMapper.queryForPageTotalCount();
		System.out.println("總紀錄數: " + totalCount);
	}
	
	
	@Test
	public void test02() {
		List<Company> companies = companyMapper.queryForPageItems(0, 5);
		for(Company company : companies) {
			System.out.println(company.getCmpName());
		}
	}
	
	
	@Test
	public void test03() {
		int totalCount = companyMapper.queryForPageTotalCountByAuditStatus("審核未通過");
		System.out.println("totalCount: " + totalCount);
	}
	
	
	@Test
	public void test04() {
		List<Company> companies = companyMapper.queryForPageItemsByAuditStatus("審核通過", 0, 5);
		for(Company company : companies) {
			System.out.println(company.getAuditStatus());
		}
	}
	
	
	@Test
	public void test05() {
		List<Company> companies = companyMapper.getAllCompanies();
		for(Company company : companies) {
			System.out.println("廠商名稱: " + company.getCmpName());
			for (Roomtype roomtype : company.getRoomtypes()) {
				System.out.println("房型名稱: " + roomtype.getRoomtypeName());
				for(RoomImg roomImg : roomtype.getRoomImgs()) {
					System.out.println(roomImg);
				}
			}
			
			System.out.println("----------------------------------------------");
		}
	}
	
	@Test
	public void test06() {
		List<Company> companies = companyMapper.getAllCompaniesByLocation("台北");
		for(Company company : companies) {
			System.out.println("廠商名稱: " + company.getCmpName());
			for (Roomtype roomtype : company.getRoomtypes()) {
				System.out.println("房型名稱: " + roomtype.getRoomtypeName());
				for(RoomImg roomImg : roomtype.getRoomImgs()) {
					System.out.println(roomImg);
				}
			}
			
			System.out.println("----------------------------------------------");
		}
	}
	
}
