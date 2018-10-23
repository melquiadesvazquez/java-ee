package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.CursoEjbLocal;

@WebServlet("/ActualizarCursoAction")
public class ActualizarCursoAction extends HttpServlet {

	@EJB
	CursoEjbLocal curso;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		curso.actualizarCurso(
			Integer.parseInt(request.getParameter("idCurso")),  
			request.getParameter("documento"), 
			Integer.parseInt(request.getParameter("duracion")), 
			request.getParameter("nombre")
		);		
	}

}