
import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class spaceownerregistration
 */
@WebServlet("/spaceownerregistration")
public class spaceownerregistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public spaceownerregistration() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");

		HttpSession session = request.getSession(false);
		if (session != null) {
			if (pass1.equals(pass2)) {
				connection c = new connection();
				c.loadDriver();
				Connection con = c.getConnection();
				String sql = "insert into spaceowner (name, email, password) values (?,?,?)";
				try {
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, name);
					ps.setString(2, email);
					ps.setString(3, pass1);
					ps.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("marketingstaff_control.html");
			} else {
				throw new ServletException("PASSWORD AND CONFIRM PASSWORD SHOULD MATCH");
			}
		}
		else {
			throw new ServletException("SESSION HAD BEEN LOGGED OUT PREVIOUSLY, LOGIN TO CONTINUE");
		}
	}

}
