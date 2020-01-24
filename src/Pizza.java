
import javax.swing.JOptionPane;

public class Pizza {

	private Ingrediente primeiro, atual, ultimo;

	public Pizza() {
		primeiro = ultimo = atual = null;
	}
	
	public void incluiInicio(String v) {
		Ingrediente e = new Ingrediente(v);
		if (estaVazio()) {
			primeiro = e;
			ultimo = e;
		} else {
			e.proximo = primeiro;
			primeiro = e;
		}
	}
	
	public void incluiFinal(String v) {
		Ingrediente e = new Ingrediente(v);
		if (estaVazio()) {
			primeiro = e;
			ultimo = e;
			atual = e;
		} else {
			ultimo.proximo = e;
			ultimo = e;
		}
	}
	
	public int comprimento() {
		int comp = 0;
		atual = primeiro;
		while (atual != null) {
			comp ++;
			atual = atual.proximo;
		}
		return comp;
	}
	
	public void incluiPosicao(String v) {
		int pos = Integer.parseInt(JOptionPane.showInputDialog("Que posição incluir?"));
		int i = 0;
		atual = primeiro;
		boolean terminou = false;
		Ingrediente e = new Ingrediente(v);
		if (pos == 0) {
			incluiInicio(v);
		} else if (pos > comprimento()){
			incluiFinal(v);
		} else {
			do {
				if (i == pos - 1) {
					e.proximo = atual.proximo;
					atual.proximo = e;
					terminou = true;
				} else {
					i ++;
					atual = atual.proximo;
				}
			} while (!terminou);
		}
	}
	
	public String retiraInicio() {
		if (primeiro != null) {
			String ret = primeiro.valor;
			primeiro = primeiro.proximo;
			return ret;
		}
		return "";
	}
	
	public String retiraFinal() {
		int pos;
		String pizza = ultimo.valor;
		pos = comprimento() - 1;
		moveParaPosicao(pos);
		atual.proximo = null;
		ultimo = atual;
		if (comprimento() == 1) {
			ultimo = null;
		}
		return pizza;
	}
	
	public void retiraPosicao(int pos) {
		String v = "";	
		atual = primeiro;
		if (pos == 0) {
			retiraInicio();
		} else if (pos > comprimento()){
			retiraFinal();
		} else {
			moveParaPosicao(pos);
			Ingrediente e = new Ingrediente(v);
			e = atual.proximo;
			pos = pos - 1;
			moveParaPosicao(pos);
			atual.proximo = e;
		}
	}
	
	public String mostraPosicao(int pos) {
		moveParaPosicao(pos);
		return atual.valor;
	}
	
	public void moveParaPosicao(int pos) {
		atual = primeiro;
		for (int i = 0; i < pos; i++) {
			atual = atual.proximo;
		}
	}
	
	public boolean estaVazio() {
		if (primeiro == null || ultimo == null) {
			return true;
		}
		return false;
	}
	
	public int verTamanho() {
		return comprimento();
	}

}
