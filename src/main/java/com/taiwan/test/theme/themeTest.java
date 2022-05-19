package com.taiwan.test.theme;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taiwan.beans.Theme;
import com.taiwan.service.theme.ThemeService;

public class themeTest {

	@Autowired
	private ThemeService themeService;

	@Test
	public void test01() {
		List<Theme> ls = new ArrayList<Theme>();
		ls = themeService.findAll();
		System.out.println(ls);
	}
//	
//	private ThemeMapper mapper;
//	@Test
//	public void test02() {
//		System.out.println(mapper.queryById(7));
//	}

}
