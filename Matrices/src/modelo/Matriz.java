/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author gerald
 */
public class Matriz {
    public int[][] matrizNumeros;
    public int m;
    public int n;
    public int numeroMatriz;
    public String tipo;
    public String nombreArchivo;
    public String matrizMostrar;

    public Matriz(int m, int n, int numeroMatriz, String tipo, String nombreArchivo) throws IOException {
        this.m = m;
        this.n = n;
        this.numeroMatriz = numeroMatriz;
        this.tipo = tipo;
        this.nombreArchivo = nombreArchivo;
        this.matrizMostrar = " ";
        matrizNumeros = new int[m][n];
        generarMatriz();
        guardarTxt();
    }

    public Matriz() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void generarMatriz(){
        if(tipo != null){
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    matrizNumeros[i][j] = generarNumeroRandom(tipo);
                }
            }
        }
    }   
    public int generarNumeroRandom(String tipo){
        if(null != tipo)switch (tipo) {
            case "P":{
                int numeroAleatorio = (int) (Math.random()*(100)+1);
                return numeroAleatorio;
                }
            case "N":{
                int numeroAleatorio = (int) (Math.random()*(100+1)-100);
                return numeroAleatorio;
                }
            default:{
                int numeroAleatorio = (int) (Math.random()*(200+1)-100);
                return numeroAleatorio;
                }
        }else
            return 0;
    }
    public void guardarTxt() throws IOException{
        String ruta = nombreArchivo+".txt";
        String datosArchivo = "";
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        datosArchivo += ("Valor de M: "+m+", Valor de N: "+n+"\n");
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                datosArchivo += (matrizNumeros[i][j]+"\t");
                matrizMostrar += (matrizNumeros[i][j]+"\t");
            }
            datosArchivo += "\n";
            matrizMostrar += "\n";
        }
        bw.write(datosArchivo);
     
        bw.close();
    }

    public int[][] getMatrizNumeros() {
        return matrizNumeros;
    }

    public void setMatrizNumeros(int[][] matrizNumeros) {
        this.matrizNumeros = matrizNumeros;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getNumeroMatriz() {
        return numeroMatriz;
    }

    public void setNumeroMatriz(int numeroMatriz) {
        this.numeroMatriz = numeroMatriz;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public void calcularSumaMatrices(int[][] matriz1, int[][] matriz2){
        this.matrizNumeros = new int[matriz1.length][matriz1[0].length];
        this.m = matriz1.length;
        this.n = matriz1[0].length;
        
        for(int i = 0; i < matriz1.length; i++){
            for(int j = 0; j < matriz1[0].length; j++){
                this.matrizNumeros[i][j] = (matriz1[i][j]+matriz2[i][j]);
            }
        }
    }
    public void calcularMatrizPorEscalar(int[][] matriz, int escalar){
        this.matrizNumeros = new int[matriz.length][matriz[0].length];
        this.m = matriz.length;
        this.n = matriz[0].length;
        
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                this.matrizNumeros[i][j] = (matriz[i][j]*escalar);
            }
        }
    }
    public void calcularTranspuestaMatriz(int[][] matriz){
        this.matrizNumeros = new int[matriz[0].length][matriz.length];
        this.n = matriz.length;
        this.m = matriz[0].length;
        
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                this.matrizNumeros[j][i] = matriz[i][j];
            }
        }
    }
}
