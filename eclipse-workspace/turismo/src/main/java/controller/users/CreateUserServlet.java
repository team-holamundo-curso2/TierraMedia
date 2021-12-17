package controller.users;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import persistence.commons.UsuarioException;
import services.UserService;

@WebServlet("/users/create.do")
public class CreateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private UserService userService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.userService = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/users/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("nombre");
		Double monedas = Double.parseDouble(req.getParameter("monedas"));
		Double time = Double.parseDouble(req.getParameter("tiempo"));
		Integer preferencia = Integer.parseInt(req.getParameter("preferencia"));

		Usuario tmp_user = null;
		try {
			tmp_user = userService.create(-1, username, preferencia, monedas, time, false, false);
		} catch (UsuarioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (tmp_user.esValido()) {
			resp.sendRedirect("/turismo/users/index.do");
		} else {
			req.setAttribute("tmp_user", tmp_user);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/users/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
