/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
            vistaVentana.vistaAgregar.txtAResultado.setText(nuevaMatriz.matrizMostrar);
        }
    }
    
    //-------------------------------------------------Operaciones----------------------------------------------------------
    public void sumaMatrices(int matriz1, int matriz2,String nombreArchivo) throws IOException{
        Matriz matrizNueva = new Matriz();
        matrizNueva.calcularSumaMatrices(listaMatrices.get(matriz1).getMatrizNumeros(), listaMatrices.get(matriz2).getMatrizNumeros());
        matrizNueva.setNombreArchivo(nombreArchivo);
        matrizNueva.setTipo("M");
        matrizNueva.setNumeroMatriz(numeroMatrices);
        listaMatrices.add(matrizNueva);
        matrizNueva.guardarTxt();
        vistaVentana.txtAResultado.setText(matrizNueva.matrizMostrar);
        numeroMatrices++;
        
    }
    public void matrizPorEscalar(int matriz1, int escalar,String nombreArchivo) throws IOException{
        Matriz matrizNueva = new Matriz();
        matrizNueva.calcularMatrizPorEscalar(listaMatrices.get(matriz1).getMatrizNumeros(), escalar);
        matrizNueva.setNombreArchivo(nombreArchivo);
        matrizNueva.setTipo("M");
        matrizNueva.setNumeroMatriz(numeroMatrices);
        listaMatrices.add(matrizNueva);
        matrizNueva.guardarTxt();
        vistaVentana.txtAResultado.setText(matrizNueva.matrizMostrar);
        numeroMatrices++;
        
    }
    public void matrizTranspuesta(int matriz1,String nombreArchivo) throws IOException{
        Matriz matrizNueva = new Matriz();
        matrizNueva.calcularTranspuestaMatriz(listaMatrices.get(matriz1).getMatrizNumeros());
        matrizNueva.setNombreArchivo(nombreArchivo);
        matrizNueva.setTipo("M");
        matrizNueva.setNumeroMatriz(numeroMatrices);
        listaMatrices.add(matrizNueva);
        matrizNueva.guardarTxt();
        vistaVentana.txtAResultado.setText(matrizNueva.matrizMostrar);
        numeroMatrices++;
        
    }
    

 
    
    
}
