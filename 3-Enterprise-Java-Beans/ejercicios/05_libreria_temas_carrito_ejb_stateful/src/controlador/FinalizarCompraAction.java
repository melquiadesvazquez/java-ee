package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejbs.CarritoEjbLocal;
import javabeans.Libro;
import modelo.VentasEjbLocal;

@WebServlet("/FinalizarCompraAction")
public class FinalizarCompraAction extends HttpServlet {
	
	@EJB
	VentasEjbLocal dVentas;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		HttpSession session = request.getSession();
		CarritoEjbLocal carrito = (CarritoEjbLocal)request.getSession().getAttribute("carrito");
		ArrayList<Libro> cesta = carrito.get();
		int cliente = (Integer)session.getAttribute("cliente");			
		
		ArrayList<Integer> ventas = dVentas.crearVentas(cliente, cesta);
        System.out.println("++++++++++++++");
        System.out.println("Venta realizada");
        System.out.println(ventas);
        System.out.println("++++++++++++++");
	}
}
