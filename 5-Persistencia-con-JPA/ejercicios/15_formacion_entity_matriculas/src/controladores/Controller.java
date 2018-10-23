package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		String url = "menu.html";
		
		switch (op) {
			case "doCrearAlumno":
				request.getRequestDispatcher("CrearAlumnoAction").include(request, response);
			case "doMostrarAlumnos":
				request.getRequestDispatcher("MostrarAlumnosAction").include(request, response);
				url = "alumnos.jsp";
				break;
			case "doMostrarAlumnosSelect":
				request.getRequestDispatcher("MostrarAlumnosAction").include(request, response);
				url = "selectAlumnos.jsp";
				break;
				
			case "doActualizarAlumno":
				request.getRequestDispatcher("ActualizarAlumnoAction").include(request, response);				
			case "doMostrarAlumno":
				request.getRequestDispatcher("MostrarAlumnoAction").include(request, response);
				url = "alumno.jsp";
				break;
											
			case "doCrearCurso":
				request.getRequestDispatcher("CrearCursoAction").include(request, response);
			case "doMostrarCursos":
				request.getRequestDispatcher("MostrarCursosAction").include(request, response);
				url = "cursos.jsp";
				break;
			case "doMostrarCursosSelect":
				request.getRequestDispatcher("MostrarCursosAction").include(request, response);
				url = "selectCursos.jsp";
				break;
							
			case "doActualizarCurso":
				request.getRequestDispatcher("ActualizarCursoAction").include(request, response);
			case "doMostrarCurso":
				request.getRequestDispatcher("MostrarCursoAction").include(request, response);
				url = "curso.jsp";
				break;
				
			case "doBuscarCursos":
				request.getRequestDispatcher("BuscarCursosAction").include(request, response);
				url = "cursos.jsp";
				break;
				
			case "doCrearMatricula":
				request.getRequestDispatcher("CrearMatriculaAction").include(request, response);
			case "doMostrarMatriculas":
				request.getRequestDispatcher("MostrarAlumnosAction").include(request, response);
				url = "matriculas.jsp";
				break;
				
			case "doEliminarMatricula":
				request.getRequestDispatcher("EliminarMatriculaAction").include(request, response);
				request.getRequestDispatcher("MostrarAlumnosAction").include(request, response);
				url = "matriculas.jsp";
				break;
				
			case "toAltaAlumno":
				url = "alumno.jsp";
				break;	
			case "toAltaCurso":
				url = "curso.jsp";
				break;
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}

