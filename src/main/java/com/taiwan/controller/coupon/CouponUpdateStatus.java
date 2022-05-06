//package com.taiwan.controller.coupon;
//
//import java.io.IOException;
//import java.util.*;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.taiwan.beans.CouponVO;
//import com.taiwan.service.coupon.CouponService;
//import com.taiwan.utils.ControllerUtil;
//
//@WebServlet("/coupon/timerUpdateStatus")
//public class CouponUpdateStatus extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	CouponService couponService = ControllerUtil.getBean(CouponService.class);
//	Timer timer = new Timer();
//
//	public void init() throws ServletException {
//		Calendar cal = new GregorianCalendar(2022, Calendar.MAY, 4, 0, 0, 0);
//		TimerTask task = new TimerTask() {
//
//			@Override
//			public void run() {
//
//				List<CouponVO> couponVOs=couponService.findAll();
//				for(CouponVO couponVO:couponVOs) {
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//					Integer copId = couponVO.getCopId();
//					Timestamp enddate = couponVO.getEnddate();
//					Date today;
//					try {
//						today = sdf.parse(sdf.format(new Date()));
//						if (today.after(enddate)) {
//							couponService.updateStatus(copId, "下架");
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//				}
//				}
//			}
//		};
//		timer.scheduleAtFixedRate(task, cal.getTime(),60*60*1000);
//	}
//
//	public void destory() {
//
//	}
//
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//	}
//
//}
