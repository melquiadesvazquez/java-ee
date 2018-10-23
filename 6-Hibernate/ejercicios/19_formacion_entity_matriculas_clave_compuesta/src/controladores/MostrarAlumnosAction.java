package controladores;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.AlumnoEjbLocal;

@WebServlet("/MostrarAlumnosAction")
public class MostrarAlumnosAction extends HttpServlet {

	@EJB
	AlumnoEjbLocal alumno;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("alumnos", alumno.getAlumnos());
	}

}

