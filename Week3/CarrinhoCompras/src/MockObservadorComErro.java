
public class MockObservadorComErro implements ObservadorCarrinho {

	@Override
	public void produtoAdicionado(String nome, int valor) {
		throw new RuntimeException("Problema simulado pelo mock");
	}

}
