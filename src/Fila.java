

public class Fila {

	private Jogador inicio, fim;
	
	public Fila() {
		inicio = fim = null;
	}
	
	public void inclui(int v, String p) {
		Jogador e = new Jogador(v, p);
		if (inicio == null) {
			inicio = e;
			fim = e;
		} else {
			fim.proximo = e;
			fim = e;
		}
	}
	
	public int retiraPos() {
		int v = inicio.valor;
		return v;
	}
	
	public String retiraPizza() {
		String p = inicio.pizza;
		inicio = inicio.proximo;
		return p;
	}
	
	public String mostraPizza() {
		String p = inicio.pizza;
		return p;
	}
}
