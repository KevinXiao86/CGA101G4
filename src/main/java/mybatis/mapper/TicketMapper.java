package mybatis.mapper;

import java.util.List;

import com.taiwan.beans.TicketVO;

public interface TicketMapper {
	
	public int insert (TicketVO ticketVO);
	
	public int update (TicketVO ticketVO);
	
	public int delete (Integer tktId);
	
	public TicketVO queryById (Integer tktId);
	
	public List<TicketVO> queryAll();
	
	public List<TicketVO> getTicketByScore (); 
	
	public List<TicketVO> getTicketByPeople ();
	
	public List<TicketVO> queryTicketByTktName (String tktName);
	
	public List<TicketVO> queryTicketByKind (String kind);
	
	public List<TicketVO> queryTicketByLocation (String location);
	
	public List<TicketVO> queryTicketByStatus (String status);
}