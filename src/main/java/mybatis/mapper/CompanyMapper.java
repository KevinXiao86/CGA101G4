package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.Company;

public interface CompanyMapper {
	// 根據 cmp_account 和 cmp_password 查詢廠商
	public Company queryCompanyByAccountAndPassword(@Param("account") String account,
			@Param("password") String password);

	// 新增一筆廠商資料
	public int insertCompany(Company company);
	
	//根據廠商帳號查詢廠商
	public Company queryCompanyByCmpAccount(String cmpAccount);
	
	//根據廠商帳號更新廠商營業登記證圖片路徑
	public int updateSerialNoByCmpAccount(@Param("cmpAccount")String cmpAccount, @Param("serialNo")String serialNo);
	
	//根據廠商編號查詢廠商(管理員, 用戶)
	public Company queryCompanyByCmpId(Integer cmpId);
	
	//根據廠商編號修改廠商資料
	public int updateCompanyByCmpId(Company company);
	
	//查詢所有廠商資料(管理員)
	public List<Company> queryAllCompany();
	
	//根據廠商編號修改審核狀態為審核通過(管理員)
	public int updateAuditStatusByCmpId(@Param("cmpId")Integer cmpId,@Param("auditStatus")String auditStatus);
	
	//根據廠商編號修改廠商狀態(管理員)
	public int updateStatusByCmpId(@Param("cmpId")Integer cmpId,@Param("status")String status);
}
