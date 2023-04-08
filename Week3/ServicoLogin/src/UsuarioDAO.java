
public interface UsuarioDAO {

	void adicionaSenha(String login, String senha);
	
	String getSenha(String usuario);

}
