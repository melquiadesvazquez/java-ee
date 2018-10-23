package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Contactos;
import modelo.ContactosEjbLocal;

@WebServlet("/ListarAction")
public class ListarAction extends HttpServlet {
	@EJB
	ContactosEjbLocal dcontactos;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Contactos> contactos = dcontactos.listarContactos();
		request.setAttribute("contactos", contactos);
	}

}
