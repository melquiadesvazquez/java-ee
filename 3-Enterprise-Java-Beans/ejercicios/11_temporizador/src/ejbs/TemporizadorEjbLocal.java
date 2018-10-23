package ejbs;

import javax.ejb.Local;

@Local
public interface TemporizadorEjbLocal {

	void detenerTemporizador();

	void iniciarTemporizador(long periodo);

}
