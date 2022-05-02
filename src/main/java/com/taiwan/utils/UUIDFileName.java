package com.taiwan.utils;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.Part;

public class UUIDFileName {
	
	public String getUUIDFileName(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String id=UUID.randomUUID().toString();
		String filename = new File(header.substring(header.length()-6, header.length() - 1)).getName();
		filename=id+filename;
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
