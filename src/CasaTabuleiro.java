

public class CasaTabuleiro {

	int valor;
	CasaTabuleiro proximo, anterior;
	
	public CasaTabuleiro(int v) {
		this.valor = v;
		this.proximo = null;
		this.anterior = null;
	}
}
