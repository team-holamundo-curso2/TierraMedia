package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import persistence.commons.UsuarioException;
import services.UserService;
@WebServlet("/users/edit.do")

public class EditUserServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 3455721046062278592L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Usuario tmp_user = userService.find(id);
		req.setAttribute("tmp_user", tmp_user);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/users/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("nombre");
		Integer preferencia = Integer.parseInt(req.getParameter("preferencia"));
		Double monedas = Double.parseDouble(req.getParameter("monedas"));
		Double time = Double.parseDouble(req.getParameter("tiempo"));
		Integer id = Integer.parseInt(req.getParameter("id"));

		Usuario tmp_user = null;
		try {
			tmp_user = userService.update(id, username, preferencia, monedas, time, false, false);
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (tmp_user.esValido()) {
			resp.sendRedirect("/turismo/users/index.do");
		} else {
			req.setAttribute("tmp_user", tmp_user);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/users/edit.jsp");
			dispatcher.forward(req, resp);
		}

	}
}
