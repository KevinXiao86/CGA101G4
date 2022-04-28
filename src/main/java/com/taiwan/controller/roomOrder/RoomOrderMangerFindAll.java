<<<<<<< HEAD
<<<<<<< HEAD
package com.taiwan.controller.RoomOrder;
=======
=======
>>>>>>> m1a2st
package com.taiwan.controller.roomOrder;

>>>>>>> d51c797ee69a3f245ed8c5d6827faec7f936e49f
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taiwan.beans.RoomOrder;
import com.taiwan.service.roomOrder.RoomOrderMyService;
import com.taiwan.utils.ControllerUtil;
@WebServlet("/roomOrderManger/findAll")
public class RoomOrderMangerFindAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomOrderMyService roomOrderMyService=ControllerUtil.getBean(RoomOrderMyService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
		roomOrders = roomOrderMyService.findAll();
		request.setAttribute("list", roomOrders);
		RequestDispatcher rd = request.getRequestDispatcher("/front-end/roomOrder/roomOrder_findAll.jsp");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
