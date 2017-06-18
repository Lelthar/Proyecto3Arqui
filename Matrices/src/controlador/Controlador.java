/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import modelo.Matriz;
import vista.VentanaPrincipal;

/**
 *
 * @author gerald
 */
public class Controlador {
    VentanaPrincipal vistaVentana;
    ArrayList<Matriz> listaMatrices;
    public int numeroMatrices;
    public Controlador() {
        this.numeroMatrices = 0;
        this.vistaVentana = new VentanaPrincipal(this);
        listaMatrices = new ArrayList<>();
        this.vistaVentana.setVisible(true);
    }
    
    public void abrirVentanaAgregar(){
        vistaVentana.vistaAgregar.setVisible(true);
    }
    public void abrirVentanaListaMatrices(){
        vistaVentana.vistaListaMatrices.setVisible(true);
    }
    public void abrirVentanaListaOperaciones(){
        vistaVentana.vistaListaOperaciones.setVisible(true);
    }
    public void realizarOperacion() throws IOException{
        if(vistaVentana.txtfCantidadHilos.getText() != "" && vistaVentana.txtfOperacion.getText() != "" && vistaVentana.txtfMatriz1.getText() != "" && vistaVentana.txtfResultado.getText() != "" && !listaMatrices.isEmpty()){
            if(Integer.parseInt(vistaVentana.txtfOperacion.getText()) == 1){
                if(vistaVentana.txtfMatriz2.getText() != ""){
                    sumaMatrices(Integer.parseInt(vistaVentana.txtfMatriz1.getText()),Integer.parseInt(vistaVentana.txtfMatriz2.getText()),vistaVentana.txtfResultado.getText());
                }else{
                    //Poner mensaje de la que la matriz2 esta vacia
                }
            }else if(Integer.parseInt(vistaVentana.txtfOperacion.getText()) == 2){
                if(vistaVentana.txtfMatriz2.getText() != ""){
                   matrizPorEscalar(Integer.parseInt(vistaVentana.txtfMatriz1.getText()),Integer.parseInt(vistaVentana.txtfMatriz2.getText()),vistaVentana.txtfResultado.getText());
                }else{
                    //Poner mensaje que no tiene escalar
                }
            }else if(Integer.parseInt(vistaVentana.txtfOperacion.getText()) == 3){
                if(vistaVentana.txtfMatriz2.getText() != ""){
                    System.out.println("Aqui va el codigo de producto de matrices");
                }else{
                    System.out.println("Falta parametro de matriz 2");
                }
            }else if(Integer.parseInt(vistaVentana.txtfOperacion.getText()) == 4){
                System.out.println("Aqui va el de calcular inversa");
            }else if(Integer.parseInt(vistaVentana.txtfOperacion.getText()) == 5){
                System.out.println("Aqui va el tipo de matriz");
            }else if(Integer.parseInt(vistaVentana.txtfOperacion.getText()) == 6){
                System.out.println("Aqui va el rango de la matriz");
            }else if(Integer.parseInt(vistaVentana.txtfOperacion.getText()) == 7){
                matrizTranspuesta(Integer.parseInt(vistaVentana.txtfMatriz1.getText()),vistaVentana.txtfResultado.getText());
            }
            
        }else{
            System.out.println("Faltan parametros");
        }
        
    }
    public void agregarMatriz() throws IOException{
        if(vistaVentana.vistaAgregar.txtfNombre.getText() != "" && vistaVentana.vistaAgregar.txtfM.getText() != "" && vistaVentana.vistaAgregar.txtfN.getText() != "" && vistaVentana.vistaAgregar.txtfTipo.getText() != ""){
            Matriz nuevaMatriz = new Matriz(Integer.parseInt(vistaVentana.vistaAgregar.txtfM.getText()),Integer.parseInt(vistaVentana.vistaAgregar.txtfN.getText()),numeroMatrices,vistaVentana.vistaAgregar.txtfTipo.getText(),vistaVentana.vistaAgregar.txtfNombre.getText());
            listaMatrices.add(nuevaMatriz);
            cargarMatrizPanelAgregar(vistaVentana.vistaAgregar.txtfNombre.getText(),Integer.parseInt(vistaVentana.vistaAgregar.txtfM.getText()),Integer.parseInt(vistaVentana.vistaAgregar.txtfN.getText()),vistaVentana.vistaAgregar.txtfTipo.getText());
            numeroMatrices++;
            System.out.println(listaMatrices.size());
            //vistaVentana.vistaAgregar.txtAResultado.setText(nuevaMatriz.matrizMostrar);
        }
    }
    
