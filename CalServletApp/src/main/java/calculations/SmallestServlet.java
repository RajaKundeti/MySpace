package calculations;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
@SuppressWarnings("serial")
@WebServlet("/small")
public class SmallestServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		float v1 = Float.parseFloat(req.getParameter("v1"));
		float v2 = Float.parseFloat(req.getParameter("v2"));
		float output = 0.0f;
		if(v1<v2) {
			output = v1;
		}
		else {
			output = v2;
		}
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		pw.println("<center><h4>Smallest Number<h4>"+output+"</center><br>");
		RequestDispatcher rd = req.getRequestDispatcher("home.html");
		rd.include(req, res);
	}

}
