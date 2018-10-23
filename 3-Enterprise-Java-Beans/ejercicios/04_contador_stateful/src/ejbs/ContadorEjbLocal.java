package ejbs;

import javax.ejb.Local;
import javax.ejb.Remove;

@Local
public interface ContadorEjbLocal {
	void incrementar();
	void decrementar();
	int getValor();
	void inicializar(int valor);
	void finalizar();
	void pasivacion();
	void activacion();
}
