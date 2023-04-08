import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TesteCarrinhoCompras {

	@Test
	void totalCarrinho() {
		CarrinhoCompras c = new CarrinhoCompras();
		c.adicionaProduto(new Produto("tenis",100));
		c.adicionaProduto(new Produto("camiseta",50));
		c.adicionaProduto(new Produto("bermuda",70));
		assertEquals(220, c.total());
	}
	
	@Test
	void escutaAdicaoDeProduto() {
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock = new MockObservadorCarrinho();
		c.adicionaObservador(mock);
		c.adicionaProduto(new Produto("tenis",100));
		mock.verificaRecebimentoProduto("tenis",100);
	}
	
	@Test
	void adicionarDoisObservadores() {
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		MockObservadorCarrinho mock2 = new MockObservadorCarrinho();
		c.adicionaObservador(mock1);
		c.adicionaObservador(mock2);
		c.adicionaProduto(new Produto("tenis",100));
		mock1.verificaRecebimentoProduto("tenis",100);
		mock2.verificaRecebimentoProduto("tenis",100);
	}

	@Test
	void continuaNotificandoComErroEmObservador() {
		CarrinhoCompras c = new CarrinhoCompras();
		MockObservadorCarrinho mock1 = new MockObservadorCarrinho();
		MockObservadorCarrinho mock2 = new MockObservadorCarrinho();
		mock2.querQueVoceDeErro();
		//ObservadorCarrinho mock2 = new MockObservadorComErro(); //essa é outra alternativa de simular o erro é criar uma classe que lanća a exception sem precisar criar if no MockObservadorCarrinho
		MockObservadorCarrinho mock3 = new MockObservadorCarrinho();
		c.adicionaObservador(mock1);
		c.adicionaObservador(mock2);
		c.adicionaObservador(mock3);
		c.adicionaProduto(new Produto("tenis",100));
		mock1.verificaRecebimentoProduto("tenis",100);
		//mock2.verificaRecebimentoProduto("tenis",100); //nao verifico porque forcei um erro no mock2
		mock3.verificaRecebimentoProduto("tenis",100);
	}

}
