package controlador;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabeans.Tema;
import modelo.TemasEjbLocal;

@WebServlet("/ListarTemasAction")
public class ListarTemasAction extends HttpServlet {
	
	@EJB
	TemasEjbLocal dTemas;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Tema> temas = dTemas.listarTemas();
		request.setAttribute("temas", temas);
	}
}
