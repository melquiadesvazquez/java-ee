package entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the historico database table.
 * 
 */
@Entity
@NamedQuery(name="Historico.findAll", query="SELECT h FROM Historico h")
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idHistorico;

	private double cantidad;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="idCliente",referencedColumnName="idCliente")
	private Cliente cliente;

	public Historico() {
	}

	public Historico(int idHistorico, double cantidad, Cliente cliente) {
		super();
		this.idHistorico = idHistorico;
		this.cantidad = cantidad;
		this.cliente = cliente;
	}

	public int getIdHistorico() {
		return this.idHistorico;
	}

	public void setIdHistorico(int idHistorico) {
		this.idHistorico = idHistorico;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}