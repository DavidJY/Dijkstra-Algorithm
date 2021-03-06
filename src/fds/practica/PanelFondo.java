package fds.practica;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * Esta clase permite poner una imagen de fondo en un nuevo panel de forma
 * que los demás elementos de la interfaz puedan colocarse sobre este panel.
 * <p>
 * Técnica utilizada: Dijkstra modificado para ser no dirigido.
 * 
 * @author      David Julián Yela
 * @version     1.0
 */

public class PanelFondo extends javax.swing.JPanel {

    /**
     * Creates new form PanelFondo
     */
    public PanelFondo() {
        initComponents();
        this.setSize(640, 480);
        
    }
    
    @Override
    public void paintComponent (Graphics g) {
        Dimension tamano = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass()
                .getResource("/fds/practica/images/FondoApp2.jpg"));
        g.drawImage(imagenFondo.getImage(), 0, 0, tamano.width, tamano.height, null);
        //setOpaque(false);
        setOpaque(false);
        super.paintComponents(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
