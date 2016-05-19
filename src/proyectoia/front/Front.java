/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.front;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.io.File;
import static java.lang.Math.sqrt;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import proyectoia.controllers.FrontController;

/**
 *
 * @author root
 */
public class Front extends javax.swing.JFrame {

    /**
     * Creates new form Front
     */
    FrontController fc;
    public boolean buscando;

    //@SuppressWarnings("unchecked")
    public Front() {
        initComponents();

        //instancio controladores
        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("../images/banner.png")));
        fc = new FrontController();//instancio el controlador
        buscando=true;//variable manipuladora del Boton Ejecutar - ejecutarJButton

        //asigno un filtro de busquda de archivos al jFileChooser
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt");
        cargarRutaMundoJFC.setFileFilter(filtro);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cargarMundoJDialog = new javax.swing.JDialog();
        cargarRutaMundoJFC = new javax.swing.JFileChooser();
        panelLogo = new javax.swing.JPanel();
        labelLogo = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();
        jTextFieldRutaMundo = new javax.swing.JTextField();
        cargarMundoJButton = new javax.swing.JButton();
        editarMundoJButton = new javax.swing.JButton();
        tipoBusquedaJLabel = new javax.swing.JLabel();
        busquedaJComboBox = new javax.swing.JComboBox<>();
        algoritmosJComboBox = new javax.swing.JComboBox<>();
        ejecutarJButton = new javax.swing.JButton();
        scrollMundo = new javax.swing.JScrollPane();
        panelMundo = new javax.swing.JPanel();
        nodosExpandidos = new javax.swing.JTextField();
        profundidad = new javax.swing.JTextField();
        tiempoComputo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        cargarRutaMundoJFC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarRutaMundoJFCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cargarMundoJDialogLayout = new javax.swing.GroupLayout(cargarMundoJDialog.getContentPane());
        cargarMundoJDialog.getContentPane().setLayout(cargarMundoJDialogLayout);
        cargarMundoJDialogLayout.setHorizontalGroup(
            cargarMundoJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cargarRutaMundoJFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        cargarMundoJDialogLayout.setVerticalGroup(
            cargarMundoJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cargarRutaMundoJFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UNIVALLE Smart Robot");
        setBackground(new java.awt.Color(102, 102, 102));
        setMinimumSize(new java.awt.Dimension(620, 600));
        setPreferredSize(new java.awt.Dimension(620, 460));

        panelLogo.setPreferredSize(new java.awt.Dimension(0, 54));

        labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelLogoLayout = new javax.swing.GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );

        panelContent.setBackground(new java.awt.Color(102, 102, 102));
        panelContent.setMinimumSize(new java.awt.Dimension(600, 346));

        jTextFieldRutaMundo.setEditable(false);

        cargarMundoJButton.setLabel("Cargar Mundo");
        cargarMundoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarMundoJButtonActionPerformed(evt);
            }
        });

        editarMundoJButton.setText("Editar Mundo");
        editarMundoJButton.setEnabled(false);
        editarMundoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarMundoJButtonActionPerformed(evt);
            }
        });

        tipoBusquedaJLabel.setText("Tipo de Busqueda: ");

        busquedaJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Informada", "Informada" }));
        busquedaJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busquedaJComboBoxActionPerformed(evt);
            }
        });

        algoritmosJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Amplitud", "Costo Uniforme", "Profundidad" }));

        ejecutarJButton.setText("Buscar");
        ejecutarJButton.setEnabled(false);
        ejecutarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMundoLayout = new javax.swing.GroupLayout(panelMundo);
        panelMundo.setLayout(panelMundoLayout);
        panelMundoLayout.setHorizontalGroup(
            panelMundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 416, Short.MAX_VALUE)
        );
        panelMundoLayout.setVerticalGroup(
            panelMundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 299, Short.MAX_VALUE)
        );

        scrollMundo.setViewportView(panelMundo);

        nodosExpandidos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nodosExpandidos.setEnabled(false);

        profundidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        profundidad.setEnabled(false);

        tiempoComputo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tiempoComputo.setEnabled(false);

        jLabel1.setText("# de Nodos Expandidos:");

        jLabel2.setText("Profundidad del Arbol:");

        jLabel3.setText("Tiempo de Cómputo (ms):");

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addComponent(scrollMundo, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2)
                            .addGroup(panelContentLayout.createSequentialGroup()
                                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelContentLayout.createSequentialGroup()
                                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelContentLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(5, 5, 5)))
                                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tiempoComputo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                    .addComponent(nodosExpandidos)
                                    .addComponent(profundidad)))))
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldRutaMundo)
                            .addGroup(panelContentLayout.createSequentialGroup()
                                .addComponent(tipoBusquedaJLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(busquedaJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(algoritmosJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ejecutarJButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cargarMundoJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editarMundoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRutaMundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargarMundoJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editarMundoJButton)
                    .addComponent(tipoBusquedaJLabel)
                    .addComponent(busquedaJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(algoritmosJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ejecutarJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollMundo)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nodosExpandidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(profundidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tiempoComputo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
@Deprecated
    private void cargarRutaMundoJFCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarRutaMundoJFCActionPerformed
    cargarRutaMundoJFC = (JFileChooser) evt.getSource();
    String comando = evt.getActionCommand();
    Map miMundo = new HashMap();

    if (comando.equals(JFileChooser.APPROVE_SELECTION)) {

        File archivoSeleccionado = cargarRutaMundoJFC.getSelectedFile();

        jTextFieldRutaMundo.setText(archivoSeleccionado.getAbsolutePath());
        jTextFieldRutaMundo.setToolTipText(archivoSeleccionado.getAbsolutePath());
        editarMundoJButton.setEnabled(true);// habilito boton abrir Script

        //cargo el mundo inicial
        //creo el mundo y obtengo un Map con todos los label con el icono
        miMundo = fc.getMundo(fc.getMundoMatrix(archivoSeleccionado));
        
        //cargo el mundo en el panel a partir del hash con los iconos indexados
        setMundoPanel(miMundo);
        
        //habilito el boton ejecutar
        ejecutarJButton.setEnabled(true);
        
        //oculto el JFileChooser
        cargarRutaMundoJFC.hide();
        cargarMundoJDialog.hide();

    } else if (comando.equals(JFileChooser.CANCEL_SELECTION)) {
        cargarRutaMundoJFC.hide();
        cargarMundoJDialog.hide();
    }
    
        
    }//GEN-LAST:event_cargarRutaMundoJFCActionPerformed

    private void cargarMundoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarMundoJButtonActionPerformed
        cargarMundoJDialog.setSize(620, 360);
        cargarMundoJDialog.setLocationRelativeTo(null);
        cargarMundoJDialog.setVisible(true);
        cargarRutaMundoJFC.setVisible(true);


    }//GEN-LAST:event_cargarMundoJButtonActionPerformed

    private void editarMundoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarMundoJButtonActionPerformed
        try {
            if (jTextFieldRutaMundo.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No se ha especificado la ruta", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Desktop.getDesktop().open(new File(jTextFieldRutaMundo.getText()));
            }
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(null, "Error al Abrir el archivo del mundo", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_editarMundoJButtonActionPerformed

    private void busquedaJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busquedaJComboBoxActionPerformed
        String index = busquedaJComboBox.getSelectedItem().toString();
        
        if(index.equals("No Informada")){
            algoritmosJComboBox.removeAllItems();
            algoritmosJComboBox.addItem("Amplitud");
            algoritmosJComboBox.addItem("Costo Uniforme");
            algoritmosJComboBox.addItem("Profundidad");            
        }else if(index.equals("Informada")){
            algoritmosJComboBox.removeAllItems();
            algoritmosJComboBox.addItem("Avara");
            algoritmosJComboBox.addItem("A*");
        }
        
    }//GEN-LAST:event_busquedaJComboBoxActionPerformed

    private void ejecutarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarJButtonActionPerformed
        if(buscando){
            switch(algoritmosJComboBox.getSelectedItem().toString()){
                case "Amplitud":
                    
                        fc.Amplitud(new File(jTextFieldRutaMundo.getText())).breadthFirst();
                        
                        nodosExpandidos.setText(""+fc.getpAmplitud().getExplored().size());//nodos expandidos
                        profundidad.setText(""+fc.getpAmplitud().getSolution().getDepth());//profundidad
                        tiempoComputo.setText(""+fc.getpAmplitud().getTotalTime());//tiempo en milisegundos
                        
                    break;
                case "Costo Uniforme":
                        
                    break;
                case "Profundidad":
                        fc.Profundidad(new File(jTextFieldRutaMundo.getText())).depthFirst();
                        
                        nodosExpandidos.setText(""+fc.getProfundidad().getExplored().size());//nodos expandidos
                        profundidad.setText(""+fc.getProfundidad().getSolution().getDepth());//profundidad
                        tiempoComputo.setText(""+fc.getProfundidad().getTotalTime());//tiempo en milisegundos
                    break;
                case "Avara":
                    break;
                case "A*":
                    break;
                default: 
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun algoritmo", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            buscando = false;
            ejecutarJButton.setText("Detener");
        }else if(!buscando){
            //detener la ejecucion 
            buscando = true;
            ejecutarJButton.setText("Buscar");
        }
    }//GEN-LAST:event_ejecutarJButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Front.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Front().setVisible(true);
            }
        });
    }
    
    /***
     * carga el mundo en el panel
     * @param miMundo 
     */
    public void setMundoPanel(Map miMundo){
        int size = (int) sqrt(miMundo.size());
    panelMundo.setLayout(new GridLayout(size, size));

    //remover contenido
    if (miMundo != null) {
        panelMundo.removeAll();
    }
    //Cargo el mundo en panelMundo
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            JLabel lb = (JLabel) miMundo.get(i + "" + j);
            lb.setSize(32, 32);
            lb.setVisible(true);
            panelMundo.add(lb);
        }
    }
   
    
    panelMundo.setVisible(true);
    panelMundo.repaint();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> algoritmosJComboBox;
    private javax.swing.JComboBox<String> busquedaJComboBox;
    private javax.swing.JButton cargarMundoJButton;
    private javax.swing.JDialog cargarMundoJDialog;
    private javax.swing.JFileChooser cargarRutaMundoJFC;
    private javax.swing.JButton editarMundoJButton;
    private javax.swing.JButton ejecutarJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldRutaMundo;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JTextField nodosExpandidos;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JPanel panelMundo;
    private javax.swing.JTextField profundidad;
    private javax.swing.JScrollPane scrollMundo;
    private javax.swing.JTextField tiempoComputo;
    private javax.swing.JLabel tipoBusquedaJLabel;
    // End of variables declaration//GEN-END:variables
}
