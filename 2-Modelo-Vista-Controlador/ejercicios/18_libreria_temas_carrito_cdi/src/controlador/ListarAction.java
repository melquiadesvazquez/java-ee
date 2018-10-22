package controlador;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabeans.Libro;
import modelo.IDaoLibros;

@WebServlet("/ListarAction")
public class ListarAction extends HttpServlet {
	@Inject
	IDaoLibros dLibros;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Libro> libros = dLibros.listarLibros();
		request.setAttribute("libros", libros);
	}
}
