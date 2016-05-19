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

/**
 *
 * @author root
 */
public class FrontController {
    
    PreferentePorAmplitud pAmplitud;
    Profundidad profundidad;

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
    
    
    
    
}
