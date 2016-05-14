
package proyectoia.front;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author root
 */


public class Mundo extends JPanel implements ActionListener{
    
    //Nos sirve para almacenar a los objetos creados
    public static Map hashMap = new HashMap();

    public Mundo(){
        
        this.setSize(500, 0);        
        this.setVisible(true);
        this.setBorder(BorderFactory.createLineBorder( Color.BLACK ));
        this.setLayout(new MigLayout(""));
        
    }

    public void CrearMundo(String index,Icon icon,int[][] mundo){        
               
        //Creo el mundo en base a una arreglo de n*n de forma dinámica
        //con sus respctivas propiedades
        for(int i=0;i< mundo.length;i++){
            for(int j=0; j<mundo.length;j++){
                //instancia nueva a componente
                ComponenteItem casilla = new ComponenteItem(index,icon);
                 
                if(j == mundo.length){
                    this.add(casilla, "wrap");//se añade al jpanel
                }else{
                    this.add(casilla);//se añade al jpanel
                }
                
                this.validate();
                //se añade al MAP
                this.hashMap.put(index, casilla);
            }
        }
        
        
    }   
    
    public void actionPerformed(ActionEvent e) {
        //se obtiene el comando ejecutado
        String comando = e.getActionCommand();
        //se recorre el MAP
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            //se obtiene el KEY -> identificador unico
            String itm = entry.getKey().toString();
            //si comando de componente es igual a comando pulsado
            if( itm.equals(comando))
            {
                //se recupera el contenido del JTextfield
                //String name = ((jpComponente) entry.getValue()).txtName.getText();
                //mostramos resultado
               // JOptionPane.showMessageDialog(null, "Se presiono boton " + itm + " \n Hola " /*+ name*/);
            }
        }
    }
    
}
