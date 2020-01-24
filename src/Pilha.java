

import java.util.Random;

public class Pilha {

	private Carta topo = null;
	
	public Pilha() {
		
	}
	
	public boolean estaVazia() {
		return topo == null;
	}
	
	public void empilha(String tipo) {
		Carta nova = new Carta(tipo);
		if (topo == null) {
			topo = nova;
		} else {
			nova.proxima = topo;
			topo = nova;
		}
	}
	
	public String desempilha() {
		String tipo = topo.tipo;
		topo = topo.proxima;
		return tipo;
	}
	
	public void embaralha() {
		int cont = 0;
		do {
			int contPerde = 20;
			int contGanha2 = 20;
			int contRetira = 20;
			int contQueima = 20;
			Random r = new Random();
			int embaralhador = r.nextInt(4 - 0) + 1;
			if (embaralhador == 1 && contPerde != 0) {
				empilha("perde");
			} else if (embaralhador == 2 && contGanha2 != 0) {
				empilha("ganha2");
			}else if (embaralhador == 3 && contRetira != 0) {
				empilha("retira");
			}if (embaralhador == 4 && contQueima != 0) {
				empilha("queima");
			}
			cont ++;
		} while (cont < 80);
	}
	
}
