

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class addunitsbusinessspace
 */
@WebServlet("/addunitsbusinessspace")
public class addunitsbusinessspace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addunitsbusinessspace() {
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
		String space = request.getParameter("space");
		int unit = Integer.parseInt(request.getParameter("unit"));

		HttpSession session = request.getSession(false);
		if (session != null) {
		
				connection c = new connection();
				c.loadDriver();
				Connection con = c.getConnection();
				String sql = "insert into unitsspace(space,unit,unitoccupied) values(?,?,?)";
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, space);
					ps.setInt(2, unit);
					ps.setInt(3, 0);
					
					
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