    public void cargarMatrizPanelAgregar(String nombre,int m, int n, String tipo) throws FileNotFoundException, IOException{
        
        FileReader file = new FileReader(nombre+".txt");
        BufferedReader archivo = new BufferedReader(file);
        vistaVentana.vistaAgregar.txtAResultado.setText("");
        //System.out.println(archivo.readLine());
        vistaVentana.vistaAgregar.txtAResultado.append(archivo.readLine()+"\n");
        String matriz = archivo.readLine();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                vistaVentana.vistaAgregar.txtAResultado.append(conversorValores(matriz.charAt(i*n+j),tipo)+" ");
                //System.out.print(conversorValores(matriz.charAt(i*m+j),tipo)+" ");
            }
            vistaVentana.vistaAgregar.txtAResultado.append("\n");
            //System.out.println("");
        }
        
        archivo.close();
    }
    
    public void cargarMatrizPanelPrincipal(String nombre,int m, int n, String tipo) throws FileNotFoundException, IOException{
        
        FileReader file = new FileReader(nombre+".txt");
        BufferedReader archivo = new BufferedReader(file);
        vistaVentana.txtAResultado.setText("");
        //System.out.println(archivo.readLine());
        vistaVentana.txtAResultado.append(archivo.readLine()+"\n");
        String matriz = archivo.readLine();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                vistaVentana.txtAResultado.append(conversorValores(matriz.charAt(i*n+j),tipo)+" ");
                //System.out.print(conversorValores(matriz.charAt(i*m+j),tipo)+" ");
            }
            vistaVentana.txtAResultado.append("\n");
            //System.out.println("");
        }
        
        archivo.close();
    }
    
    public int conversorValores(int numero,String tipo){
        if(tipo == "P"){
            return numero;
        }else if(tipo == "N"){
            return (65536-numero)*-1;
        }else{
            if(numero > 32768){
                return (65536-numero)*-1;
            }else{
                return numero;
            }
        }

    }
    
    //-------------------------------------------------Operaciones----------------------------------------------------------
    public void sumaMatrices(int matriz1, int matriz2,String nombreArchivo) throws IOException{
        Matriz matrizNueva = new Matriz();
        matrizNueva.calcularSumaMatrices(listaMatrices.get(matriz1).getNombreArchivo(), listaMatrices.get(matriz2).getNombreArchivo(),listaMatrices.get(matriz1).getM(),listaMatrices.get(matriz1).getN(),nombreArchivo,listaMatrices.get(matriz2).getN());
        //matrizNueva.setNombreArchivo(nombreArchivo);
        matrizNueva.setTipo("M");
        matrizNueva.setNumeroMatriz(numeroMatrices);
        listaMatrices.add(matrizNueva);
        //matrizNueva.guardarTxt();
        //vistaVentana.txtAResultado.setText(matrizNueva.matrizMostrar);
        cargarMatrizPanelPrincipal(nombreArchivo,listaMatrices.get(matriz1).getM(),listaMatrices.get(matriz1).getN(),"M");
        numeroMatrices++;
        
    }
    public void matrizPorEscalar(int matriz1, int escalar,String nombreArchivo) throws IOException{
        Matriz matrizNueva = new Matriz();
        matrizNueva.calcularMatrizPorEscalar(listaMatrices.get(matriz1).getNombreArchivo(),listaMatrices.get(matriz1).getM(),listaMatrices.get(matriz1).getN(), escalar,nombreArchivo);
        matrizNueva.setTipo("M");
        matrizNueva.setNumeroMatriz(numeroMatrices);
        listaMatrices.add(matrizNueva);
        cargarMatrizPanelPrincipal(nombreArchivo,listaMatrices.get(matriz1).getM(),listaMatrices.get(matriz1).getN(),"M");
        numeroMatrices++;
        
    }
    public void matrizTranspuesta(int matriz1,String nombreArchivo) throws IOException{
        Matriz matrizNueva = new Matriz();
        matrizNueva.calcularTranspuestaMatriz(listaMatrices.get(matriz1).getNombreArchivo(),listaMatrices.get(matriz1).getM(),listaMatrices.get(matriz1).getN(),nombreArchivo);
        matrizNueva.setTipo("M");
        matrizNueva.setNumeroMatriz(numeroMatrices);
        listaMatrices.add(matrizNueva);
        cargarMatrizPanelPrincipal(nombreArchivo, listaMatrices.get(matriz1).getN(),listaMatrices.get(matriz1).getM(),"M");
        numeroMatrices++;
        
    }
    
    

 
    
    
}
