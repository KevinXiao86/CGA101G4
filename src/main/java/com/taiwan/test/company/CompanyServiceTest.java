package com.taiwan.test.company;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taiwan.beans.Company;
import com.taiwan.service.company.CompanyService;

public class CompanyServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	CompanyService companyService = context.getBean(CompanyService.class);
	
	@Test
	public void test01() {
		Company company = companyService.login("caesar", "caesar");
		System.out.println(company);
		System.out.println(company.getAuditStatus());
		System.out.println(company.getMessage());
		System.out.println(company.isSuccessful());
		System.out.println(company.getUrl());
	}
	
	
	@Test
	public void test02() {
		String cmpName = "台中旭日文旅";
		String cmpTel = "02-87800000";
		String cmpMail = "emma@gmail.com";
		String cmper = "emma";
		String cmperTel = "0987664123";
		String serialNo = "/images/emmaccount";
		String cmpAccount = "admin";
		String cmpPassword = "emmapassword";
		String cmpIntroduce = "旭日文旅開幕於2018年1月1日，旭日，象徵著酒店如旭日東升般朝氣蓬勃，希望帶給每位到來的旅客充滿活力的樣貌。\r\n"
				+ "\r\n"
				+ "酒店位置緊鄰交通重要樞紐，為生活機能便利的路段。漫步城中，體驗著街道的繁華與寧靜，旅程結束，館內大廳設有沙發區及迎賓飲料，讓旅客們感受回到家的放鬆。\r\n"
				+ "\r\n"
				+ "精緻俐落的房型，提供旅人最舒適的休息空間，是台中住宿性價比極高的首選。";
		
		
        
        String checkinTime = "15:00";
		String checkoutTime = "12:00";
        
		String location = "台中";
		String notice = "請注意，這棟建築內的部分房型無對外窗。如有任何特殊要求，均需視實際情況而定。\r\n"
				+ "由於新冠肺炎（COVID-19）疫情，住客必須於所有室內公共區域佩戴口罩。";
		
		String canx = "取消和預付款政策根據各種住宿類型而有所不同。 請輸入您的入住日期並參閱您所需的客房的條款";
		
		Company company = new Company(null, cmpName, cmpTel, cmpMail, cmper, cmperTel, null, null, 
				serialNo, cmpAccount, cmpPassword, cmpIntroduce, checkinTime, checkoutTime, location, notice, canx, null);
		
		Company company2 = companyService.regist(company);
		
		System.out.println(company2);
		System.out.println(company2.getMessage());
		System.out.println(company2.isSuccessful());
		System.out.println(company2.getUrl());
	}

	
	@Test
	public void test03() {
		boolean result = companyService.existsCmpAccount("ademin");
		System.out.println(result);
	}
	
	
	@Test
	public void test04() {
		boolean result = companyService.updateSerialNoPath("jack", "images/jackpassword/營業登記證3.jpg");
		System.out.println(result);
	}
	
	
	@Test
	public void test05() {
		Company company = companyService.getCompanyByCmpId(20000);
		System.out.println(company);
	}
	
	
	@Test
	public void test06() {
		String cmpTel = "04-2202-2555";
		String cmpMail = "emmaAccount@gmail.com";
		
		String cmpIntroduce = "旭日文旅開幕於2018年1月1日，旭日，象徵著酒店如旭日東升般朝氣蓬勃，希望帶給每位到來的旅客充滿活力的樣貌。\r\n"
				+ "\r\n"
				+ "酒店位置緊鄰交通重要樞紐，為生活機能便利的路段。漫步城中，體驗著街道的繁華與寧靜，旅程結束，館內大廳設有沙發區及迎賓飲料，讓旅客們感受回到家的放鬆。\r\n"
				+ "\r\n"
				+ "精緻俐落的房型，提供旅人最舒適的休息空間，是台中住宿性價比極高的首選。";
		
		String checkinTime = "15:00";
		String checkoutTime = "12:00";
		
		String location = "台中";
		String notice = "請注意，這棟建築內的部分房型無對外窗。如有任何特殊要求，均需視實際情況而定。\r\n"
				+ "由於新冠肺炎（COVID-19）疫情，住客必須於所有室內公共區域佩戴口罩。";
		
		String canx = "取消和預付款政策根據各種住宿類型而有所不同。 請輸入您的入住日期並參閱您所需的客房的條款";
//		
//		Company company = new Company(null, null, null, null, null, null, null, null, 
//				null, null, null, cmpIntroduce, checkinTime, checkoutTime, location, notice, canx);
		
		Company company = new Company(20030, null, null, null, null, null, null, null, 
				null, null, null, cmpIntroduce, null, null, null, null, null, null);
		
		System.out.println(companyService.updateCompanyById(company));
	}

	@Test
	public void test07() {
		List<Company> list = companyService.getAllCompany();
		System.out.println(list.size());
		for(Company company : list) {
			System.out.println(company.getCmpName());
		}
	}
	
	
	@Test
	public void test08() {
		boolean result = companyService.updateAuditStatusByCmpId(20000, "審核通過");
		System.out.println(result);
	}


	@Test
	public void test09() {
		boolean res = companyService.updateStatusByCmpId(20000, "哈哈哈");
		System.out.println(res);
	}
}
