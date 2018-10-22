package beans;

public class Order {
	private String nombre;
	private Double precio;
	private int unidades;
	
	public Order(String nombre, Double precio, int unidades) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.unidades = unidades;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
}

