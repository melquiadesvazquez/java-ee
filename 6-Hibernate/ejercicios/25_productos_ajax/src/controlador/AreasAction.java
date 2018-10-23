package controlador;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Seccion;
import modelo.dao.DaoSeccionesEjbLocal;


@WebServlet("/AreasAction")
public class AreasAction extends HttpServlet {
	@EJB
	DaoSeccionesEjbLocal daoSecciones;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Seccion> secciones = daoSecciones.obtenerSecciones();
		request.setAttribute("secciones", secciones);
	}

}
