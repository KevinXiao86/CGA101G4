package mybatis.mapper;

import java.util.List;

import com.taiwan.beans.Theme;

public interface ThemeMapper {
//	新增一條活動主題(ok)
	public int insert(Theme obj);

//	搜尋全部的活動主題(ok)
	public List<Theme> queryAll();

//	根據id來搜尋活動主題(x)
	public Theme queryById(Integer themeId);

//	根據title來搜尋活動主題(ok)
	public List<Theme> queryNewsByTitle(String title);

//	根據id更新活動主題(ok)
	public int updateById(Theme obj);

//	刪除活動主題(ok)
	public int delete(Integer themeId);

}
