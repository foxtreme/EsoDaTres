/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.controllers;

import java.io.File;
import java.util.Map;
import proyectoia.data.Core;
import proyectoia.data.Entorno;
import proyectoia.noinformada.PreferentePorAmplitud;
import proyectoia.noinformada.Profundidad;
import proyectoia.data.EjecutarBusqueda;
import proyectoia.informada.Aestrella;
import proyectoia.informada.Avara;
import proyectoia.noinformada.CostoUniforme;

/**
 *
 * @author root
 */
public class FrontController {
    
    PreferentePorAmplitud pAmplitud;
    Profundidad profundidad;
    CostoUniforme costo;
    Avara avara;
    Aestrella aStar;

    public FrontController() {
    }
    
    public int[][] getMundoMatrix(File mundoFile){
        return new Entorno().getMundo(mundoFile);
    }
    
    public Map getMundo(int[][] mundo){
        return new Core().getMundo(mundo);
    }
    
    public PreferentePorAmplitud Amplitud(File fileMundo){
        pAmplitud = new PreferentePorAmplitud(fileMundo);
        return pAmplitud;
    }
    
    public Profundidad Profundidad(File fileMundo){
        profundidad =   new Profundidad(fileMundo);
        return profundidad;
    }
    public CostoUniforme CostoUniforme(File fileMundo){
        costo = new CostoUniforme(fileMundo);
        return costo;
    }
    
    public Avara Avara(File fileMundo){
        avara = new Avara(fileMundo);
        return avara;
    }
    
    public Aestrella AStar(File fileMundo){
        aStar = new Aestrella(fileMundo);
        return aStar;
    }
    public Entorno getEntorno(){
        return new Entorno();
    }
       
    
    //getter and setters

    public PreferentePorAmplitud getpAmplitud() {
        return pAmplitud;
    }

    public void setpAmplitud(PreferentePorAmplitud pAmplitud) {
        this.pAmplitud = pAmplitud;
    }

    public Profundidad getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Profundidad profundidad) {
        this.profundidad = profundidad;
    }

    public CostoUniforme getCosto() {
        return costo;
    }

    public void setCosto(CostoUniforme costo) {
        this.costo = costo;
    }

    public Avara getAvara() {
        return avara;
    }

    public void setAvara(Avara avara) {
        this.avara = avara;
    }

    public Aestrella getaStar() {
        return aStar;
    }

    public void setaStar(Aestrella aStar) {
        this.aStar = aStar;
    }
    
    
    
    
}
