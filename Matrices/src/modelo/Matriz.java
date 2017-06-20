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
    
    /*
     * Este metodo sirve para hacer una multiplicacion de una matriz por un escalar y guardar el resultado en un txt
     * Recibe el nombre del txt de la matriz, tambien el valor de m y n, tambien el escalar y el nombre del archivo para el resultado
     */
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
    
    /*
     * Este metodo sirve para calcular la transpuesta de una matriz y guardar el resultado en un txt
     * Recibe el nombre del txt de la matriz 1 y el valor de m y n, y el nombre del archivo donde se va a guardar el resulado
     */
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
   
    /*
     * Este metodo sirve para hacer una multiplicacion de matrices y guardar el resultado en un txt
     * Recibe el nombre del txt de la matriz 1 y 2, tambien el valor de m y n de las 2 y el nombre del archivo para el resultado
     */
    public void multiplicacionMatrices(String matriz1, String matriz2,int pM1, int pN1,String pNombreArchivo,int pN2) throws FileNotFoundException, IOException{
        FileReader file1 = new FileReader(matriz1+".txt"); //Abre el archivo de la primera matriz
        BufferedReader archivo1 = new BufferedReader(file1);
        String lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz1 = archivo1.readLine(); //Carga en un string la linea que contiene la matriz entera
         
        FileReader file2 = new FileReader(matriz2+".txt"); //Abre el archivo de la segunda matriz
        BufferedReader archivo2 = new BufferedReader(file2);
        String lineaMatriz2 = archivo2.readLine(); //Carga en un string la linea que contiene los datos de la matriz
        lineaMatriz2 = archivo2.readLine(); //Carga en un string la linea que contiene la matriz entera
        
        this.m = pM1;
        this.n = pN2;
        this.nombreArchivo = pNombreArchivo;
        int valor;
        
        String ruta = pNombreArchivo+".txt"; 
        File archivo = new File(ruta); //Crea el txt del resultado de la operacion
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("Valor de M: "+pM1+", Valor de N: "+pN2+"\n"); //Le agrega la linea con los datos de la matriz
        //System.out.println("entro");
        System.out.println("Valor de M: "+pM1+", Valor de N: "+pN2);
        for(int i = 0; i < pM1; i++){
            for(int j = 0; j < pN2; j++){
                valor = 0;
                for(int k = 0; k < pN1; k++){
                    valor += lineaMatriz1.charAt(pN1*i+k)*lineaMatriz2.charAt(pN2*k+j); //multiplica el elemento en la posicion x y y de las 2 matrices
                }
                if(valor <= 32 && valor >= 0 || valor >= 65536){
                    bw.write(33);
                }else{
                    bw.write(valor);
                }
                System.out.print(valor+" ");
            }
            System.out.println("");
        }
        //System.out.println("salgo");
        bw.close();
        archivo1.close();
        archivo2.close();
    }
    /*
     * Este metodo sirve para ver los tipos de matriz que es una matriz en un txt
     * Recibe el nombre del archivo, el valor de m y n
     */
    public void tiposMatriz(String matriz1,int pM1, int pN1) throws FileNotFoundException, IOException{
        if(matrizFila(pM1)){
            System.out.println("La matriz es matriz fila.");
        }else{
            System.out.println("La matriz no es matriz fila");
        }
        if(matrizColumna(pN1)){
            System.out.println("La matriz es matriz columna.");
        }else{
            System.out.println("La matriz no es matriz columna");
        }
        if(matrizRectangular(pM1,pN1)){
            System.out.println("La matriz no es matriz cuadrada.");
            System.out.println("La matriz es matriz rectangular.");
        }else{
            System.out.println("La matriz es matriz cuadrada.");
            System.out.println("La matriz no es matriz rectangular");
        }
        if(matrizNula(matriz1,pM1,pN1)){
            System.out.println("La matriz es matriz nula.");
        }else{
            System.out.println("La matriz no es matriz nula");
        }
        if(matrizTriangularSuperior(matriz1,pM1,pN1)){
            System.out.println("La matriz es matriz triangular superior");
        }else{
            System.out.println("La matriz no es matriz triangular superior");
        }
        if(matrizTriangularInferior(matriz1,pM1,pN1)){
            System.out.println("La matriz es matriz triangular inferior");
        }else{
            System.out.println("La matriz no es matriz triangular inferior");
        }
        if(matrizDiagonal(matriz1,pM1,pN1)){
            System.out.println("La matriz es matriz diagonal");
        }else{
            System.out.println("La matriz no es matriz diagonal");
        }
        if(matrizEscalar(matriz1,pM1,pN1)){
            System.out.println("La matriz es matriz escalar");
        }else{
            System.out.println("La matriz no es matriz escalar");
        }
        if(matrizIdentidad(matriz1,pM1,pN1)){
            System.out.println("La matriz es matriz identidad");
        }else{
            System.out.println("La matriz no es matriz identidad");
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
