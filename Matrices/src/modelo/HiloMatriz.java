/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerald
 */
public class HiloMatriz extends Thread{
    int[] listaOperaciones;
    String nombreArchivo;
    int m;
    int n;
    
    public HiloMatriz(int[] operaciones, String pNombre, int pM, int pN){
        this.listaOperaciones = operaciones;
        this.nombreArchivo = pNombre;
        this.m = pM;
        this.n = pN;
    }
    
    @Override
    public void run() {
        for(int i = 0; i < listaOperaciones.length; i++){
            try {
                operacionElegida(listaOperaciones[i]);
            } catch (IOException ex) {
                Logger.getLogger(HiloMatriz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //this.destroy();
    }
    
    public void operacionElegida(int numero) throws IOException{
        switch(numero){
            case 1:
                if(matrizFila(this.m)){
                    System.out.println("La matriz es matriz fila.");
                }else{
                    System.out.println("La matriz no es matriz fila");
                }
                break;
            case 2:
                if(matrizColumna(n)){
                    System.out.println("La matriz es matriz columna.");
                }else{
                    System.out.println("La matriz no es matriz columna");
                }
                break;
            case 3:
                if(matrizRectangular(m,n)){
                    System.out.println("La matriz no es matriz cuadrada.");
                    System.out.println("La matriz es matriz rectangular.");
                }else{
                    System.out.println("La matriz es matriz cuadrada.");
                    System.out.println("La matriz no es matriz rectangular");
                }
                break;
            case 4:
                if(matrizNula(nombreArchivo,m,n)){
                    System.out.println("La matriz es matriz nula.");
                }else{
                    System.out.println("La matriz no es matriz nula");
                }
                break;
            case 5:
                if(matrizTriangularSuperior(nombreArchivo,m,n)){
                    System.out.println("La matriz es matriz triangular superior");
                }else{
                    System.out.println("La matriz no es matriz triangular superior");
                }
                break;
            case 6:
                if(matrizTriangularInferior(nombreArchivo,m,n)){
                    System.out.println("La matriz es matriz triangular inferior");
                }else{
                    System.out.println("La matriz no es matriz triangular inferior");
                }
                break;
            case 7:
                if(matrizDiagonal(nombreArchivo,m,n)){
                    System.out.println("La matriz es matriz diagonal");
                }else{
                    System.out.println("La matriz no es matriz diagonal");
                }
                break;
            case 8:
                if(matrizEscalar(nombreArchivo,m,n)){
                    System.out.println("La matriz es matriz escalar");
                }else{
                    System.out.println("La matriz no es matriz escalar");
                }
            case 9:
                if(matrizIdentidad(nombreArchivo,m,n)){
                    System.out.println("La matriz es matriz identidad");
                }else{
                    System.out.println("La matriz no es matriz identidad");
                }
        }

        
    }
    
    /*
     * Este metodo sirve para ver si una matriz de un txt es matriz fila
     * Recibe el valor de m
     */
    public boolean matrizFila(int pM1){
        return (pM1 == 1);
    }
    
    /*
     * Este metodo sirve para ver si una matriz de un txt es matriz columna
     * Recibe el valor de n
     */
    public boolean matrizColumna(int pN1){
        return (pN1 == 1);
    }
    
    /*
     * Este metodo sirve para ver si una matriz de un txt es matriz rectangular
     * Recibe el valor de m y n
     */
    public boolean matrizRectangular(int pM1,int pN1){
        return (pN1 != pM1);
    }
    
    /*
     * Este metodo sirve para ver si una matriz de un txt es matriz cuadrada
     * Recibe el valor de m y n
     */
    public boolean matrizCuadrada(int pM1,int pN1){
        return (pN1 == pM1);
    }
    
    /*
     * Este metodo sirve para ver si una matriz de un txt es matriz nula
     * Recibe el nombre del txt, el valor de m y n
     */
    public boolean matrizNula(String matriz1,int pM1, int pN1) throws FileNotFoundException, IOException{

        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
        
        
        for(int i = 0; i < pM1; i++){
            for(int j = 0; j < pN1; j++){
                if(lineaMatriz1.charAt(pN1*i+j) != 0){ //Revisa si algun elemento de la matriz es diferente a 0
                    archivo1.close();
                    return false;
                }
            }
        }
        archivo1.close();
        return true;
    }
    
    /*
     * Este metodo sirve para ver si una matriz de un txt es matriz diagonal
     * Recibe el nombre del txt y el valor de m y n de la matriz
     */
    public boolean matrizDiagonal(String matriz1,int pM1, int pN1) throws FileNotFoundException, IOException{
        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
        int contador = 0;
        
        for(int i = 0; i < pM1; i++){
            for(int j = 0; j < pN1; j++){
                if(contador == j){ //Revisa si es elemento de la diagonal
                    if(lineaMatriz1.charAt(pN1*i+j) == 0){ //Revisa si el elemento de la diagonal no es 0
                        archivo1.close();
                        return false;
                    }
                }else{
                    if(lineaMatriz1.charAt(pN1*i+j) != 0){ //Revisa que si los demas valores de la matriz no son 0
                        archivo1.close();
                        return false;
                    }
                
                }
            }
            contador++;
        }
        archivo1.close();
        return true;
    }
    
    /*
     * Este metodo sirve para ver si una matriz de un txt es matriz escalar
     * Recibe el nombre del txt y el valor de m y n de la matriz
     */
    public boolean matrizEscalar(String matriz1,int pM1, int pN1) throws FileNotFoundException, IOException{
        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
        int contador = 0;
        int numeroEscalar = lineaMatriz1.charAt(pN1*0+0);
        
        for(int i = 0; i < pM1; i++){
            for(int j = 0; j < pN1; j++){
                if(contador == j){ //Revisa si es elemento de la diagonal
                    if(lineaMatriz1.charAt(pN1*i+j) != numeroEscalar){ //Revisa si el elemento de la diagonal no son iguales
                        archivo1.close();
                        return false;
                    }
                }else{
                    if(lineaMatriz1.charAt(pN1*i+j) != 0){ //Revisa que si los demas valores de la matriz no son 0
                        archivo1.close();
                        return false;
                    }
                
                }
            }
            contador++;
        }
        archivo1.close();
        return true;
    }
    /*
     * Este metodo sirve para ver si una matriz en un txt es identidad o no
     * Recibe el nombre del archivo y el valor de m y n
     */
    public boolean matrizIdentidad(String matriz1,int pM1, int pN1) throws FileNotFoundException, IOException{
        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
        int contador = 0;
        
        for(int i = 0; i < pM1; i++){
            for(int j = 0; j < pN1; j++){
                if(contador == j){  //Revisa si es un elemento de la diagonal
                    if(lineaMatriz1.charAt(pN1*i+j) != 1){ //Revisa si la posicion en la diagonal es igual a 1
                        archivo1.close();
                        return false;
                    }
                }else{
                    if(lineaMatriz1.charAt(pN1*i+j) != 0){ //Revisa que si los demas valores de la matriz no son 0
                        archivo1.close();
                        return false;
                    }
                }
            }
            contador++;
        }
        archivo1.close();
        return true;
    }
    
    /*
     * Este metodo sirve para ver si una matriz en un txt es matriz triangular superior
     * Recibe el nombre del archivo y el valor de m y n
     */
    public boolean matrizTriangularSuperior(String matriz1,int pM1, int pN1) throws FileNotFoundException, IOException{
        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
        int contador = 0;
        
        for(int i = 0; i < pM1; i++){
            for(int j = 0; j < pN1; j++){
                if(j >= contador){   //Revisa si es un elemento de la parte triangular superior de la matriz
                    if(lineaMatriz1.charAt(pN1*i+j) == 0){ //Revisa si la posicion en la matriz es igual a 0 
                        archivo1.close();
                        return false;
                    }
                }else{
                    if(lineaMatriz1.charAt(pN1*i+j) != 0){ //Revisa que si los demas valores de la matriz no son 0
                        archivo1.close();
                        return false;
                    }
                }
            }
            contador++;
        }
        archivo1.close();
        return true;
    }
    
    /*
     * Este metodo sirve para ver si una matriz en un txt es matriz triangular inferior
     * Recibe el nombre del archivo y el valor de m y n
     */
    public boolean matrizTriangularInferior(String matriz1,int pM1, int pN1) throws FileNotFoundException, IOException{
        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
        int contador = 0;
        
        for(int i = 0; i < pM1; i++){
            for(int j = 0; j < pN1; j++){
                if(j <= contador){  //Revisa si es un elemento de la parte triangular inferior de la matriz
                    if(lineaMatriz1.charAt(pN1*i+j) == 0){ //Revisa si la posicion en la parte superior es igual a 0
                        archivo1.close();
                        return false;
                    }
                }else{
                    if(lineaMatriz1.charAt(pN1*i+j) != 0){ //Revisa que si los demas valores de la matriz no son 0
                        archivo1.close();
                        return false;
                    }
                }
            }
            contador++;
        }
        archivo1.close();
        return true;
    }
}
