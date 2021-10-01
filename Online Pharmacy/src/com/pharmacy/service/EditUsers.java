package com.pharmacy.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pharmacy.bean.UserBean;
import com.pharmacy.dao.UserDAO;

/**
 * Servlet implementation class EditUsers
 */
@WebServlet("/EditUsers")
public class EditUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO dao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    @Override
	public void init() throws ServletException {
		dao=new UserDAO();
	}
    public EditUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id=Integer.parseInt((String)request.getParameter("id"));
		UserBean user=new UserBean();
		user.setId(id);
		user.setUsername((String)request.getParameter("username"));
		user.setPhoneNumber((String)request.getParameter("phoneNumber"));
		user.setEmail((String)request.getParameter("email"));
		user.setPassword((String)request.getParameter("password"));
		user.setRole((String)request.getParameter("role"));
		String result=dao.updateUser(user,id);
		request.getSession().setAttribute("message", result);
		response.sendRedirect("views/dashboard.jsp");
	}

	

}
