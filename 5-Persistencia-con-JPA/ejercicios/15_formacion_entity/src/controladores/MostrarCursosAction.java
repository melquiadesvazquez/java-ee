package controladores;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.CursoEjbLocal;

@WebServlet("/MostrarCursosAction")
public class MostrarCursosAction extends HttpServlet {

	@EJB
	CursoEjbLocal curso;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("cursos", curso.getCursos());
	}

}

