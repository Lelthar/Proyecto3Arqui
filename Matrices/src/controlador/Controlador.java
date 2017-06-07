/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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
    public Controlador() {
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
    

 
    
    
}
