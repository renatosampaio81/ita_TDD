import java.util.function.BooleanSupplier;

public class ServicoLogin {
	
	private UsuarioDAO dao;

	public void setDAO(UsuarioDAO dao) {
		this.dao = dao;
	}

	public BooleanSupplier login(String usuario, String senha) {
		
	    // recupera a senha do usuário a partir do DAO
	    String senhaArmazenada = dao.getSenha(usuario);
	    
	    // verifica se usuário e senha informada é igual à senha armazenada
	    boolean autenticado = senhaArmazenada != null && senhaArmazenada.equals(senha);
	    
	    // retorna um BooleanSupplier que indica se o usuário foi autenticado ou não
	    return () -> autenticado;
	}

}
