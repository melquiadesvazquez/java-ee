package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javabeans.Libro;

public class Venta implements Serializable {
	private int idCliente;
	private List<Libro> cesta;
		
	public Venta(int idCliente, List<Libro> cesta) {
		this.idCliente = idCliente;
		this.cesta = cesta;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public List<Libro> getCesta() {
		return cesta;
	}
	public void setCesta(ArrayList<Libro> cesta) {
		this.cesta = cesta;
	}
	
	
}
