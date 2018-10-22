package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Contacto;
import modelo.DaoContactos;

@WebServlet("/ListarAction")
public class ListarAction extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoContactos dcontactos = new DaoContactos();
		List<Contacto> contactos = dcontactos.listarContactos();
		request.setAttribute("contactos", contactos);
	}

}
