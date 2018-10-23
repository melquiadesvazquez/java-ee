package ejbs;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;

@Stateless
@LocalBean
public class TemporizadorEjb implements TemporizadorEjbLocal {
	@Resource
	SessionContext sc;
	
	Timer tm;
	
	@Timeout
    void imprimirMensaje(Timer t) {
    	System.out.println("Información: " + (String)t.getInfo());
    }
	
	@Override
	public void iniciarTemporizador(long periodo) {
		long retardo = 3000;
		String adicional = "mensaje de temporizador";
		tm = sc.getTimerService().createTimer(retardo, periodo, adicional);
	}
	
	@Override
	public void detenerTemporizador() {
		tm.cancel();
	}
}
