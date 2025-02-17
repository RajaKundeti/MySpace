package calculations;

import java.io.IOException;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/choice")

public class ChoiceServlet extends GenericServlet {
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String op = req.getParameter("option");
		if(op.equals("Addition")) {
			RequestDispatcher rd = req.getRequestDispatcher("add");
			rd.forward(req, res);
		}
		else if(op.equals("Subtraction")) {
			RequestDispatcher rd = req.getRequestDispatcher("sub");
			rd.forward(req, res);
		}
		else if(op.equals("Multiplication")) {
			RequestDispatcher rd = req.getRequestDispatcher("mul");
			rd.forward(req, res);
		}
		else if(op.equals("Division")) {
			RequestDispatcher rd = req.getRequestDispatcher("div");
			rd.forward(req, res);
		}
		else if(op.equals("Modular Division")) {
			RequestDispatcher rd = req.getRequestDispatcher("mod");
			rd.forward(req, res);
		}
		else if(op.equals("Biggest")) {
			RequestDispatcher rd = req.getRequestDispatcher("big");
			rd.forward(req, res);
		}
		else if(op.equals("Smallest")) {
			RequestDispatcher rd = req.getRequestDispatcher("small");
			rd.forward(req, res);
		}
	}

}
