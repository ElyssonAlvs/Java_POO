/*
 @002 Carro & Controla a quantidade de pessoas e sai para passear

Essa atividade se propõe a implementar um carro ecológico que pode passear pela cidade.
Ele deve poder embarcar e desembarcar pessoas, colocar combustível e andar.
 */


package Carro;

import java.util.Scanner;

class Car {
    public int pass; // Passageiros
    public int passMax; // limite de Passageiros
    public int gas; // tanque
    public int gasMax; // limite do tanque
    public int km; // quantidade de quilometragem

    public Car() {
        gas = 0;
        pass = 0;
        km = 0;

        passMax = 2;
        gasMax = 100;
    }

    public String toString() {
        return "pass: " + this.pass + ", gas: " + this.gas + ", km: " + this.km;
    }

    public void enter() {
        if (pass < passMax) {
            this.pass++;
        } else {
            System.out.println("fail: limite de pessoas atingido");
        }
    }

    public void leave() {
        if (pass != 0) {
            this.pass--;
        } else {
            System.out.println("fail: nao ha ninguem no carro");
        }
    }

    public void fuel(int gas) {
        if (gas >= gasMax || this.gas + gas >= gasMax) {
            this.gas = this.gasMax;
        } else {
            this.gas += gas;
        }
    }

    public void drive(int km) {
        if (this.pass == 0) {
            System.out.println("fail: nao ha ninguem no carro");
        } else if (this.gas > 0 && this.gas < km) {
            System.out.println("fail: tanque vazio apos andar " + this.gas + " km");
            this.km += km - (km - this.gas);
            this.gas = 0;
        } else if (gas == 0) {
            System.out.println("fail: tanque vazio");
        } else {
            this.km += km;
            this.gas -= km;
        }
    }

};

class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Car car = new Car();

        while (true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            System.out.println("$" + line);
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("enter")) {
                car.enter();
            } else if (ui[0].equals("leave")) {
                car.leave();
            } else if (ui[0].equals("show")) {
                System.out.println(car.toString());
            } else if (ui[0].equals("drive")) {// km
                car.drive(Integer.parseInt(ui[1]));
            } else if (ui[0].equals("fuel")) {// gas
                car.fuel(Integer.parseInt(ui[1]));
            } else {
                System.out.println("fail: comando invalido");
            }
        }
        scanner.close();
    }
}