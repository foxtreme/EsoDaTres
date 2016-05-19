/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.data;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author root
 */
public class Core {

    //Nos sirve para almacenar a los objetos creados
    public static Map hashMap = new HashMap();
    public static String index;

    public Core() {

    }
    /***
     * Retorna un Map con los items indexados del mundo a partir de una matriz cuadrada
     * @param mundo
     * @return 
     */
    public Map getMundo(int[][] mundo) {

        //Creo el mundo en base a un arreglo de n*n de forma dinámica
        //con sus respctivas propiedades
        for (int i = 0; i < mundo.length; i++) {
            for (int j = 0; j < mundo.length; j++) {
                //instancia nueva a componente

                ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("../images/" + mundo[i][j] + ".png"));
                index = i + "" + j;

                JLabel mb = new JLabel(icon);
                mb.setSize(32, 32);
                mb.setVisible(true);
                mb.setToolTipText(index);

                //se añade al MAP
                this.hashMap.put(index, mb);
            }
        }

        return hashMap;

    }

}
