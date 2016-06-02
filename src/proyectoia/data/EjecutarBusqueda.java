/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.data;

import java.awt.Point;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import proyectoia.front.Front;

/**
 *
 * @author root
 */
public class EjecutarBusqueda extends Thread {

    List<Node> path;
    int[][] originalMap;
    Tiempo time;

    public EjecutarBusqueda(List<Node> path, int[][] originalMap) {
        this.path = path;
        this.originalMap = originalMap;
    }

    @Deprecated
    @Override
    public void run() {
        try {

            for (int i = 0; i < path.size() - 1; i++) {
                Point pos = Front.fc.getEntorno().findRobot(path.get(i).getState());
                Point pos2 = Front.fc.getEntorno().findRobot(path.get(i + 1).getState());
                String index1 = (int) pos.getX() + "" + (int) pos.getY();
                String index2 = (int) pos2.getX() + "" + (int) pos2.getY();

                ImageIcon robot = new javax.swing.ImageIcon(getClass().getResource("../images/2.png"));
                JLabel mb = new JLabel(robot);
                ImageIcon original;
                if (originalMap[(int) pos.getX()][(int) pos.getY()] == 3 || originalMap[(int) pos.getX()][(int) pos.getY()] == 6 || originalMap[(int) pos.getX()][(int) pos.getY()] == 2) {
                    original = new javax.swing.ImageIcon(getClass().getResource("../images/0.png"));
                } else {
                    original = new javax.swing.ImageIcon(getClass().getResource("../images/" + originalMap[(int) pos.getX()][(int) pos.getY()] + ".png"));
                }

                JLabel ol = new JLabel(original);

                mb.setSize(32, 32);
                mb.setVisible(true);
                mb.setToolTipText(index2);

                ol.setSize(32, 32);
                ol.setVisible(true);
                ol.setToolTipText(index1);

                Front.miMundo.replace(index1, ol);
                Front.miMundo.replace(index2, mb);

                Front.setMundoPanel(Front.miMundo);
                try{
                Thread.sleep(500);
                }catch(InterruptedException ie){
                    
                }
                
                Front.panelMundo.repaint();
            }

        } catch (Exception ex) {
            Logger.getLogger(EjecutarBusqueda.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
