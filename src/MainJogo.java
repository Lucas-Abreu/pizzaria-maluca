

import java.util.Random;

import javax.swing.JOptionPane;

public class MainJogo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Tabuleiro tabuleiro = new Tabuleiro();
		iniciaTabuleiro(tabuleiro);
	
		Pizza calabresa = new Pizza();
		iniciaPizzaCalabresa(calabresa);
		boolean temCalabresa = false;
		
		Pizza alCapone = new Pizza();
		iniciaPizzaAlCapone(alCapone);
		boolean temAlCapone = false;
		
		Pizza ovos = new Pizza();
		iniciaPizzaOvos(ovos);
		boolean temOvos = false;
		
		Pizza vegana = new Pizza();
		iniciaPizzaVegana(vegana);
		boolean temVegana = false;
		
		Pizza presunto = new Pizza();
		iniciaPizzaPresunto(presunto);
		boolean temPresunto = false;
		
		System.out.println(""
				+ "*************************\n"
				+ "*    Pizzaria Maluca    *\n"
				+ "*************************\n"
				+ "Por Gustavo Zancheta\n"
				+ "	 & Lucas de Abreu\n"
				+ "\nCarregando...");
		
		// Fila dos Jogadores;
		Fila f = new Fila();
		
		// Pilha das cartas de sorte ou azar:
		Pilha p = new Pilha();
		// Embaralha a carta:
		p.embaralha();
		
		boolean ganhou = false;

		int jogadores;
		
		// Garante que no jogo haverão apenas de 2 a 5 jogadores.
		do {
			jogadores = Integer.parseInt(JOptionPane.showInputDialog("Quantos jogadores irão jogar?"));
		} while (jogadores < 2 || jogadores > 5);
		
		// Cada jogador deverá escolher uma pizza.
		for (int i = 1; i <= jogadores; i++) {
			String menu = "Jogador " + i + ", selecione sua pizza:\n\n";
			
			if (!temCalabresa) {
				menu += "1 - Calabresa (Calabresa / Cebola / Queijo / Tomate / Azeitona)\n";
			} if (!temAlCapone) {
				menu += "2 - Al Capone (Tomate / Cebola / Queijo / Presunto / Azeitona)\n";
			} if (!temOvos) {
				menu += "3 - Ovos (Tomate / Cebola / Queijo / Presunto / Ovos)\n";
			} if (!temVegana) {
				menu += "4 - Vegana (Tomate / Cebola / Brócolis / Milho / Azeitona)\n";
			} if (!temPresunto) {
				menu += "5 - Presunto (Cebola / Presunto / Calabresa / Queijo / Azeitona)\n";
			}
			
			int escolha = Integer.parseInt(JOptionPane.showInputDialog(menu));
			
			// Incluirá um jogador na fila, cada um com seu tipo de pizza.
			if (escolha == 1 && !temCalabresa) {
				f.inclui(0, "calabresa");
				temCalabresa = true;
			} else if (escolha == 2 && !temAlCapone) {
				f.inclui(0, "al capone");
				temAlCapone = true;
			} else if (escolha == 3 && !temOvos) {
				f.inclui(0, "ovos");
				temOvos = true;
			} else if (escolha == 4 && !temVegana) {
				f.inclui(0, "vegana");
				temVegana = true;
			} else if (escolha == 5 && !temPresunto) {
				f.inclui(0, "presunto");
				temPresunto = true;
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, escolha novamente.");
				i--;
			}
		}
		
		// Contador principal da Fila de Jogadores.
		do {
			for (int i = 1; i <= jogadores; i++) {
				
				// Variável do dado.
				Random r = new Random();
				int dado = r.nextInt(6 - 0) + 1;
				
				int posicao = 0;
				int casa = 0;
				
				// Variável auxiliar que irá checar a posição atual do Jogador, e sua pizza.
				int atualPos = f.retiraPos();
				String atualPizza = f.retiraPizza();
			
				System.out.println("O jogador " + i + " jogou o dado! O resultado foi de: " + dado);
				
				if (atualPos + dado >= 35) {
					posicao = atualPos + dado - 35;
				} else {
					posicao = atualPos + dado;
				}
				
				atualPos = posicao;
				casa = tabuleiro.retiraPosicao(posicao);	
				System.out.println("O jogador " + i + " está agora na posição " + posicao + " do tabuleiro. \n");
				
				// ============================================================================================================================
				// VERIFICA A CASA QUE O JOGADOR ATUAL CAIU
				
				if (!calabresa.estaVazio() && !alCapone.estaVazio() && !ovos.estaVazio() && !vegana.estaVazio() && !presunto.estaVazio()) {
					if (casa == 0) {
						
						// PERDE TUDO
						
						System.out.println("PERDE TUDO! O jogador " + i + " perdeu todos os ingredientes.\n\n");
						// Retira todos os ingredientes do jogador atual e inicia-os de novo.
						if (atualPizza.equalsIgnoreCase("calabresa")) {
							while (!calabresa.estaVazio()) {
								calabresa.retiraPosicao(0);
							}
							iniciaPizzaCalabresa(calabresa);
						} else if (atualPizza.equalsIgnoreCase("al capone")) {
							while (!alCapone.estaVazio()) {
								alCapone.retiraPosicao(0);
							}
							iniciaPizzaAlCapone(alCapone);
						} else if (atualPizza.equalsIgnoreCase("ovos")) {
							while (!ovos.estaVazio()) {
								ovos.retiraPosicao(0);
							}
							iniciaPizzaOvos(ovos);
						} else if (atualPizza.equalsIgnoreCase("vegana")) {
							while (!vegana.estaVazio()) {
								vegana.retiraPosicao(0);
							}
							iniciaPizzaVegana(vegana);
						} else if (atualPizza.equalsIgnoreCase("presunto")) {
							while (!presunto.estaVazio()) {
								presunto.retiraPosicao(0);
							}
							iniciaPizzaPresunto(presunto);
						}
					} else if (casa == 1 || casa == 23) {
						
						// CEBOLA
						
						switch (atualPizza) {
							case "calabresa":
								for (int j = 0; j < calabresa.comprimento(); j++) {
									if (calabresa.mostraPosicao(j).equalsIgnoreCase("cebola")){
										System.out.println("O Jogador " + i + " encontrou o ingrediente " + calabresa.mostraPosicao(j) +
												", restam mais " + (calabresa.comprimento()-1) + " ingredientes.\n");
										calabresa.retiraPosicao(j);									
									}
								}
								break;
							case "al capone":
								for (int j = 0; j < alCapone.comprimento(); j++) {
									if (alCapone.mostraPosicao(j).equalsIgnoreCase("cebola")){
										System.out.println("O Jogador " + i + " encontrou o ingrediente " + alCapone.mostraPosicao(j) +
												", restam mais " + (alCapone.comprimento()-1) + " ingredientes.\n");
										alCapone.retiraPosicao(j);
									}
								}
								break;
							case "ovos":
								for (int j = 0; j < ovos.comprimento(); j++) {
									if (ovos.mostraPosicao(j).equalsIgnoreCase("cebola")){
										System.out.println("O Jogador " + i + " encontrou o ingrediente " + ovos.mostraPosicao(j) +
												", restam mais " + (ovos.comprimento()-1) + " ingredientes.\n");
										ovos.retiraPosicao(j);
									}
								}
								break;
							case "vegana":
								for (int j = 0; j < vegana.comprimento(); j++) {
									if (vegana.mostraPosicao(j).equalsIgnoreCase("cebola")){
										System.out.println("O Jogador " + i + " encontrou o ingrediente " + vegana.mostraPosicao(j) +
											", restam mais " + (vegana.comprimento()-1) + " ingredientes.\n");
										vegana.retiraPosicao(j);
									}
								}
								break;
							case "presunto":
								for (int j = 0; j < presunto.comprimento(); j++) {
									if (presunto.mostraPosicao(j).equalsIgnoreCase("cebola")){
										System.out.println("O Jogador " + i + " encontrou o ingrediente " + presunto.mostraPosicao(j) +
												", restam mais " + (presunto.comprimento()-1) + " ingredientes.\n");
										presunto.retiraPosicao(j);
									}
								}
								break;
						}
					} else if (casa == 2 || casa == 5 || casa == 6 || casa == 10 || casa == 11 || casa == 14 || casa == 18 || casa == 19 ||
							casa == 21 || casa == 24 || casa == 26 || casa == 28 || casa == 31 || casa == 34) {
						// ===================================================================================================================
						// SORTE OU AZAR
						
						String cartaRetirada = p.desempilha();
						if (cartaRetirada.equalsIgnoreCase("perde")) {
							System.out.println("CARTA DE AZAR!\nQue Azar! Menos um ingrediente para você!");
							switch (atualPizza) {
								case "calabresa":
									System.out.println("O Jogador " + i + " perdeu um ingrediente! Restam mais "
										+ (calabresa.comprimento()+1) + " ingredientes.\n");
									calabresa.incluiFinal("calabresa");
									
									break;
								case "al capone":
									System.out.println("O Jogador " + i + " perdeu um ingrediente! Restam mais "
										+ (alCapone.comprimento()+1) + " ingredientes.\n");
									alCapone.incluiFinal("tomate");
									
									break;
								case "ovos":
									System.out.println("O Jogador " + i + " perdeu um ingrediente! Restam mais "
										+ (ovos.comprimento()+1) + " ingredientes.\n");
									ovos.incluiFinal("tomate");
									
									break;
								case "vegana":
									System.out.println("O Jogador " + i + " perdeu um ingrediente! Restam mais "
										+ (vegana.comprimento()+1) + " ingredientes.\n");
									vegana.incluiFinal("tomate");
									
									break;
								case "presunto":
									System.out.println("O Jogador " + i + " perdeu um ingrediente! Restam mais "
										+ (presunto.comprimento()+1) + " ingredientes.\n");
									presunto.incluiFinal("cebola");
									
									break;
							}
						} else if (cartaRetirada.equalsIgnoreCase("ganha2")) {
							System.out.println("CARTA DE SORTE!\nQue Sorte! Mais dois ingredientes para você!");
							switch (atualPizza) {
								case "calabresa":
									for (int a = 0; a < 2; a++) {
										if (!calabresa.estaVazio()) {
											calabresa.retiraPosicao(0);
										}
									}
									System.out.println("O Jogador " + i + " ganhou dois ingredientes! Restam mais "
											+ (calabresa.comprimento()) + " ingredientes.\n");
									break;
								case "al capone":
									for (int a = 0; a < 2; a++) {
										if (!alCapone.estaVazio()) {
											alCapone.retiraPosicao(0);
										}
									}
									System.out.println("O Jogador " + i + " ganhou dois ingredientes! Restam mais "
											+ (alCapone.comprimento()) + " ingredientes.\n");
									break;
								case "ovos":
									for (int a = 0; a < 2; a++) {
										if (!ovos.estaVazio()) {
											ovos.retiraPosicao(0);
										}
									}
									System.out.println("O Jogador " + i + " ganhou dois ingredientes! Restam mais "
											+ (ovos.comprimento()) + " ingredientes.\n");
									break;
								case "vegana":
									for (int a = 0; a < 2; a++) {
										if (!vegana.estaVazio()) {
											vegana.retiraPosicao(0);
										}
									}
									System.out.println("O Jogador " + i + " ganhou dois ingredientes! Restam mais "
											+ (vegana.comprimento()) + " ingredientes.\n");
									break;
								case "presunto":
									for (int a = 0; a < 2; a++) {
										if (!presunto.estaVazio()) {
											presunto.retiraPosicao(0);
										}
									}
									System.out.println("O Jogador " + i + " ganhou dois ingredientes! Restam mais "
											+ (presunto.comprimento()) + " ingredientes.\n");
									break;
							}
						} else if (cartaRetirada.equalsIgnoreCase("retira")) {
						// ===================================================================================================================	
						// CARTAS PARA RETIRAR DE OUTRO JOGADOR	
							String opcao = "CARTA DE SORTE! Jogador " + i + ", selecione o portador de pizza que perderá dois ingredientes:\n\n";
							
							// Mostra todas as opções para retirar ingredientes, menos a atual (que é quem tirou a carta de sorte).
							if (temCalabresa && !atualPizza.equalsIgnoreCase("calabresa")) {
								opcao += "1 - Pizza de Calabresa\n";
							} if (temAlCapone && !atualPizza.equalsIgnoreCase("al capone")) {
								opcao += "2 - Pizza de Al Capone\n";
							} if (temOvos && !atualPizza.equalsIgnoreCase("ovos")) {
								opcao += "3 - Pizza de Ovos\n";
							} if (temVegana && !atualPizza.equalsIgnoreCase("vegana")) {
								opcao += "4 - Pizza Vegana\n";
							} if (temPresunto && !atualPizza.equalsIgnoreCase("presunto")) {
								opcao += "5 - Pizza de Presunto\n";
							}
							
							int pizzaEscolhida;
							
							pizzaEscolhida = Integer.parseInt(JOptionPane.showInputDialog(opcao));
							
							if (pizzaEscolhida == 1) {
								opcao = "calabresa";
							}
							if (pizzaEscolhida == 2) {
								opcao = "al capone";
							}
							if (pizzaEscolhida == 3) {
								opcao = "ovos";
							}
							if (pizzaEscolhida == 4) {
								opcao = "vegana";
							}
							if (pizzaEscolhida == 5) {
								opcao = "presunto";
							}
							
							switch (opcao) {
							case "calabresa":
								System.out.println("O Jogador com a pizza de Calabresa perdeu dois ingredientes! Restam mais "
									+ (calabresa.comprimento()+2) + " ingredientes.\n");
								calabresa.incluiFinal("calabresa");
								calabresa.incluiFinal("cebola");
								break;
							case "al capone":
								System.out.println("O Jogador com a pizza de Al Capone perdeu dois ingredientes! Restam mais "
									+ (alCapone.comprimento()+2) + " ingredientes.\n");
								alCapone.incluiFinal("tomate");
								alCapone.incluiFinal("cebola");
								break;
							case "ovos":
								System.out.println("O Jogador com a pizza de Ovos perdeu dois ingredientes! Restam mais "
									+ (ovos.comprimento()+2) + " ingredientes.\n");
								ovos.incluiFinal("tomate");
								ovos.incluiFinal("cebola");
								break;
							case "vegana":
								System.out.println("O Jogador com a pizza Vegana perdeu dois ingredientes! Restam mais "
									+ (vegana.comprimento()+2) + " ingredientes.\n");
								vegana.incluiFinal("tomate");
								vegana.incluiFinal("cebola");
								break;
							case "presunto":
								System.out.println("O Jogador com a pizza de Presunto perdeu dois ingredientes! Restam mais "
									+ (presunto.comprimento()+2) + " ingredientes.\n");
								presunto.incluiFinal("cebola");
								presunto.incluiFinal("presunto");
								break;
						}							
						// FIM CARTAS PARA RETIRAR DE OUTRO JOGADOR
						// ===================================================================================================================	
						} else if (cartaRetirada.equalsIgnoreCase("queima")) {
							System.out.println("CARTA DE AZAR!\nQue azar! Sua pizza queimou.\nO Jogador " + i + " perdeu todos os seus ingredientes.\n");
							if (atualPizza.equalsIgnoreCase("calabresa")) {
								while (!calabresa.estaVazio()) {
									calabresa.retiraPosicao(0);
								}
								iniciaPizzaCalabresa(calabresa);
							} else if (atualPizza.equalsIgnoreCase("al capone")) {
								while (!alCapone.estaVazio()) {
									alCapone.retiraPosicao(0);
								}
								iniciaPizzaAlCapone(alCapone);
							} else if (atualPizza.equalsIgnoreCase("ovos")) {
								while (!ovos.estaVazio()) {
									ovos.retiraPosicao(0);
								}
								iniciaPizzaOvos(ovos);
							} else if (atualPizza.equalsIgnoreCase("vegana")) {
								while (!vegana.estaVazio()) {
									vegana.retiraPosicao(0);
								}
								iniciaPizzaVegana(vegana);
							} else if (atualPizza.equalsIgnoreCase("presunto")) {
								while (!presunto.estaVazio()) {
									presunto.retiraPosicao(0);
								}
								iniciaPizzaPresunto(presunto);
							}
						}
						
						if (p.estaVazia()) {
							p.embaralha();
						}
						
						// ACABAM AS CARTAS DE SORTE OU AZAR
						// ===================================================================================================================	
					} else if (casa == 4 || casa == 29) {
						// QUEIJO
						switch (atualPizza) {
						case "calabresa":
							for (int j = 0; j < calabresa.comprimento(); j++) {
								if (calabresa.mostraPosicao(j).equalsIgnoreCase("queijo")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + calabresa.mostraPosicao(j) +
											", restam mais " + (calabresa.comprimento()-1) + " ingredientes.\n");
									calabresa.retiraPosicao(j);
								}
							}
							break;
						case "al capone":
							for (int j = 0; j < alCapone.comprimento(); j++) {
								if (alCapone.mostraPosicao(j).equalsIgnoreCase("queijo")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + alCapone.mostraPosicao(j) +
											", restam mais " + (alCapone.comprimento()-1) + " ingredientes.\n");
									alCapone.retiraPosicao(j);
								}
							}
							break;
						case "ovos":
							for (int j = 0; j < ovos.comprimento(); j++) {
								if (ovos.mostraPosicao(j).equalsIgnoreCase("queijo")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + ovos.mostraPosicao(j) +
											", restam mais " + (ovos.comprimento()-1) + " ingredientes.\n");
									ovos.retiraPosicao(j);
								}
							}
							break;
						case "presunto":
							for (int j = 0; j < presunto.comprimento(); j++) {
								if (presunto.mostraPosicao(j).equalsIgnoreCase("queijo")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + presunto.mostraPosicao(j) +
											", restam mais " + (presunto.comprimento()-1) + " ingredientes.\n");
									presunto.retiraPosicao(j);
								}
							}
							break;
					}	
					} else if (casa == 7 || casa == 16) {
						// TOMATE
						switch (atualPizza) {
						case "calabresa":
							for (int j = 0; j < calabresa.comprimento(); j++) {
								if (calabresa.mostraPosicao(j).equalsIgnoreCase("tomate")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + calabresa.mostraPosicao(j) +
											", restam mais " + (calabresa.comprimento()-1) + " ingredientes.\n");
									calabresa.retiraPosicao(j);
								}
							}
							break;
						case "al capone":
							for (int j = 0; j < alCapone.comprimento(); j++) {
								if (alCapone.mostraPosicao(j).equalsIgnoreCase("tomate")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + alCapone.mostraPosicao(j) +
											", restam mais " + (alCapone.comprimento()-1) + " ingredientes.\n");
									alCapone.retiraPosicao(j);
								}
							}
							break;
						case "ovos":
							for (int j = 0; j < ovos.comprimento(); j++) {
								if (ovos.mostraPosicao(j).equalsIgnoreCase("tomate")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + ovos.mostraPosicao(j) +
											", restam mais " + (ovos.comprimento()-1) + " ingredientes.\n");
									ovos.retiraPosicao(j);
								}
							}
							break;
						case "vegana":
							for (int j = 0; j < vegana.comprimento(); j++) {
								if (vegana.mostraPosicao(j).equalsIgnoreCase("tomate")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + vegana.mostraPosicao(j) +
											", restam mais " + (vegana.comprimento()-1) + " ingredientes.\n");
									vegana.retiraPosicao(j);
								}
							}
							break;
					}	
					} else if (casa == 8 || casa == 22) {
						// OVOS
						switch (atualPizza) {
						case "ovos":
							for (int j = 0; j < ovos.comprimento(); j++) {
								if (ovos.mostraPosicao(j).equalsIgnoreCase("ovos")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + ovos.mostraPosicao(j) +
											", restam mais " + (ovos.comprimento()-1) + " ingredientes.\n");
									ovos.retiraPosicao(j);
								}
							}
							break;
					}	
					} else if (casa == 15 || casa == 27) {
						// PRESUNTO
						switch (atualPizza) {
						case "al capone":
							for (int j = 0; j < alCapone.comprimento(); j++) {
								if (alCapone.mostraPosicao(j).equalsIgnoreCase("presunto")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + alCapone.mostraPosicao(j) +
											", restam mais " + (alCapone.comprimento()-1) + " ingredientes.\n");
									alCapone.retiraPosicao(j);
								}
							}
							break;
						case "ovos":
							for (int j = 0; j < ovos.comprimento(); j++) {
								if (ovos.mostraPosicao(j).equalsIgnoreCase("presunto")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + ovos.mostraPosicao(j) +
											", restam mais " + (ovos.comprimento()-1) + " ingredientes.\n");
									ovos.retiraPosicao(j);
								}
							}
							break;
						case "presunto":
							for (int j = 0; j < presunto.comprimento(); j++) {
								if (presunto.mostraPosicao(j).equalsIgnoreCase("presunto")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + presunto.mostraPosicao(j) +
											", restam mais " + (presunto.comprimento()-1) + " ingredientes.\n");
									presunto.retiraPosicao(j);
								}
							}
							break;
					}			
					} else if (casa == 9 || casa == 13 || casa == 30 || casa == 33) {
						// AZEITONA
						switch (atualPizza) {
						case "calabresa":
							for (int j = 0; j < calabresa.comprimento(); j++) {
								if (calabresa.mostraPosicao(j).equalsIgnoreCase("azeitona")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + calabresa.mostraPosicao(j) +
											", restam mais " + (calabresa.comprimento()-1) + " ingredientes.\n");
									calabresa.retiraPosicao(j);
								}
							}
							break;
						case "al capone":
							for (int j = 0; j < alCapone.comprimento(); j++) {
								if (alCapone.mostraPosicao(j).equalsIgnoreCase("azeitona")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + alCapone.mostraPosicao(j) +
											", restam mais " + (alCapone.comprimento()-1) + " ingredientes.\n");
									alCapone.retiraPosicao(j);
								}
							}
							break;
						case "vegana":
							for (int j = 0; j < vegana.comprimento(); j++) {
								if (vegana.mostraPosicao(j).equalsIgnoreCase("azeitona")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + vegana.mostraPosicao(j) +
											", restam mais " + (vegana.comprimento()-1) + " ingredientes.\n");
									vegana.retiraPosicao(j);
								}
							}
							break;
						case "presunto":
							for (int j = 0; j < presunto.comprimento(); j++) {
								if (presunto.mostraPosicao(j).equalsIgnoreCase("azeitona")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + presunto.mostraPosicao(j) +
											", restam mais " + (presunto.comprimento()-1) + " ingredientes.\n");
									presunto.retiraPosicao(j);
								}
							}
							break;
					}	
					} else if (casa == 12 || casa == 17) {
						// MILHO
						switch (atualPizza) {
						case "vegana":
							for (int j = 0; j < vegana.comprimento(); j++) {
								if (vegana.mostraPosicao(j).equalsIgnoreCase("milho")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + vegana.mostraPosicao(j) +
											", restam mais " + (vegana.comprimento()-1) + " ingredientes.\n");
									vegana.retiraPosicao(j);
								}
							}
							break;
					}	
					} else if (casa == 20 || casa == 32) {
						// BROCOLIS
						switch (atualPizza) {
						case "vegana":
							for (int j = 0; j < vegana.comprimento(); j++) {
								if (vegana.mostraPosicao(j).equalsIgnoreCase("brócolis")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + vegana.mostraPosicao(j) +
											", restam mais " + (vegana.comprimento()-1) + " ingredientes.\n");
									vegana.retiraPosicao(j);
								}
							}
							break;
					}	
					} else if (casa == 3 || casa == 25) {
						// CALABRESA
						switch (atualPizza) {
						case "calabresa":
							for (int j = 0; j < calabresa.comprimento(); j++) {
								if (calabresa.mostraPosicao(j).equalsIgnoreCase("calabresa")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + calabresa.mostraPosicao(j) +
											", restam mais " + (calabresa.comprimento()-1) + " ingredientes.\n");
									calabresa.retiraPosicao(j);
								}
							}
							break;
						case "presunto":
							for (int j = 0; j < presunto.comprimento(); j++) {
								if (presunto.mostraPosicao(j).equalsIgnoreCase("calabresa")){
									System.out.println("O Jogador " + i + " encontrou o ingrediente " + presunto.mostraPosicao(j) +
											", restam mais " + (presunto.comprimento()-1) + " ingredientes.\n");
									presunto.retiraPosicao(j);
								}
							}
							break;
					}	
					}
				}
					
				if (calabresa.estaVazio()) {
					System.out.println("PARABÉNS! O Jogador " + i + " ganhou, com a pizza de Calabresa.");
					ganhou = true;
				} else if (alCapone.estaVazio()) {
					System.out.println("PARABÉNS! O Jogador " + i + " ganhou, com a pizza de Al Capone.");
					ganhou = true;
				} else if (ovos.estaVazio()) {
					System.out.println("PARABÉNS! O Jogador " + i + " ganhou, com a pizza de Ovos.");
					ganhou = true;
				} else if (vegana.estaVazio()) {
					System.out.println("PARABÉNS! O Jogador " + i + " ganhou, com a pizza Vegana.");
					ganhou = true;
				} else if (presunto.estaVazio()) {
					System.out.println("PARABÉNS! O Jogador " + i + " ganhou, com a pizza de Presunto.");
					ganhou = true;
				}
				
				f.inclui(atualPos, atualPizza);

				if (ganhou == true) {
					i = 10;
				}
			}
		} while (!ganhou);
		
	}
	
	public static void iniciaPizzaCalabresa(Pizza calabresa) {
		calabresa.incluiFinal("Calabresa");
		calabresa.incluiFinal("Cebola");
		calabresa.incluiFinal("Queijo");
		calabresa.incluiFinal("Tomate");
		calabresa.incluiFinal("Azeitona");
	}
	
	public static void iniciaPizzaAlCapone(Pizza alCapone) {
		alCapone.incluiFinal("Tomate");
		alCapone.incluiFinal("Cebola");
		alCapone.incluiFinal("Queijo");
		alCapone.incluiFinal("Presunto");
		alCapone.incluiFinal("Azeitona");
	}
	
	public static void iniciaPizzaOvos(Pizza ovos) {
		ovos.incluiFinal("Tomate");
		ovos.incluiFinal("Cebola");
		ovos.incluiFinal("Queijo");
		ovos.incluiFinal("Presunto");
		ovos.incluiFinal("Ovos");
	}
	
	public static void iniciaPizzaVegana(Pizza vegana) {
		vegana.incluiFinal("Tomate");
		vegana.incluiFinal("Cebola");
		vegana.incluiFinal("Brócolis");
		vegana.incluiFinal("Milho");
		vegana.incluiFinal("Azeitona");
	}
	
	public static void iniciaPizzaPresunto(Pizza presunto) {
		presunto.incluiFinal("Cebola");
		presunto.incluiFinal("Presunto");
		presunto.incluiFinal("Calabresa");
		presunto.incluiFinal("Queijo");
		presunto.incluiFinal("Azeitona");
	}
	
	public static void iniciaTabuleiro(Tabuleiro tabuleiro) {
		tabuleiro.incluiInicio(0);
		tabuleiro.incluiPosicao(1, 1);
		tabuleiro.incluiPosicao(2, 2);
		tabuleiro.incluiPosicao(3, 3);
		tabuleiro.incluiPosicao(4, 4);
		tabuleiro.incluiPosicao(5, 5);
		tabuleiro.incluiPosicao(6, 6);
		tabuleiro.incluiPosicao(7, 7);
		tabuleiro.incluiPosicao(8, 8);
		tabuleiro.incluiPosicao(9, 9);
		tabuleiro.incluiPosicao(10, 10);
		tabuleiro.incluiPosicao(11, 11);
		tabuleiro.incluiPosicao(12, 12);
		tabuleiro.incluiPosicao(13, 13);
		tabuleiro.incluiPosicao(14, 14);
		tabuleiro.incluiPosicao(15, 15);
		tabuleiro.incluiPosicao(16, 16);
		tabuleiro.incluiPosicao(17, 17);
		tabuleiro.incluiPosicao(18, 18);
		tabuleiro.incluiPosicao(19, 19);
		tabuleiro.incluiPosicao(20, 20);
		tabuleiro.incluiPosicao(21, 21);
		tabuleiro.incluiPosicao(22, 22);
		tabuleiro.incluiPosicao(23, 23);
		tabuleiro.incluiPosicao(24, 24);
		tabuleiro.incluiPosicao(25, 25);
		tabuleiro.incluiPosicao(26, 26);
		tabuleiro.incluiPosicao(27, 27);
		tabuleiro.incluiPosicao(28, 28);
		tabuleiro.incluiPosicao(29, 29);
		tabuleiro.incluiPosicao(30, 30);
		tabuleiro.incluiPosicao(31, 31);
		tabuleiro.incluiPosicao(32, 32);
		tabuleiro.incluiPosicao(33, 33);
		tabuleiro.incluiPosicao(34, 34);
	}

}

