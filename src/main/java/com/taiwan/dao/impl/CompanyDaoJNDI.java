package com.taiwan.dao.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.taiwan.beans.Company;
import com.taiwan.dao.CompanyDao;
import com.taiwan.utils.JndiUtil;

public class CompanyDaoJNDI implements CompanyDao {

	@Override
	public Company queryCompanyByAccountAndPassword(String account, String password) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 1. ����s��
			connection = JndiUtil.getConnection();

			// 2. �]�m sql �y�y
			String sql = "SELECT 	`cmp_id`, `cmp_name`, `cmp_tel`, `cmp_mail`, `cmper`, `cmper_tel`, `cmp_status`, `audit_status`,"
					+ "			`serial_no`, `cmp_account`, `cmp_password`, `cmp_introduce`, `checkin_time`, `checkout_time`, "
					+ "			`location`, `notice`, `canx`, `bank_account`" + "	 FROM `Taiwan`.`COMPANY`"
					+ "	 WHERE `cmp_account` = ?" + "	 AND `cmp_password` = ?;";

			// 3. ����w�sĶ sql �y�y
			ps = connection.prepareStatement(sql);

			// 4. ��R����Ÿ�
			ps.setString(1, account);
			ps.setString(2, password);

			// 5. ���� sql �y�y����쵲�G��
			rs = ps.executeQuery();

			// 6. �N���G���ʸ˦�����
			Company company = null;
			while (rs.next()) {
				company = new Company();
				company.setCmpId(rs.getInt(1));
				company.setCmpName(rs.getString(2));
				company.setCmperTel(rs.getString(3));
				company.setCmpMail(rs.getString(4));
				company.setCmper(rs.getString(5));
				company.setCmperTel(rs.getString(6));
				company.setCmpStatus(rs.getString(7));
				// .. �ٲ�
			}
			// 7. �^�ǵ��G
			return company;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 8. �����귽
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
