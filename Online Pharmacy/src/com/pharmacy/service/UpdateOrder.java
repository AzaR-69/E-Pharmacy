package com.pharmacy.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pharmacy.dao.OrdersDAO;

/**
 * Servlet implementation class UpdateOrder
 */
@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOrder() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		String role=request.getParameter("role");
		PrintWriter out=response.getWriter();
		String status=request.getParameter("status");
		out.print(status+"/n"+orderId);
		OrdersDAO dao=new OrdersDAO();
		if(role.equals("USER")) {
			response.sendRedirect(request.getContextPath()+"/views/dashboard.jsp");
		}
		else {
			String result=dao.updateOrder(orderId, status);
			if(result.equals("SUCCESS")) {
				response.sendRedirect("views/orders.jsp");
			}
			else {
				out.print("ERROR");
			}
		}
	}

}
