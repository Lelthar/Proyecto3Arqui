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

    public Matriz(int m, int n, int numeroMatriz, String tipo, String nombreArchivo) throws IOException {
        this.m = m;
        this.n = n;
        this.numeroMatriz = numeroMatriz;
        this.tipo = tipo;
        this.nombreArchivo = nombreArchivo;
        matrizNumeros = new int[m][n];
        generarNumeroRandom(this.tipo);
        guardarTxt();
    }
    public void generarMatriz(){
        if(tipo != null){
            for(int i = 0; i < m; i++){
                for(int j = 0; i < n; j++){
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
            }
            datosArchivo += "\n";
        }
        bw.write(datosArchivo);
     
        bw.close();
    }
    
}
