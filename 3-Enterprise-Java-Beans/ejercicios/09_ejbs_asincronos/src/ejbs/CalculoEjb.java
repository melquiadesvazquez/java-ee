package ejbs;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class CalculoEjb implements CalculoEjbLocal {
	
	@Override
	@Asynchronous
	public Future<Integer> sumaNumeros(int limite) {
		int result = 0;
		
		for (int i = 0; i <= limite; i++) {			
			
			try {
				result += i;
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return new AsyncResult<Integer>(result);
	}

}
