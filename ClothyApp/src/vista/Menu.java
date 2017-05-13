package vista;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Articulos;
import modelo.Categorias;
import modelo.Clientes;
import modelo.Empleados;
import static vista.Login.*;



/**
 *
 * @author Quique
 */
public class Menu extends javax.swing.JFrame {    
    
    static Menu menu;    

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        this.setLocationRelativeTo(null);       
        user_logeado.setText(user_actual);
        //OCULTAMOS BOTÓN EMPLEADOS PARA LOS QUE NO SEAN ADMIN
        if (!user_actual.equalsIgnoreCase("admin")) {
            jBEmpleados.setVisible(false);
            jLEmpleados.setVisible(false);
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        user_logeado = new javax.swing.JLabel();
        jBArticulos = new javax.swing.JButton();
        jBCategorias = new javax.swing.JButton();
        jBMarcas = new javax.swing.JButton();
        jBTallas = new javax.swing.JButton();
        jBLineas_venta = new javax.swing.JButton();
        jBEmpleados = new javax.swing.JButton();
        jBClientes = new javax.swing.JButton();
        jBVentas = new javax.swing.JButton();
        jBSesion = new javax.swing.JButton();
        jLArticulos = new javax.swing.JLabel();
        jLCategorias = new javax.swing.JLabel();
        jLMarcas = new javax.swing.JLabel();
        jLTallas = new javax.swing.JLabel();
        jLLineas_venta = new javax.swing.JLabel();
        jLEmpleados = new javax.swing.JLabel();
        jLClientes = new javax.swing.JLabel();
        jLVentas = new javax.swing.JLabel();
        jBPedidos = new javax.swing.JButton();
        jLPedidos = new javax.swing.JLabel();
        jBCerrar = new javax.swing.JButton();
        fondo_menu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(725, 365));
        setMinimumSize(new java.awt.Dimension(725, 365));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(725, 365));
        setResizable(false);
        setSize(new java.awt.Dimension(725, 365));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tekton Pro", 1, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText(" @");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 0, 30, 40);

        user_logeado.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        user_logeado.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(user_logeado);
        user_logeado.setBounds(40, 0, 120, 30);

        jBArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/articulos_icon.png"))); // NOI18N
        jBArticulos.setToolTipText("");
        jBArticulos.setBorderPainted(false);
        jBArticulos.setContentAreaFilled(false);
        jBArticulos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBArticulos.setFocusPainted(false);
        jBArticulos.setFocusable(false);
        jBArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBArticulosActionPerformed(evt);
            }
        });
        getContentPane().add(jBArticulos);
        jBArticulos.setBounds(70, 80, 70, 78);

        jBCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/categorias_icon.png"))); // NOI18N
        jBCategorias.setToolTipText("");
        jBCategorias.setBorderPainted(false);
        jBCategorias.setContentAreaFilled(false);
        jBCategorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBCategorias.setFocusPainted(false);
        jBCategorias.setFocusable(false);
        jBCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCategoriasActionPerformed(evt);
            }
        });
        getContentPane().add(jBCategorias);
        jBCategorias.setBounds(210, 80, 60, 78);

        jBMarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/marcas_icon.png"))); // NOI18N
        jBMarcas.setToolTipText("");
        jBMarcas.setBorderPainted(false);
        jBMarcas.setContentAreaFilled(false);
        jBMarcas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBMarcas.setFocusPainted(false);
        jBMarcas.setFocusable(false);
        getContentPane().add(jBMarcas);
        jBMarcas.setBounds(320, 90, 90, 70);

        jBTallas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/tallas_icon.png"))); // NOI18N
        jBTallas.setToolTipText("");
        jBTallas.setBorderPainted(false);
        jBTallas.setContentAreaFilled(false);
        jBTallas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBTallas.setFocusPainted(false);
        jBTallas.setFocusable(false);
        getContentPane().add(jBTallas);
        jBTallas.setBounds(440, 90, 80, 70);

        jBLineas_venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/lineas_venta2.png"))); // NOI18N
        jBLineas_venta.setToolTipText("");
        jBLineas_venta.setBorderPainted(false);
        jBLineas_venta.setContentAreaFilled(false);
        jBLineas_venta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBLineas_venta.setFocusPainted(false);
        jBLineas_venta.setFocusable(false);
        getContentPane().add(jBLineas_venta);
        jBLineas_venta.setBounds(580, 80, 80, 80);

        jBEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/empleados_icon2.png"))); // NOI18N
        jBEmpleados.setToolTipText("");
        jBEmpleados.setBorderPainted(false);
        jBEmpleados.setContentAreaFilled(false);
        jBEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBEmpleados.setFocusPainted(false);
        jBEmpleados.setFocusable(false);
        jBEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEmpleadosActionPerformed(evt);
            }
        });
        getContentPane().add(jBEmpleados);
        jBEmpleados.setBounds(70, 210, 70, 78);

        jBClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/clientes_icon.png"))); // NOI18N
        jBClientes.setToolTipText("");
        jBClientes.setBorderPainted(false);
        jBClientes.setContentAreaFilled(false);
        jBClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBClientes.setFocusPainted(false);
        jBClientes.setFocusable(false);
        jBClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBClientesActionPerformed(evt);
            }
        });
        getContentPane().add(jBClientes);
        jBClientes.setBounds(200, 210, 70, 78);

        jBVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/ventas_icon.png"))); // NOI18N
        jBVentas.setToolTipText("");
        jBVentas.setBorderPainted(false);
        jBVentas.setContentAreaFilled(false);
        jBVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBVentas.setFocusPainted(false);
        jBVentas.setFocusable(false);
        getContentPane().add(jBVentas);
        jBVentas.setBounds(320, 220, 80, 60);

        jBSesion.setFont(new java.awt.Font("Magneto", 0, 14)); // NOI18N
        jBSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/logout2.png"))); // NOI18N
        jBSesion.setToolTipText("Cierra sesión");
        jBSesion.setBorderPainted(false);
        jBSesion.setContentAreaFilled(false);
        jBSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBSesion.setFocusPainted(false);
        jBSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSesionActionPerformed(evt);
            }
        });
        getContentPane().add(jBSesion);
        jBSesion.setBounds(580, 220, 80, 70);

        jLArticulos.setFont(new java.awt.Font("Magneto", 1, 20)); // NOI18N
        jLArticulos.setText("Artículos");
        jLArticulos.setToolTipText("");
        getContentPane().add(jLArticulos);
        jLArticulos.setBounds(50, 160, 110, 26);

        jLCategorias.setFont(new java.awt.Font("Magneto", 1, 20)); // NOI18N
        jLCategorias.setText("Categorias");
        jLCategorias.setToolTipText("");
        getContentPane().add(jLCategorias);
        jLCategorias.setBounds(180, 160, 120, 26);

        jLMarcas.setFont(new java.awt.Font("Magneto", 1, 20)); // NOI18N
        jLMarcas.setText("Marcas");
        jLMarcas.setToolTipText("");
        getContentPane().add(jLMarcas);
        jLMarcas.setBounds(324, 160, 90, 26);

        jLTallas.setFont(new java.awt.Font("Magneto", 1, 20)); // NOI18N
        jLTallas.setText("Tallas");
        jLTallas.setToolTipText("");
        getContentPane().add(jLTallas);
        jLTallas.setBounds(450, 160, 70, 26);

        jLLineas_venta.setFont(new java.awt.Font("Magneto", 1, 20)); // NOI18N
        jLLineas_venta.setText("Lineas de venta");
        jLLineas_venta.setToolTipText("");
        getContentPane().add(jLLineas_venta);
        jLLineas_venta.setBounds(540, 160, 176, 26);

        jLEmpleados.setFont(new java.awt.Font("Magneto", 1, 20)); // NOI18N
        jLEmpleados.setText("Empleados");
        jLEmpleados.setToolTipText("");
        getContentPane().add(jLEmpleados);
        jLEmpleados.setBounds(45, 290, 120, 26);

        jLClientes.setFont(new java.awt.Font("Magneto", 1, 20)); // NOI18N
        jLClientes.setText("Clientes");
        jLClientes.setToolTipText("");
        getContentPane().add(jLClientes);
        jLClientes.setBounds(190, 290, 100, 26);

        jLVentas.setFont(new java.awt.Font("Magneto", 1, 20)); // NOI18N
        jLVentas.setText("Ventas");
        jLVentas.setToolTipText("");
        getContentPane().add(jLVentas);
        jLVentas.setBounds(330, 290, 80, 26);

        jBPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/pedidos_icon.png"))); // NOI18N
        jBPedidos.setToolTipText("");
        jBPedidos.setBorderPainted(false);
        jBPedidos.setContentAreaFilled(false);
        jBPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBPedidos.setFocusPainted(false);
        jBPedidos.setFocusable(false);
        getContentPane().add(jBPedidos);
        jBPedidos.setBounds(440, 210, 80, 80);

        jLPedidos.setFont(new java.awt.Font("Magneto", 1, 20)); // NOI18N
        jLPedidos.setText("Pedidos");
        jLPedidos.setToolTipText("");
        getContentPane().add(jLPedidos);
        jLPedidos.setBounds(440, 290, 90, 26);

        jBCerrar.setBackground(new java.awt.Color(255, 255, 255));
        jBCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icon_exit2.png"))); // NOI18N
        jBCerrar.setBorderPainted(false);
        jBCerrar.setContentAreaFilled(false);
        jBCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(jBCerrar);
        jBCerrar.setBounds(700, 0, 30, 20);

        fondo_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/menu_fondo.jpg"))); // NOI18N
        fondo_menu.setMaximumSize(new java.awt.Dimension(733, 365));
        fondo_menu.setMinimumSize(new java.awt.Dimension(733, 365));
        fondo_menu.setPreferredSize(new java.awt.Dimension(733, 365));
        getContentPane().add(fondo_menu);
        fondo_menu.setBounds(0, -10, 730, 380);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //BOTÓN CERRAR SESIÓN
    private void jBSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSesionActionPerformed
                
        int resp = JOptionPane.showConfirmDialog(null, "¿Cerrar Sesión?", "Confirmar acción", JOptionPane.YES_NO_OPTION);
        
        if (resp == JOptionPane.YES_OPTION) {
            try {
                login.setVisible(true);
                menu.setVisible(false);

            } catch (Throwable ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }      
    }//GEN-LAST:event_jBSesionActionPerformed
        //BOTÓN PROPIO CERRAR MENÚ
    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        //CONFIRMAR CERRAR PROGRAMA
        int resp = JOptionPane.showConfirmDialog(null, "¿Salir del programa?", "Confirmar acción", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else{

        }
    }//GEN-LAST:event_jBCerrarActionPerformed
        
    private void jBArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBArticulosActionPerformed
        //BOTÓN ARTÍCULOS
        Articulos articulos = null;
        try {
            articulos = new Articulos();
        } catch (Exception e) {
            e.printStackTrace();
        }
        articulos.setVisible(true);
    }//GEN-LAST:event_jBArticulosActionPerformed
        
    private void jBEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEmpleadosActionPerformed
        //BOTÓN EMPLEADOS
        Empleados empleados = null;
        try {
            empleados = new Empleados();
        } catch (Exception e) {
            e.printStackTrace();
        }
        empleados.setVisible(true);
    }//GEN-LAST:event_jBEmpleadosActionPerformed
        
    private void jBCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCategoriasActionPerformed
        //BOTÓN CATEGORIAS
        Categorias categorias = null;
        try {
            categorias = new Categorias();
        } catch (Exception e) {
            e.printStackTrace();
        }
        categorias.setVisible(true);
    }//GEN-LAST:event_jBCategoriasActionPerformed

    private void jBClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBClientesActionPerformed
        //BOTÓN CLIENTES
        Clientes clientes = null;
        try {
            clientes = new Clientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        clientes.setVisible(true);
    }//GEN-LAST:event_jBClientesActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                menu = new Menu();
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo_menu;
    private javax.swing.JButton jBArticulos;
    private javax.swing.JButton jBCategorias;
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBClientes;
    private javax.swing.JButton jBEmpleados;
    private javax.swing.JButton jBLineas_venta;
    private javax.swing.JButton jBMarcas;
    private javax.swing.JButton jBPedidos;
    private javax.swing.JButton jBSesion;
    private javax.swing.JButton jBTallas;
    private javax.swing.JButton jBVentas;
    private javax.swing.JLabel jLArticulos;
    private javax.swing.JLabel jLCategorias;
    private javax.swing.JLabel jLClientes;
    private javax.swing.JLabel jLEmpleados;
    private javax.swing.JLabel jLLineas_venta;
    private javax.swing.JLabel jLMarcas;
    private javax.swing.JLabel jLPedidos;
    private javax.swing.JLabel jLTallas;
    private javax.swing.JLabel jLVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel user_logeado;
    // End of variables declaration//GEN-END:variables
}
