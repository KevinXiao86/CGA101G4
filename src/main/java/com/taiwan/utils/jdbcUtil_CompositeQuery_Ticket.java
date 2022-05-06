package com.taiwan.utils;

import java.util.*;

public class jdbcUtil_CompositeQuery_Ticket {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ( "TKT_ID".equals(columnName) || "PRICE".equals(columnName) ) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("TKT_NAME".equals(columnName) || "LOCATION".equals(columnName)|| "KIND".equals(columnName)) // 用於varchar  status 
			aCondition = columnName + " like '%" + value + "%'";
		else if ("ENDDATE".equals(columnName) ||"STARTDATE".equals(columnName))                          // 用於date
			aCondition = columnName + "=" + "'"+ value +"'";                          //for 其它DB  的 date
		
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

//	public static void main(String argv[]) {
//
//		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("empno", new String[] { "7001" });
//		map.put("ename", new String[] { "KING" });
//		map.put("job", new String[] { "PRESIDENT" });
//		map.put("hiredate", new String[] { "1981-11-17" });
//		map.put("sal", new String[] { "5000.5" });
//		map.put("comm", new String[] { "0.0" });
//		map.put("deptno", new String[] { "10" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
//
//		String finalSQL = "select * from emp2 "
//				          + jdbcUtil_CompositeQuery_Ticket.get_WhereCondition(map)
//				          + "order by empno";
//		System.out.println("●●finalSQL = " + finalSQL);
//
//	}
}