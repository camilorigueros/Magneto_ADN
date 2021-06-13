package com.meli.utils;

import java.util.List;

public class Util {
    public static char[][] crearMatriz(List<String> lista){
        char[][] matrixDna = new char[lista.size()][lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                matrixDna[i][j] = lista.get(i).charAt(j);
            }
        }
        return matrixDna;
    }
}
