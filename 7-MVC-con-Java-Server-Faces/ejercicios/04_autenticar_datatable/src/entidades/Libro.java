package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the libros database table.
 * 
 */
@Entity
@Table(name="libros")
@NamedQuery(name="Libro.findAll", query="SELECT l FROM Libro l")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int isbn;

	private String autor;

	private int paginas;

	private double precio;

	private String titulo;

	//bi-directional many-to-one association to Tema
	@ManyToOne
	@JoinColumn(name="idTema",referencedColumnName="idTema")
	private Tema tema;

	//bi-directional many-to-one association to Venta
	@OneToMany(mappedBy="libro")
	private List<Venta> ventas;

	public Libro() {
	}

	public Libro(int isbn, String autor, int paginas, double precio, String titulo, Tema tema, List<Venta> ventas) {
		super();
		this.isbn = isbn;
		this.autor = autor;
		this.paginas = paginas;
		this.precio = precio;
		this.titulo = titulo;
		this.tema = tema;
		this.ventas = ventas;
	}

	public int getIsbn() {
		return this.isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getPaginas() {
		return this.paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Tema getTema() {
		return this.tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<Venta> getVentas() {
		return this.ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Venta addVenta(Venta venta) {
		getVentas().add(venta);
		venta.setLibro(this);

		return venta;
	}

	public Venta removeVenta(Venta venta) {
		getVentas().remove(venta);
		venta.setLibro(null);

		return venta;
	}

}