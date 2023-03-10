/*
A classe MediaNotas armazena um conjunto de notas com os seguintes atributos e métodos:

Atributos:

ArrayList <Double> notas

Métodos:

addNota(double nota): adiciona uma nova nota
removeNota(int index) : remove a nota com índice index, caso o index seja inválido, a mensagem falha: indice invalido
maiorNota() : devolve a maior nota, caso a quantidade de notas seja zero imprimir uma mensagem falha: quantidade de notas insuficiente
menorNota(): devolve a menor nota, caso a quantidade de notas seja zero, a mensagem falha: quantidade de notas insuficiente deve ser impressa.
double media() devolve a média das notas
double parametriza(): todas as notas devem ser divididas pela maior nota e multiplicadas por 10.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;

class MediaNotas {

    private ArrayList<Double> notas = new ArrayList<Double>();

    public void addNota(double nota) {
        notas.add(nota);
    }

    public void removeNota(int index) {
        notas.remove(index);
    }

    public void mudaNota(int index, double valor) {
        notas.set(index, valor);
    }

    public double maiorNota() {
        double t = 0;
        for (double x : notas) {
            if (x > t) {
                t = x;
            }
        }
        return t;
    }

    public double menorNota() {
        double t = 0;
        for (double x : notas) {
            if (x < t && t == 0) {
                t = x;
            }
        }
        return t;
    }

    double media() {
        double t = 0;
        for (double x : notas) {
            t += x;
        }
        return t / notas.size();
    }

    void parametriza() {
        double t = maiorNota();
        for (int i = 0; i < notas.size(); i++) {
            notas.set(i, (notas.get(i) / t) * 10);
        }
    }

    public String toString() {
        String o = "[";
        for (int i = 0; i < notas.size(); i++) {
            if (i == 0) {
                o += String.format("%.2f", notas.get(i));
            } else {
                o += String.format(", %.2f", notas.get(i));
            }
        }
        return o + "]";
    }

}

class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MediaNotas m = new MediaNotas();

        while (true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            System.out.println("$" + line);
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("addNota")) {
                m.addNota(Double.parseDouble(ui[1]));
            } else if (ui[0].equals("removeNota")) {
                m.removeNota(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("maiorNota")) {
                System.out.println(m.maiorNota());
            } else if (ui[0].equals("menorNota")) {
                System.out.println(m.menorNota());
            } else if (ui[0].equals("media")) {
                System.out.printf(Locale.US, "%.2f\n", m.media());
            } else if (ui[0].equals("parametriza")) {
                m.parametriza();
            } else if (ui[0].equals("show")) {
                System.out.println(m);
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}
