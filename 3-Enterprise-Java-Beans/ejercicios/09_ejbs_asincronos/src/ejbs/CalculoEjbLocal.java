package ejbs;

import java.util.concurrent.Future;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

@Local
public interface CalculoEjbLocal {

	Future<Integer> sumaNumeros(int limite);

}
