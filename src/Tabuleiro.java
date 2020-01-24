

public class Tabuleiro {

	private CasaTabuleiro primeiro, atual, ultimo;
	int tamanho = 0;

	public Tabuleiro() {
		primeiro = ultimo = atual = null;
	}
	
	public void incluiInicio(int v) {
		CasaTabuleiro e = new CasaTabuleiro(v);
		if (estaVazio()) {
			primeiro = e;
			ultimo = e;
		} else {
			e.proximo = primeiro;
			e.anterior = ultimo;
			ultimo.proximo = e;
			primeiro = e;
		}
		tamanho ++;
	}
	
	public void incluiFinal(int v) {
		CasaTabuleiro e = new CasaTabuleiro(v);
		if (estaVazio()) {
			primeiro = e;
			ultimo = e;
		} else {
			e.anterior = ultimo;
			ultimo.proximo = e;
			ultimo = e;
			e.proximo = primeiro;
		}
		tamanho ++;
	}
	
	public void incluiPosicao(int v, int pos) {
		int i = 0;
		atual = primeiro;
		boolean terminou = false;
		CasaTabuleiro e = new CasaTabuleiro(v);
		if (pos == 0) {
			incluiInicio(v);
		} else if (pos > tamanho){
			incluiFinal(v);
		} else {
			do {
				if (i == pos - 1) {
					e.proximo = atual.proximo;
					e.anterior = atual;
					atual.proximo = e;
					terminou = true;
					tamanho ++;
				} else {
					i ++;
					atual = atual.proximo;
				}
			} while (!terminou);
		}
	}
	
	public int retiraInicio() {
		int ret = primeiro.valor;
		return ret;
	}
	
	public int retiraFinal() {
		int ret = ultimo.valor;
		return ret;
	}
	
	public int retiraPosicao(int pos) {
		int i = 0;
		int ret;
		boolean terminou = false;
		atual = primeiro;
		if (pos == 0) {
			return retiraInicio();
		} else if (pos > tamanho){
			return retiraFinal();
		} else {
			do {
				if (i == pos - 1) {
					ret = atual.proximo.valor;
					return ret;
				} else {
					i ++;
					atual = atual.proximo;
				}
			} while (!terminou);
		}
		return -1;
	}
	
	public boolean estaVazio() {
		if (primeiro == null) {
			return true;
		}
		return false;
	}
	
	public int verTamanho() {
		return tamanho;
	}

}
