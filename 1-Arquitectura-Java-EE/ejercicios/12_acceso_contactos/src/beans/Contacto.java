package beans;

public class Contacto {
	private int id;
	private String nombre;
	private String email;
	private int edad;
	
	public Contacto(int id, String nombre, String email, int edad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	};
	
	
}
