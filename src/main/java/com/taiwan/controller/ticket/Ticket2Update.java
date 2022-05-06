package com.taiwan.controller.ticket;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.TicketVO;
import com.taiwan.beans.TktImgVO;
import com.taiwan.service.TicketService;
import com.taiwan.service.tktImg.TktImgService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/ticket/tkt2Update")
public class Ticket2Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs=new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//獲得請求參數
			Integer tktId=Integer.valueOf(request.getParameter("tktId"));
			//獲取請求查詢的結果
			TicketVO ticketVO=ticketService.findById(tktId);
//			System.out.println(ticketVO);
			String allAddress=ticketVO.getAddress();
			String city=allAddress.substring(0,3);
			String town=allAddress.substring(3,6);
			String address=allAddress.substring(6);
			TktImgService tktImgService=new TktImgService();
			List<TktImgVO> tktImgVOs=tktImgService.getByTktId(tktId);
			//在網址塞資料
			String param="?tktId="  +ticketVO.getTktId()+
				       	 "&tktName="  +ticketVO.getTktName()+
				         "&originalAmount="    +ticketVO.getOriginalAmount()+
				         "&price="+ticketVO.getPrice()+
				         "&instruction="+ticketVO.getInstruction()+
				         "&startdate="    +ticketVO.getStartdate()+
				         "&enddate="   +ticketVO.getEnddate()+
				         "&location=" +ticketVO.getLocation()+
				         "&city="+city+
				         "&town="+town+
						 "&address="+address+
						 "&notice="    +ticketVO.getNotice()+
						 "&howuse="   +ticketVO.getHowuse()+
						 "&canxpolicy=" +ticketVO.getCanxpolicy()+
						 "&soldAmount="    +ticketVO.getSoldAmount()+
						 "&kind="   +ticketVO.getKind();
			String url="/back-end/ticket/ticket_update.jsp"+param;
			request.setAttribute("tktImgVOs", tktImgVOs);
			RequestDispatcher rd=request.getRequestDispatcher(url);
			rd.forward(request, response);
		}catch (Exception e) {
			errorMsgs.put("errorMsgs", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/ticket/findAll");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
