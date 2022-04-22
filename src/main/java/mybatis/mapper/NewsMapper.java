package mybatis.mapper;

import java.util.List;

import com.taiwan.beans.News;

public interface NewsMapper {

//	新增一條最新消息(ok)
	public int insert(News obj);

//	搜尋全部的最新消息(ok)
	public List<News> queryAll();

//	根據id來搜尋最新消息(x)
	public News queryById(Integer newsId);

//	根據title來搜尋最新消息(ok)
	public List<News> queryNewsByTitle(String title);

//	根據id更新最新消息(ok)
	public int updateById(News obj);

//	刪除最新消息(ok)
	public int delete(Integer newsId);

}
