/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Scanner;

/**
 *
 * @author kfelsner
 */
public class Buscaminas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Tablero prueba = new Tablero();
        prueba.putMines(10);
        int x = 0;
        int y = 0;
        while (!Tablero.win) {
            System.out.println("0 1 2 3 4 5 6 7");
            prueba.showTable();
            
            System.out.print("Introduzca una coordenada X: ");
            x = sc.nextInt();
            System.out.print("Introduzca una coordenada Y: ");
            y = sc.nextInt();
            
            prueba.colocar(y, x);
        }

        System.out.println("Numero de Minas = " + Tablero.totalMinas);
    }

}
