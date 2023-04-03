import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class TestConvertCamelCase {

	private List<String> listaEsperada;
	private ConvertCamelCase c;
	private List<String> listaRecebida;
		
	@BeforeEach
	public void inicializador() {
		c = new ConvertCamelCase();
		listaEsperada = new ArrayList<String>();
	}

	@Test
	public void testaStringVazia() {
		listaRecebida = c.converterCamelCase("");
		listaEsperada.add("");
		assertEquals(listaEsperada, listaRecebida);
	}

	@Test
	public void testaStringMinuscula() {
		listaRecebida = c.converterCamelCase("nome");
		listaEsperada.add("nome");
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void testaUnicaPalavra() {
		listaRecebida = c.converterCamelCase("Nome");
		listaEsperada.add("nome");
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void testaPalavraComposta() {
		listaRecebida = c.converterCamelCase("nomeComposto");
		listaEsperada.add("nome");
		listaEsperada.add("composto");
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void testaPalavraCompostaMaiuscula() {
		listaRecebida = c.converterCamelCase("NomeComposto");
		listaEsperada.add("nome");
		listaEsperada.add("composto");
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void testaPalavraInteiraMaiuscula() {
		listaRecebida = c.converterCamelCase("CPF");
		listaEsperada.add("CPF");
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void testaPalavraInteiraMaisuculaComposta() {
		listaRecebida = c.converterCamelCase("numeroCPF");
		listaEsperada.add("numero");
		listaEsperada.add("CPF");
		assertEquals(listaEsperada, listaRecebida);
	}

	@Test
	public void testaPalavraInteiraMaiusculaEntrePalavras() {
		listaRecebida = c.converterCamelCase("numeroCPFContribuinte");
		listaEsperada.add("numero");
		listaEsperada.add("CPF");
		listaEsperada.add("contribuinte");
		assertEquals(listaEsperada, listaRecebida);
	}
	
	@Test
	public void testaNumeroEntrePalavras() {
		listaRecebida = c.converterCamelCase("recupera10Primeiros");
		listaEsperada.add("recupera");
		listaEsperada.add("10");
		listaEsperada.add("primeiros");
		assertEquals(listaEsperada, listaRecebida);
	}
	
		
	@Test
	void testaPalavraComecandoPorNumero() {
	    assertThrows(CamelCaseInvalidoException.class, () -> {
	        listaRecebida = c.converterCamelCase("10Primeiros");
	    });
	}
	
	
	@Test
	void testaPalavraComCaracterEspecial() {
	    assertThrows(CamelCaseInvalidoException.class, () -> {
	        listaRecebida = c.converterCamelCase("nome#Composto");
	    });
	}


}