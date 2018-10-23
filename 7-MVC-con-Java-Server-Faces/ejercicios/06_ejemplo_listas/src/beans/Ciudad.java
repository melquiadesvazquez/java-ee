package beans;

public class Ciudad {
	private String nombre;
	private int codigo;
	private double temperatura;
	
	public Ciudad(String nombre, int codigo, double temperatura) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.temperatura = temperatura;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public double getTemperatura() {
		return temperatura;
	}
	
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}	
}
