package javabeans;

import java.io.Serializable;

// En caso de que el servidor serialize la clase, si no lo tiene, devolverá un error
public class Libro implements Serializable {
	private int isbn;
	private String titulo;
	private String author;
	private double precio;
	private int paginas;
	private int idTema;
	
	public Libro(int isbn, String titulo, String author, double precio, int paginas, int idTema) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.author = author;
		this.precio = precio;
		this.paginas = paginas;
		this.idTema = idTema;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public int getIdTema() {
		return idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	
	
}
