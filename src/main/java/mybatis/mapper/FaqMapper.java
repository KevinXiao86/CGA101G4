package mybatis.mapper;

import java.util.List;

import com.taiwan.beans.FaqVO;

public interface FaqMapper {

	public int insert (FaqVO faqVO);
	
	public int update (FaqVO faqVO);
	
	public int delete (Integer faqId);
	
	public FaqVO queryById (Integer faqId);
	
	public List<FaqVO> queryAll();
	
	public List<FaqVO> queryFaqByTitle (String title);
}
