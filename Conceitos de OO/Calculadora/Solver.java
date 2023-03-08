package Calculadora;
/*
 @001 Calculadora & Utilizando uma calculora que gasta e recarrega bateria

 O objetivo dessa atividade é implementar uma calculadora a bateria. 
 Se há bateria, ela executa operações de soma, multiplicação e divisão. 
 É possível também mostrar a quantidade de bateria e recarregar a calculadora. 
 Ela avisa quando está sem bateria e se há tentativa de divisão por 0.
 */

import java.util.Scanner;
import java.text.*;

class Calculator {
    public int batteryMax;
    public int battery;
    public float display;

    // Inicia os atributos, battery e display começam com o zero.
    public Calculator(int batteryMax) {
        this.display = 0;
        this.battery = 0;
        this.batteryMax = batteryMax;

    }

    // Aumenta a bateria, porém não além do máximo.
    public void chargeBattery(int value) {

        if ((value + battery) > batteryMax) {
            battery = batteryMax;
        } else {
            battery += value; // battery = battery + value
        }

    }

    // Tenta gastar uma unidade da bateria e emite um erro se não conseguir.
    public boolean useBattery() {

        if (battery > 0) {
            battery--;
            return true;
        } else {
            System.out.println("fail: bateria insuficiente");
            return false;
        }

    }

    // Se conseguir gastar bateria, armazene a soma no atributo display.
    public void sum(int a, int b) {

        if (useBattery()) {
            display = a + b;
        }

    }

    // Se conseguir gastar bateria e não for divisão por 0.
    public void division(int num, int den) {

        if (useBattery()) {
            if (den != 0) {
                display = (float) num / den;
            } else {
                System.out.println("fail: divisao por zero");
            }
        }
    }

    // Retorna o conteúdo do display com duas casas decimais.
    public String toString() {
        DecimalFormat form = new DecimalFormat("0.00");
        return "display = " + form.format(this.display).replace(",", ".") + ", battery = " + this.battery;
    }
}

class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator(0);
        while (true) {
            String line = scanner.nextLine();
            System.out.println("$" + line);
            String ui[] = line.split(" ");
            if (line.equals("end")) {
                break;
            } else if (ui[0].equals("init")) { // batteryMax
                calc = new Calculator(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("show")) {
                System.out.println(calc);
            } else if (ui[0].equals("charge")) {
                calc.chargeBattery(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("sum")) {// value value
                calc.sum(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
            } else if (ui[0].equals("div")) {// value value
                calc.division(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}