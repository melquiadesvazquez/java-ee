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

@WebServlet("/MostrarCursoAction")
public class MostrarCursoAction extends HttpServlet {

	@EJB
	CursoEjbLocal curso;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("curso", curso.getCurso(Integer.parseInt(request.getParameter("idCurso"))));
	}

}