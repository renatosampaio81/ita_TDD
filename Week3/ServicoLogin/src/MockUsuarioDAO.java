//import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class MockUsuarioDAO implements UsuarioDAO {
	
	//private String usuario, senha, esperado;
	private Map<String,String> loginSenha = new HashMap<>();
	
	
	@Override
	public void adicionaSenha(String login, String senha) {
		loginSenha.put(login, senha);
	}
	
	@Override
	public String getSenha(String login) {
		return loginSenha.get(login);
	}
	
	/*
	@Override
	public String getSenha(String usuario) {
		this.usuario = usuario;
		return senha;
	}
	*/
	
	/*
	public void setUser(String u) {
		esperado = u;
	}
	
	public void setSenha(String s) {
		senha = s;
	}
	
	
	
	public void verificarChamadas() {
		assertEquals(usuario, esperado);
	}
	*/
	
}
