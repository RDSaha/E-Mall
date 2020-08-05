

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
/**
 * Servlet implementation class bookspace
 */
@WebServlet("/bookspace")
public class bookspace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookspace() {
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

		int sid =Integer.parseInt(request.getParameter("id"));
		int units = Integer.parseInt(request.getParameter("unit"));
		LocalDate startdate = LocalDate.parse(request.getParameter("startdate"));
		LocalDate enddate = LocalDate.parse(request.getParameter("enddate"));
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			
			connection c = new connection();
			c.loadDriver();
			Connection con = c.getConnection();

			revenue ri = new revenue();
			
		
			double revenue = (ri.calculate_individual(sid,startdate,enddate)) * (units);
			
			Date startdate1 = Date.valueOf(startdate);
			Date enddate1 = Date.valueOf(enddate);
			
			int oid = (int) session.getAttribute("oid");
				
				try {
					
					String sql = "insert into  spaceallotment(oid, sid, units, startdate, enddate, revenue) values (?,?,?,?,?,?)";

					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, oid);
					ps.setInt(2, sid);
					ps.setInt(3, units);
					ps.setDate(4, startdate1);
					ps.setDate(5, enddate1);
					ps.setString(6, Double.toString(revenue));
					
					ps.execute();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				response.sendRedirect("marketingstaff_control.html");
			
		}
		else {
			throw new ServletException("SESSION HAD BEEN LOGGED OUT PREVIOUSLY, LOGIN TO CONTINUE");
		}

	}

}
