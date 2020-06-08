package com.jspiders.webapp.first;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class MyJdbcServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Connection CON = null;
		Statement STMT = null ;
		ResultSet RES = null;
		
		PrintWriter out = resp.getWriter();
		
		try {
			Driver driverref = new Driver();
			DriverManager.registerDriver(driverref);
			
			String dburl = "jdbc:mysql://localhost:3306/bejm37?user=root&password=root";
			
			CON = DriverManager.getConnection(dburl);
			
			String query = " select * from studentsinfo ";
			
			STMT = CON.createStatement();
			RES = STMT.executeQuery(query);
			
			while(RES.next())
			{
				int regNum = RES.getInt("regno");
				String firstName = RES.getString("firstname");
				String midleName = RES.getString("midlename");
				String lastName = RES.getString("lastname");
			
				out.println("regno: "+regNum);
				out.println("firstName: "+firstName);
				out.println("midleName: "+midleName);
				out.println("lastName: "+lastName);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			try {
				if(CON!=null)
				{
					CON.close();
				}
				if(STMT!=null)
				{
					STMT.close();
				}
				if(RES!= null)
				{
					RES.close();
				}
			}catch(Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
}
