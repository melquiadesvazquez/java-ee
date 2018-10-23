package entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ventas database table.
 * 
 */
@Entity
@Table(name="ventas")
@NamedQuery(name="Venta.findAll", query="SELECT v FROM Venta v")
public class Venta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVEnta;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idCliente",referencedColumnName="idCliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Libro
	@ManyToOne
	@JoinColumn(name="idLibro",referencedColumnName="isbn")
	private Libro libro;

	public Venta(int idVEnta, Date fecha, Cliente cliente, Libro libro) {
		super();
		this.idVEnta = idVEnta;
		this.fecha = fecha;
		this.cliente = cliente;
		this.libro = libro;
	}

	public Venta() {
	}

	public int getIdVEnta() {
		return this.idVEnta;
	}

	public void setIdVEnta(int idVEnta) {
		this.idVEnta = idVEnta;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

}