

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
 * Servlet implementation class updateunitsbusinessspace
 */
@WebServlet("/updateunitsbusinessspace")
public class updateunitsbusinessspace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateunitsbusinessspace() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		int unit = Integer.parseInt(request.getParameter("unit"));

		HttpSession session = request.getSession(false);
		if (session != null) {
		
				connection c = new connection();
				c.loadDriver();
				Connection con = c.getConnection();
				String sql = "update unitsspace set unit = ? where id = ?";
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, unit);
					ps.setInt(2, id);
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
