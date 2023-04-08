import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicoLoginTeste {
	
	private ServicoLogin serv;
	private MockUsuarioDAO mock;
	
	@BeforeEach
	void criarServicos() {
		serv = new ServicoLogin();
		mock = new MockUsuarioDAO();
	}
	
	/*
	@Test
	void loginComSucesso() {
		ServicoLogin serv = new ServicoLogin();
		MockUsuarioDAO mock = new MockUsuarioDAO();
		serv.setDAO(mock);
		mock.setUser("renato");
		mock.setSenha("coxinha123");
		assertTrue(serv.login("renato", "coxinha123"));
		mock.verificarChamadas();
	}
		
	@Test
	void loginFalhaSenhaIncorreta() {
		ServicoLogin serv = new ServicoLogin();
		MockUsuarioDAO mock = new MockUsuarioDAO();
		serv.setDAO(mock);
		mock.setUser("renato");
		mock.setSenha("coxinha123");
		assertFalse(serv.login("renato", "coxinha321"));
		mock.verificarChamadas();
	}
	
	*/
	
	@Test
	void loginComSucesso() {
		serv.setDAO(mock);
		mock.adicionaSenha("renato", "coxinha123");
		assertTrue(serv.login("renato", "coxinha123"));
	}
	
	@Test
	void loginFalhaSenhaIncorreta() {
		serv.setDAO(mock);
		mock.adicionaSenha("renato", "coxinha123");
		assertFalse(serv.login("renato", "coxinha321"));
	}
	
	@Test
	void loginFalhaLoginIncorreto() {
		serv.setDAO(mock);
		mock.adicionaSenha("renato", "coxinha123");
		mock.adicionaSenha("renan", "abc123");
		assertFalse(serv.login("renan", "coxinha123"));
	}

	@Test
	void loginFalhaLoginVazio() {
		serv.setDAO(mock);
		mock.adicionaSenha("renato", "coxinha123");
		mock.adicionaSenha("renan", "abc123");
		assertFalse(serv.login("", "coxinha123"));
	}
	
	@Test
	void loginFalhaSenhaVazia() {
		serv.setDAO(mock);
		mock.adicionaSenha("renato", "coxinha123");
		mock.adicionaSenha("renan", "abc123");
		assertFalse(serv.login("renato", ""));
	}

}
