

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class viewcurrentbusiness
 */
@WebServlet("/viewcurrentbusiness")
public class viewcurrentbusiness extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewcurrentbusiness() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			connection c = new connection();
			c.loadDriver();
			Connection con = c.getConnection();
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>E-MALL_MARKETING-CURRENT-BUSINESS-SPACE-ALLOTMENT</title>\r\n" + 
					"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
					"<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n" + 
					"<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Raleway\">\r\n" + 
					"<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n" + 
					"<style>\r\n" + 
					"html,body,h1,h2,h3,h4,h5 {font-family: \"Raleway\", sans-serif}\r\n" + 
					"</style>\r\n" + 
					"<body class=\"w3-light-grey\">\r\n" + 
					"\r\n" + 
					"<!-- Top container -->\r\n" + 
					"<div class=\"w3-bar w3-top w3-black w3-large\" style=\"z-index:4\">\r\n" + 
					"  <button class=\"w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey\" onclick=\"w3_open();\"><i class=\"fa fa-bars\"></i> &nbsp;Menu</button>\r\n" + 
					"  <span class=\"w3-bar-item w3-right\"><a href=\"logout\" style=\"text-decoration: none;\">Log Out</a> </span>\r\n" + 
					"</div>\r\n" + 
					"\r\n" + 
					"<!-- Sidebar/menu -->\r\n" + 
					"<nav class=\"w3-sidebar w3-collapse w3-white w3-animate-left\" style=\"z-index:3;width:300px;\" id=\"mySidebar\"><br>\r\n" + 
					"  <div class=\"w3-container w3-row\">\r\n" + 
					"    <div class=\"w3-col s4\">\r\n" + 
					"      <img src=\"https://www.w3schools.com/w3images/avatar2.png\" class=\"w3-circle w3-margin-right\" style=\"width:46px\">\r\n" + 
					"    </div>\r\n" + 
					"    <div class=\"w3-col s8 w3-bar\">\r\n" + 
					"      <span>Welcome, <strong>Marketing Staff</strong></span><br>\r\n" + 
					"      <a href=\"#\" class=\"w3-bar-item w3-button\"><i class=\"fa fa-envelope\"></i></a>\r\n" + 
					"      <a href=\"#\" class=\"w3-bar-item w3-button\"><i class=\"fa fa-user\"></i></a>\r\n" + 
					"      <a href=\"#\" class=\"w3-bar-item w3-button\"><i class=\"fa fa-cog\"></i></a>\r\n" + 
					"    </div>\r\n" + 
					"  </div>\r\n" + 
					"  <hr>\r\n" + 
					"  <div class=\"w3-container\">\r\n" + 
					"    <h5>Dashboard</h5>\r\n" + 
					"  </div>\r\n" + 
					"  <div class=\"w3-bar-block\">\r\n" + 
					"    <a href=\"#\" class=\"w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black\" onclick=\"w3_close()\" title=\"close menu\"><i class=\"fa fa-remove fa-fw\"></i>&nbsp; Close Menu</a>\r\n" + 
					"    <a href=\"addbusinessspace.html\" class=\"w3-bar-item w3-button w3-padding\">&nbsp; Add New Business Space</a>\r\n" + 
					"    <a href=\"viewbusinessspace_update\" class=\"w3-bar-item w3-button w3-padding\">&nbsp; Update Cost Of Business Space</a>\r\n" + 
					"    <a href=\"viewbusinessspace_delete\" class=\"w3-bar-item w3-button w3-padding\">&nbsp; Delete Business Space</a>\r\n" + 
					"    <a href=\"addunitsbusinessspace.html\" class=\"w3-bar-item w3-button w3-padding\">&nbsp; Add Unit(s) Business Space</a>\r\n" + 
					"    <a href=\"viewunitsbusinessspace_update\" class=\"w3-bar-item w3-button w3-padding\">&nbsp; Update Unit(s) Business Space</a>\r\n" + 
					"    <a href=\"spaceowner_register.html\" class=\"w3-bar-item w3-button w3-padding\">&nbsp; Register New Space Owner</a>\r\n" + 
					"    <a href=\"viewspaceowner\" class=\"w3-bar-item w3-button w3-padding\">&nbsp; Book Available Business Space</a>\r\n" + 
					"    <a href=\"viewcurrentbusiness\" class=\"w3-bar-item w3-button w3-padding w3-blue\">&nbsp; Business space allotment</a>\r\n" + 
					"    <a href=\"revenuegenerated\" class=\"w3-bar-item w3-button w3-padding\">&nbsp; Revenue Generated</a>\r\n" + 
					"    <a href=\"complaint.html\" class=\"w3-bar-item w3-button w3-padding\">&nbsp; Register Complaint</a><br><br>\r\n" + 
					"  </div>\r\n" + 
					"</nav>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"<!-- Overlay effect when opening sidebar on small screens -->\r\n" + 
					"<div class=\"w3-overlay w3-hide-large w3-animate-opacity\" onclick=\"w3_close()\" style=\"cursor:pointer\" title=\"close side menu\" id=\"myOverlay\"></div>\r\n" + 
					"\r\n" + 
					"<!-- !PAGE CONTENT! -->\r\n" + 
					"<div class=\"w3-main\" style=\"margin-left:300px;margin-top:43px;\">\r\n" + 
					"\r\n" + 
					"  <!-- Header -->\r\n" + 
					"  <header class=\"w3-container\" style=\"padding-top:22px\">\r\n" + 
					"    <h5><b> Business space allotment</b></h5>\r\n" + 
					"  </header>\r\n" + 
					"\r\n" + 
					"  <div class=\"w3-row-padding w3-margin-bottom\">\r\n" + 
					"    ");
		

			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM currrentspaceallotment");
				out.println("<table border=1 width=50% height=50%>");
				out.println(
						"<tr><th>Id</th><th>Name</th><th>Email</th><th>Space</th><th>Unit(s) Occupied</th><th>Start Date</th><th>End Date</th><th>Revenue</th><tr>");
				while (rs.next()) {
					int id = rs.getInt("oid");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String space = rs.getString("space");
					int units = rs.getInt("units");
					Date startdate = rs.getDate("startdate");
					Date enddate = rs.getDate("enddate");
					double revenue = rs.getDouble("revenue");

					out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>"  + email + "</td><td>"  
					+ space + "</td><td>"  + units +"</td><td>"  + startdate +"</td><td>"  + enddate +"</td><td>"  + revenue + "</td></tr>");
				}
				
				out.println("</table></div>\r\n" + 
						"\r\n" + 
						"<script>\r\n" + 
						"// Get the Sidebar\r\n" + 
						"var mySidebar = document.getElementById(\"mySidebar\");\r\n" + 
						"\r\n" + 
						"// Get the DIV with overlay effect\r\n" + 
						"var overlayBg = document.getElementById(\"myOverlay\");\r\n" + 
						"\r\n" + 
						"// Toggle between showing and hiding the sidebar, and add overlay effect\r\n" + 
						"function w3_open() {\r\n" + 
						"  if (mySidebar.style.display === 'block') {\r\n" + 
						"    mySidebar.style.display = 'none';\r\n" + 
						"    overlayBg.style.display = \"none\";\r\n" + 
						"  } else {\r\n" + 
						"    mySidebar.style.display = 'block';\r\n" + 
						"    overlayBg.style.display = \"block\";\r\n" + 
						"  }\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						"// Close the sidebar with the close button\r\n" + 
						"function w3_close() {\r\n" + 
						"  mySidebar.style.display = \"none\";\r\n" + 
						"  overlayBg.style.display = \"none\";\r\n" + 
						"}\r\n" + 
						"</script>");
				out.println("&nbsp&nbsp&nbsp&nbsp<button onclick = \"window.print()\">Print</button>");
				out.println("</html></body>");
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			throw new ServletException("SESSION HAD BEEN LOGGED OUT PREVIOUSLY, LOGIN TO CONTINUE");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
