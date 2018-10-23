package controladores;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Curso;
import modelo.CursoEjbLocal;

@WebServlet("/CrearCursoAction")
public class CrearCursoAction extends HttpServlet {

	@EJB
	CursoEjbLocal curso;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Curso newCurso = curso.crearCurso(
			request.getParameter("documento"),
			Integer.parseInt(request.getParameter("duracion")),	
			request.getParameter("nombre")
		);
		
		request.setAttribute("curso", newCurso);
	}

}