package controladores;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Alumno;
import entidades.Curso;
import modelo.AlumnoEjbLocal;

@WebServlet("/CrearMatriculaAction")
public class CrearMatriculaAction extends HttpServlet {

	@EJB
	AlumnoEjbLocal alumno;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		alumno.matricularAlumno(
			request.getParameter("usuario"),
			Integer.parseInt(request.getParameter("idCurso"))	
		);
	}

}