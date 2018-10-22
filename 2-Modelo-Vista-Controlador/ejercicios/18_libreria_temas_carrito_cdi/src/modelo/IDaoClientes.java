package modelo;

public interface IDaoClientes {

	int autenticarCliente(String usuario, String password);

	int registrarCliente(String usuario, String password, String email, int telefono);

}