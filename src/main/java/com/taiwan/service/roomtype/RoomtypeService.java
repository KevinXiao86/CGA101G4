package com.taiwan.service.roomtype;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.security.auth.message.callback.TrustStoreCallback;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.taiwan.beans.RoomImg;
import com.taiwan.beans.Roomtype;
import com.taiwan.utils.CommonUtils;

import mybatis.mapper.RoomImgMapper;
import mybatis.mapper.RoomtypeMapper;

@Service
public class RoomtypeService {

	@Autowired
	private RoomtypeMapper roomtypeMapper;

	@Autowired
	private RoomImgMapper roomImgMapper;

	// 獲取廠商的所有房型
	@Transactional(readOnly = true)
	public List<Roomtype> getAllRoomtypes(Integer cmpId) {
		List<Roomtype> roomtypes = roomtypeMapper.queryAllRoomtypes(cmpId);
		return roomtypes;
	}

	// 根據廠商編號和房型編號更新狀態
	@Transactional
	public boolean updateStatusByCmpIdAndRoomtypeId(String cmpId, String rootypeId, String status) {
		int idCmp = CommonUtils.parseInt(cmpId, 0);
		int idRoomtype = CommonUtils.parseInt(rootypeId, 0);
		if (idCmp == 0 || idRoomtype == 0) {
			return false;
		}
		int result = roomtypeMapper.updateStatusByRoomtypeIdAndCmpId(idCmp, idRoomtype, status);
		return result != 0;
	}

	// 根據廠商編號和房型編號更新價格
	@Transactional
	public boolean updatePriceByCmpIdAndRoomtypeId(String cmpId, String rootypeId, String price) {
		int idCmp = CommonUtils.parseInt(cmpId, 0);
		int idRoomtype = CommonUtils.parseInt(rootypeId, 0);
		int intPrice = CommonUtils.parseInt(price, 0);
		if (idCmp == 0 || idRoomtype == 0 || intPrice == 0) {
			return false;
		}

		int result = roomtypeMapper.updatePriceByCmpIdAndRoomtypeId(idCmp, idRoomtype, intPrice);

		return result != 0;
	}

	// 查看房型詳情
	@Transactional(readOnly = true)
	public Roomtype getRoomtypeByCmpIdAndRoomtypeId(String cmpId, String rootypeId) {
		int idCmp = CommonUtils.parseInt(cmpId, 0);
		int idRoomtype = CommonUtils.parseInt(rootypeId, 0);
		if (idCmp == 0 || idRoomtype == 0) {
			return null;
		}

		Roomtype roomtype = roomtypeMapper.getRoomtypeByCmpIdAndRoomtypeId(idCmp, idRoomtype);

		return roomtype;
	}

	// 返回目前的房型編號 roomtypeId
	@Transactional(readOnly = true)
	public int getRoomtypeId() {
		int roomtypeId = roomtypeMapper.queryRoomtypeId();
		return roomtypeId;
	}

	// 新增房型
	@Transactional
	public boolean insertRoomtype(Roomtype roomtype) {
		int result1 = roomtypeMapper.insertRoomtype(roomtype);
		int result2 = roomImgMapper.insertRoomImg(roomtype.getRoomImgs());

		if (result1 == 0 || result2 == 0) {
			return false;
		}

		return true;
	}

	// 在房型修改頁面刪除已有的房型圖片
	@Transactional
	public boolean deleteRoomImgInEditPage(String[] roomImgIds, HttpSession session) {
		Integer[] ids = new Integer[roomImgIds.length];

		for (int i = 0; i < roomImgIds.length; i++) {
			int id = CommonUtils.parseInt(roomImgIds[i], 0);

			if (id == 0) {
				// 轉換失敗
				return false;
			}

			ids[i] = id;
		}

		// 刪除服務器裡面的圖片
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
			RoomImg roomImg = roomImgMapper.queryRoomImgByRoomImgId(ids[i]);
			String savePath = roomImg.getRoomImg();
			File file = new File(session.getServletContext().getRealPath(File.separator) + File.separator + savePath);
			file.delete();

			// 刪除之後看上層目錄
			File parentPath = new File(file.getParent());
			if (parentPath.exists()) {
				if (parentPath.list().length == 0) {
					parentPath.delete();
				}
			}
		}

		// 刪除數據庫裡面的紀錄
		int result = roomImgMapper.deleteRoomImgByRoomImgId(ids);

		return result != 0;
	}


	// 修改房型
	@Transactional
	public boolean updateRoomtype(Roomtype roomtype) {
		int result = roomtypeMapper.updateRoomtype(roomtype);
		boolean flag = false;
		
		if (roomtype.getRoomImgs().size() > 0) {
			flag = true;
			roomImgMapper.insertRoomImg(roomtype.getRoomImgs());
		}
		if (result == 0) {
			return false;
		}
		if (!flag) {
			return false;
		}
		
		return true;
	}
	
	

	public String getSavePath(MultipartFile multipartFile, HttpSession session, String cmpAccount, String roomtypeId) {
		// 獲取上傳的文件名
		String fileName = multipartFile.getOriginalFilename();

		// 處理文件重名問題
		String hzName = fileName.substring(fileName.lastIndexOf("."));
		fileName = UUID.randomUUID().toString() + hzName;

		// 獲取服務器中 images/Company/廠商帳號/房型編號 目錄的路徑
		ServletContext servletContext = session.getServletContext();
		String roomtypePath = servletContext.getRealPath(File.separator + "images" + File.separator + "Company"
				+ File.separator + cmpAccount + File.separator + roomtypeId);

		// 創建目錄
		File file2 = new File(roomtypePath);
		if (!file2.exists()) {
			file2.mkdir();
		}

		String savePath = "images" + File.separator + "Company" + File.separator + cmpAccount + File.separator
				+ roomtypeId + File.separator + fileName;
		System.out.println(savePath);

		return savePath;
	}
}
