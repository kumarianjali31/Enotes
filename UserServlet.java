package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.DAO.userDAO;
import com.User.UserDetails;
import com.Db.DBConnect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		String name=request.getParameter("fname");
		String email=request.getParameter("uemail");
		String password=request.getParameter("upassword");
		UserDetails us = new UserDetails();
		us.setName(name);
		us.setEmail(email);
		us.setPassword(password);
		userDAO dao = new userDAO(DBConnect.getConn());
		boolean f=dao.addUser(us);
		HttpSession  session;
		if(f)
		{
			
			session = request.getSession();
			session.setAttribute("reg-sucess", "Registration Successfull..");
			response.sendRedirect("register.jsp");
		}
		else
		{
			session = request.getSession();
			session.setAttribute("failed-msg","something went wrong on server");
			response.sendRedirect("register.jsp");
		}
	}
}
