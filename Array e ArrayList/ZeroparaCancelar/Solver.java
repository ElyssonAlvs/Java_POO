/*
Seu chefe está ao telefone, nervoso. Ele quer que você compute a soma de 
uma sequência de números que ele vai falar para você ao telefone, para
saber o total das vendas em sua mais recente viagem de negócios.

Infelizmente, de vez em quando seu chefe fala números errados para você ao
telefone.

Felizmente, seu chefe rapidamente percebe que falou um número errado e
diz “zero”, que como combinado previamente quer dizer ignore o último
número corrente.

Infelizmente, seu chefe pode cometer erros repetidos, e diz “zero” para
cada erro.

Por exemplo, seu chefe pode falar ao telefone “Um, três, cinco, quatro,
zero, zero, sete, zero, zero, seis”, o que significa uma soma total igual
a 7, conforme explicado na tabela abaixo:

Fala do chefe	Números correntes	Explicação
“um, três, cinco, quatro”	1,3,5,4	registre os quatro números
“zero, zero”	1, 3	ignore os dois últimos números
“sete”	1, 3, 7	registre o sete ao final da lista
“zero, zero”	1	ignore os dois últimos números
“seis”	1, 6	registre seis ao final da lista
Construa uma classe ComputaSequencia com os seguintes métodos:

cadastra(int valor) : se o valor for diferente de zero, o valor será
adicionado na lista; caso contrário, o último elemento é removido
computa() : devolva a soma dos elementos cadastrados.
 */

import java.util.Scanner;
import java.util.ArrayList;

class ComputaSequencia {

    ArrayList<Integer> v = new ArrayList<Integer>();

    public void cadastra(int valor) {
        if (valor != 0) {
            v.add(valor);
        } else {
            v.remove(v.size() - 1);
        }
    }

    public int computa() {
        int soma = 0;
        for (int x : v) {
            soma += x;
        }
        return soma;
    }

    public String toString() {
        return String.format("valores = %s", v);
    }

}

class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ComputaSequencia sequencia = new ComputaSequencia();

        while (true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            System.out.println("$" + line);
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("cadastra")) {
                sequencia.cadastra(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("computa")) {
                System.out.println(sequencia.computa());
            } else if (ui[0].equals("show")) {
                System.out.println(sequencia);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
