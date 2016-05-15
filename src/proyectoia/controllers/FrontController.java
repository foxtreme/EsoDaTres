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

/**
 *
 * @author root
 */
public class FrontController {

    public FrontController() {
    }
    
    public int[][] getMundoMatrix(File mundoFile){
        return new Entorno().getMundo(mundoFile);
    }
    
    public Map getMundo(int[][] mundo){
        return new Core().getMundo(mundo);
    }
    
    
}
