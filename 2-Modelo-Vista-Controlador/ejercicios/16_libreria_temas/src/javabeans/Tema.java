package javabeans;

public class Tema {
	private int idTema;
	private String tema;
	
	public Tema(int idTema, String tema) {
		super();
		this.idTema = idTema;
		this.tema = tema;
	}
	
	public int getIdTema() {
		return idTema;
	}
	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}	
}
