package controladores;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Alumno;
import modelo.AlumnoEjbLocal;

@WebServlet("/CrearAlumnoAction")
public class CrearAlumnoAction extends HttpServlet {

	@EJB
	AlumnoEjbLocal alumno;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Alumno newAlumno = alumno.crearAlumno(
			request.getParameter("usuario"),
			Integer.parseInt(request.getParameter("edad")),
			request.getParameter("email"),
			request.getParameter("nombre"),
			request.getParameter("password")
		);
		
		request.setAttribute("alumno", newAlumno);
	}

}