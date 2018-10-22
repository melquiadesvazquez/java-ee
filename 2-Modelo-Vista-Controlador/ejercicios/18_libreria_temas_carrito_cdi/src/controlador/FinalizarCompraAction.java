package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.Libro;
import modelo.DaoVentas;

@WebServlet("/FinalizarCompraAction")
public class FinalizarCompraAction extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		HttpSession session = request.getSession();
		ArrayList<Libro> cesta = (ArrayList<Libro>)session.getAttribute("cesta");
		int cliente = (Integer)session.getAttribute("cliente");
			
		DaoVentas dVentas = new DaoVentas();
		ArrayList<Integer> ventas = dVentas.crearVentas(cliente, cesta);
        System.out.println("++++++++++++++");
        System.out.println("Venta realizada");
        System.out.println(ventas);
        System.out.println("++++++++++++++");
	}
}
