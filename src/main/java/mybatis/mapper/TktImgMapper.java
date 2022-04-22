package mybatis.mapper;

import java.util.List;

import com.taiwan.beans.TktImgVO;


public interface TktImgMapper {

	public int insert(TktImgVO tktImgVO);
	
	public int update(TktImgVO tktImgVO);
	
	public int delete(Integer tktImgId);
	
	public TktImgVO queryById(Integer tktImgId); 	
	
	public List<TktImgVO> queryAll();
	
	public List<TktImgVO> queryByTktId(Integer tktId);
}
