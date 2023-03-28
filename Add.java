

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class Add
 */
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		HttpSession obj=request.getSession();
		Integer a=(Integer) obj.getAttribute("sid");
		String b=(String) obj.getAttribute("sname");
		String c=(String) obj.getAttribute("sdep");
		Integer d=(Integer)obj.getAttribute("syop");
		
		 if (a == null || b == null || c == null || d == null) {
	            PrintWriter out = response.getWriter();
	            out.println("SESSION TIMEOUT");
	            return;
	        }
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/std310","root","");
		PreparedStatement st=con.prepareStatement("insert into studentinfo values(?,?,?,?)");
		st.setInt(1, a);
		st.setString(2, b);
		st.setString(3, c);
		st.setInt(4, d);
		int f=st.executeUpdate();
        if(f>0) {
        PrintWriter p=response.getWriter();
		p.println("Student record successfull add in database");
		p.println();
		
        }
	}
		
		catch(Exception e) {
			System.out.println(e);
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}
