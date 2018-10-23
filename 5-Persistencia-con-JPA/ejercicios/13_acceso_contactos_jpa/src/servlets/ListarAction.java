package servlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Contacto;
import modelo.IDaoContactos;

@WebServlet("/ListarAction")
public class ListarAction extends HttpServlet {
	@Inject
	IDaoContactos dcontactos;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Contacto> contactos = dcontactos.listarContactos();
		request.setAttribute("contactos", contactos);
	}

}
