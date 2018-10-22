package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabeans.Libro;
import modelo.DaoLibros;

@WebServlet("/ListarPorTemaAction")
public class ListarPorTemaAction extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoLibros dLibros = new DaoLibros();
		String tema = (request.getParameter("tema") == null)? "0" : (String)request.getParameter("tema");
		List<Libro> libros = tema.equals("0")? dLibros.listarLibros() : dLibros.listarLibros(tema);
		request.setAttribute("libros", libros);
	}
}
