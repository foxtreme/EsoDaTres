/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoia.data;

/**
 *
 * @author NSC-Computers
 * 
 */
import java.util.Timer;
import java.util.TimerTask;


public class Tiempo  {

    private Timer timer = new Timer(); 
    //private double segundos=0;
    private double milisec=0.0;

    //Clase interna que funciona como contador
    class Contador extends TimerTask {
        @Override
        public void run() {
            
            milisec = milisec + 0.1;
            //System.out.println("tiempo: "+milisec);
        }
    }
    //Crea un timer, inicia segundos a 0 y comienza a contar
    public void Contar()
    {
        this.milisec=0.0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new Contador(), 0, 100);
    }
    //Detiene el contador
    public void cancelar() {
        timer.cancel();
    }
    //Metodo que retorna los segundos transcurridos
    
    public double getSegundos(){
        return this.milisec;
    }
    
}
