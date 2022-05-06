package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.taiwan.beans.Company;

public interface CompanyMapper {

	// 根據廠商帳號和廠商密碼來獲取廠商
	public Company queryCompanyByCmpAccountAndCmpPassword(@Param("cmpAccount") String cmpAccount,
			@Param("cmpPassword") String cmpPassword);

	// 新增廠商
	public int insertCompany(Company company);

	// 根據廠商帳號查詢廠商
	public Company queryCompanyByCmpAccount(String cmpAccount);

	// 根據廠商編號修改廠商資料
	public int updateCompanyByCmpId(Company company);
	
	//根據廠商編號查詢廠商(管理員, 用戶)
	public Company queryCompanyByCmpId(Integer cmpId);
	
	//查詢所有廠商資料(管理員)
	public List<Company> queryAllCompany();
	
	//根據廠商編號修改廠商狀態(管理員)
	public int updateStatusByCmpId(@Param("cmpId")Integer cmpId,@Param("status")String status);

	//根據廠商編號修改審核狀態(管理員)
	public int updateAuditStatusByCmpId(@Param("cmpId")Integer cmpId,@Param("auditStatus")String auditStatus);
}
