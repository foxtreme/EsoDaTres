/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.controllers;

import java.io.File;
import proyectoia.data.Entorno;

/**
 *
 * @author root
 */
public class FrontController {

    public FrontController() {
    }
    
    public int[][] crearMundo(File mundoFile){
        return new Entorno().getMundo(mundoFile);
    }
}
