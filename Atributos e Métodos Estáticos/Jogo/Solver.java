/*
Nosso jogo consiste em um tabuleiro contendo uma trilha de quadrados e um conjunto de peças coloridas.
No início do jogo, cada jogador recebe uma peça; todas as peças são inicialmente posicionadas logo antes
da primeira casa da trilha.

O jogo prossegue em rodadas. Em cada rodada, os jogadores rolam um par de dados e movem suas peças para frente
um número de quadrados igual ao resultado obtido pelos dados. Os jogadores rolam os dados sempre na mesma ordem
(jogador A, depois jogador B, etc.) nas rodadas. A maioria dos quadrados no tabuleiro são quadrados simples, mas
alguns são “armadilhas”. Se a peça de um jogador cair em um quadrado da armadilha no final do movimento do jogador,
o jogador perde a próxima rodada. Ou seja, ela/ele não jogue os dados, e sua peça fica uma rodada sem se mover.

Haverá exatamente três armadilhas na trilha.

O vencedor do jogo é o jogador cuja peça chega primeiro ao final da trilha. O fim da trilha é depois da última casa
do tabuleiro. Considere, por exemplo, a placa da figura acima, que tem quadrados numerados de 1 a 48. No início, as
peças são posicionadas no local marcado como ‘Início’, ou seja, antes do quadrado número 1. Portanto, se um jogador
rolar um 7 (dados mostrando 2 e 5 por exemplo), sua peça é posicionada na casa número 7 no final da primeira rodada
do jogo. Além disso, se a peça de um jogador estiver posicionada na casa 41, o jogador precisa de um resultado de
rolagem de pelo menos 8 para chegar ao final da trilha e ganhar o jogo.Observe também que não haverá empate no jogo.
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.Locale;

class Jogo {

    private final int numJogadores;
    private final int[] armadilha;
    private final int[] posicao;
    private final boolean[] podeJogar;
    private int numArmadilha = 0;
    private final int numCasas;
    private int prox = 1;
    private boolean fimDoJogo = false;

    public Jogo(int numJogadores, int numCasas) {
        this.numJogadores = numJogadores;
        this.numCasas = numCasas;
        posicao = new int[numJogadores];
        podeJogar = new boolean[numCasas];
        armadilha = new int[numCasas + 1];
        Arrays.fill(armadilha, 1);
        Arrays.fill(posicao, 0);
        Arrays.fill(podeJogar, true);
    }

    public void addArmadilha(int t) {
        if (numArmadilha < 3) {
            armadilha[t] = 0; // Armazena o indice das armadilhas;
            numArmadilha++;
        }
    }

    public void addMove(int d1, int d2) {
        int casas = d1 + d2;
        if (!fimDoJogo) {
            if (prox > numJogadores) {
                prox = 1;
            }
            if (podeJogar[posicao[prox - 1]]) {
                posicao[prox - 1] += casas;
                System.out.println("O jogador " + prox + " vai para a casa " + posicao[prox - 1]);
                if (posicao[prox - 1] > numCasas) {
                    System.out.println("O jogador " + (prox) + " venceu o jogo");
                    fimDoJogo = true;
                    this.prox++;
                    return;
                }
                if (armadilha[posicao[prox - 1]] == 0) {
                    System.out.println("O jogador " + (prox) + " caiu em um armadilha");
                    armadilha[posicao[prox - 1] + casas] = 1;
                    podeJogar[posicao[prox - 1]] = false;
                }
            } else {
                System.out.println("O jogador " + (prox) + " passa a vez");
                podeJogar[posicao[prox - 1]] = true;
                prox++;
                addMove(d1, d2);
                prox--;
            }
            prox++;
        } else {
            System.out.println("O jogo acabou");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i != posicao.length) {
            if (i == posicao.length - 1) {
                sb.append("PosJogador[").append(i + 1).append("] = ").append(posicao[i]);
            } else {
                sb.append("PosJogador[").append(i + 1).append("] = ").append(posicao[i]).append(('\n'));
            }
            i++;
        }
        return sb.toString() + '\n';
    }
}

class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        Jogo jogo = new Jogo(0, 0);

        label: while (true) {
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            System.out.println("$" + line);
            switch (ui[0]) {
                case "init":
                    jogo = new Jogo(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
                    break;
                case "end":
                    break label;
                case "addArmadilha":
                    jogo.addArmadilha(Integer.parseInt(ui[1]));
                    break;
                case "addMove":
                    int d1 = Integer.parseInt(ui[1]);
                    int d2 = Integer.parseInt(ui[2]);
                    jogo.addMove(d1, d2);
                    break;
                case "show":
                    System.out.println(jogo);
                    break;
                default:
                    System.out.println("fail: comando invalido");
                    break;
            }
        }
        scanner.close();
    }
}
