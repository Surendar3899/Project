

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;

/**
 * Servlet implementation class Database
 */
public class Database extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Database() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
	    PrintWriter obj=response.getWriter();
		int id=Integer.parseInt(request.getParameter("i"));
		String name=request.getParameter("n");
		String dept=request.getParameter("d");
		int yop=Integer.parseInt(request.getParameter("y"));
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/std310","root","");
		java.sql.Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from studentinfo");
		int count=0;
	    while(rs.next()) {
	    	if(id==rs.getInt(1)) {
	    	  count++;
	    	} 	
	    }
	    if(count>0) {
	    	response.setContentType("text/html");
	    	obj.println("Student already exists");
	    	RequestDispatcher a=request.getRequestDispatcher("Student.html");
	    	a.include(request, response);
	    }
	    else {
	    	response.setContentType("text/html");
	    	obj.println("Student details not in databse");
	    	HttpSession obj1=request.getSession();
	    	obj1.setAttribute("sid", id);
	    	obj1.setAttribute("sname", name);
	    	obj1.setAttribute("sdep", dept);
	    	obj1.setAttribute("syop", yop);
	    	obj1.setMaxInactiveInterval(15);
	    	obj.println("To add a student: <a href='Add'>Click here</a>");	
	    }
		
		}
		catch(Exception e) {
			PrintWriter obj=response.getWriter();
			obj.println("can't");
		}
	}

}
