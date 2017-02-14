/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import javax.swing.JOptionPane;

/**
 *
 * @author kfelsner
 */
public class Tablero {

    public int xMax = 8;
    public int yMax = 8;
    public Celdas[][] table = new Celdas[xMax][yMax];
    public static int[][] vecinas = {
        {-1, -1}, {-1, 0}, {-1, +1}, {0, -1},
        {0, +1}, {+1, -1}, {+1, 0}, {+1, +1}
    };
    public static int totalMinas = 0;
    public int cMinas = 0;
    public int cCasillas = 0;
    public static boolean win = false;

    //Constructor
    public Tablero() {
        makeTable();
    }

    //Comprueba que las coordenadas estén dentro del tablero.
    public boolean insTable(int x, int y) {
        return (!((x < 0) || (x >= xMax) || (y < 0) || (y >= yMax)));
    }

    //Crea la Tabla.
    public void makeTable() {
        for (int x = 0; x < yMax; x++) {
            for (int y = 0; y < xMax; y++) {
                table[x][y] = new Celdas();
            }
        }
    }

    //Muestra el mapa.
    public void showTable() {
        for (int x = 0; x < yMax; x++) {
            for (int y = 0; y < xMax; y++) {
                if (table[x][y].revelada) {
                    if (table[x][y].mina) {
                        System.out.print("P ");
                    } else {

                        System.out.print(table[x][y].numero + " ");
                    }
                } else {
                    System.out.print(table[x][y].forma + " ");
                }
            }
            System.out.println();
        }
    }

    //Revela TODO el mapa.
    public void showAll() {
        for (int x = 0; x < yMax; x++) {
            for (int y = 0; y < xMax; y++) {
                if (table[x][y].mina) {
                    System.out.print("P ");
                } else {

                    System.out.print(table[x][y].numero + " ");
                }
            }
            System.out.println();
        }
    }

    //Genera minas aleatorias, las coloca y aumenta el contador de minas.
    public void putMines(int minas) {

        for (int k = 0; k < minas; k++) {

            int x = (int) (Math.random() * xMax);
            int y = (int) (Math.random() * yMax);

            if (insTable(x, y)) {
                if (!(table[x][y].mina)) {
                    table[x][y].mina = true;
                    totalMinas++;
                } else {
                    k--;
                }
            }
        }
    }

    //Destapa una casilla.
    public void colocar(int x, int y) {
        if (insTable(x, y)) {
            table[x][y].numero = 0;
            table[x][y].revelada = true;
            if (!table[x][y].mina) {

                contarVecinas(x, y);
            }

            cCasillas++;
            win(x, y);
        }
    }

    //Comprueba si ha ganado el jugador.
    public void win(int x, int y) {
        if (cCasillas == xMax * yMax - totalMinas) {
            win = true;
            JOptionPane.showMessageDialog(null, "HAS GANADOcc:) !");
        }
        if (table[x][y].mina) {
            win = true;
            JOptionPane.showMessageDialog(null, "HAS PERDIDO :( !");
            showAll();
        }
    }

    //Destapa casillas hasta que encuentra una mina cerca.
    public void contarVecinas(int x, int y) {
        for (int k = 0; k < vecinas.length; k++) {
            if (insTable(x + vecinas[k][0], y + vecinas[k][1])) {
                if (table[x + vecinas[k][0]][y + vecinas[k][1]].mina) {
                    table[x][y].numero++;
                }
            }
        }
        if (table[x][y].numero == 0) {
            for (int k = 0; k < vecinas.length; k++) {
                if (insTable(x + vecinas[k][0], y + vecinas[k][1])) {
                    if (!table[x + vecinas[k][0]][y + vecinas[k][1]].revelada) {
                        colocar(x + vecinas[k][0], y + vecinas[k][1]);
                    }
                }
            }
        }
    }
}
