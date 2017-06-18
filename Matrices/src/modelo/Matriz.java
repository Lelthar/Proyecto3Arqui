/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author gerald
 */
public class Matriz {
    //public int[][] matrizNumeros;
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
        guardarTxt();
    }

    public Matriz() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void generarMatriz(){
        if(tipo != null){
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    //matrizNumeros[i][j] = generarNumeroRandom(tipo);
                }
            }
        }
    }   
    public int generarNumeroRandom(String tipo){
        if(null != tipo)switch (tipo) {
            case "P":{
                int numeroAleatorio = (int) (Math.random()*(68) + 33);
                return numeroAleatorio;
                }
            case "N":{
                int numeroAleatorio = (int) (Math.random()*(100+1)-100);
                return numeroAleatorio;
                }
            default:{
                int tipoNumero = (int) (Math.random()*(2));
                if(tipoNumero == 0){
                    int numeroAleatorio = (int) (Math.random()*(68) + 33);
                    return numeroAleatorio;
      
                }else{
                    int numeroAleatorio = (int) (Math.random()*(100+1)-100);
                    return numeroAleatorio;
                }
                
                }
        }else
            return 0;
    }
    /*
     * Este metodo sirve para generar un txt con valores aleatorios positivos, negativos o mixtos, dependiendo del tipo
     */
    public void guardarTxt() throws IOException{ 
        String ruta = nombreArchivo+".txt"; 
        File archivo = new File(ruta); //Crea el archivo con el nombre que le pusieron a la matriz
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo)); 
        bw.write("Valor de M: "+m+", Valor de N: "+n+"\n"); //Añade el tamaño de la matriz en la primera linea
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                bw.write(generarNumeroRandom(tipo)); //Agrega al txt en una sola linea todos los valores de la matriz
                
            }
        }

        bw.close();
    }
    /*
     * Este metodo sirve para convertir un char a negativo o positivo dependiendo del tipo de matriz que le dieron 
     *
     */
    public int conversorValores(int numero,String tipo){ //Pide el numero y el tipo que puede ser positivo, negativo o mixto
        if(tipo == "P"){
            return numero;
        }else if(tipo == "N"){  
            return (65536-numero)*-1;  //Lo que hace es restar a 2 bytes el numero que se tiene para ver el valor y luego se multiplica por -1 para hacerlo negativo 
        }else{
            if(numero > 32768){ 
                return (65536-numero)*-1;
            }else{
                return numero;
            }
        }

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
    /*
     *
     * Este metodo sirve para sumar 2 matrices, recibe por parametros el nombre del txt donde están las 2 matrices, 
     * el largo y ancho de la matriz y el nombre del archivo de resultado
     */
    public void calcularSumaMatrices(String matriz1, String matriz2,int pM1, int pN1,String pNombreArchivo,int pN2) throws IOException{
        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
         
        FileReader file2 = new FileReader(matriz2+".txt"); //Abre el archivo de la segunda matriz
        BufferedReader archivo2 = new BufferedReader(file2);
        String lineaMatriz2 = archivo2.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz2 = archivo2.readLine(); //Carga en un string la linea que contiene la matriz entera
        
        this.m = pM1;
        this.n = pN1;
        this.nombreArchivo = pNombreArchivo;
        int valor;
        
        String ruta = pNombreArchivo+".txt"; 
        File archivo = new File(ruta); //Crea el txt del resultado de la operacion
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("Valor de M: "+pM1+", Valor de N: "+pN1+"\n"); //Le agrega la linea con los datos de la matriz
        
        for(int i = 0; i < pM1; i++){
            for(int j = 0; j < pN1; j++){
                valor = (lineaMatriz1.charAt(pN1*i+j)+lineaMatriz2.charAt(pN2*i+j)); //Hace la suma de los elementos de la posicion x y y de las matrices
                if(valor<=32 && valor>=0){ //Revisa si es menor o igual a 32 y mayor o igual a 0 para que no se introduzcan simbolos del sistema 
                    valor = 33;  //y agrega por defecto 33
                    bw.write(valor);
                }else{
                    bw.write(valor);
                }
                
            }
            
        }
        System.out.println("Se completó la suma");
        archivo1.close();
        archivo2.close();
        bw.close();
    }
    public void calcularMatrizPorEscalar(String matriz1,int pM1, int pN1, int escalar,String pNombre) throws IOException{
        this.m = pM1;
        this.n = pN1;
        this.nombreArchivo = pNombre;
        
        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
        
        String ruta = pNombre+".txt";
        File archivo = new File(ruta);
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("Valor de M: "+pM1+", Valor de N: "+pN1+"\n");
        
        for(int i = 0; i < pM1; i++){
            for(int j = 0; j < pN1; j++){
                bw.write((lineaMatriz1.charAt(pN1*i+j)*escalar));
            }
        }
        
        bw.close();
        archivo1.close();
        
    }
    public void calcularTranspuestaMatriz(String matriz1,int pM1, int pN1,String pNombre) throws FileNotFoundException, IOException{
        this.m = pN1;
        this.n = pM1;
        this.nombreArchivo = pNombre;
        
        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
        
        String ruta = pNombre+".txt";
        File archivo = new File(ruta);
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("Valor de M: "+pN1+", Valor de N: "+pM1+"\n");
        
        for(int j = 0; j < pN1; j++){
            for(int i = 0; i < pM1; i++){
                bw.write(lineaMatriz1.charAt(pN1*i+j));
            }
        }
        
        bw.close();
        archivo1.close();
    }
}
