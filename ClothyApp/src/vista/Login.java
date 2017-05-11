package vista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.*;
import javax.swing.JOptionPane;
import static vista.Menu.menu;

/**
 *
 * @author Quique
 */
public class Login extends javax.swing.JFrame {
    
    ArrayList<Empleado> empleados;   
    static Login login;
    static String user_actual;
    
    /**
     * Creates new form Login
     * @throws java.sql.SQLException
     */
    public Login() throws SQLException {        
        
        initComponents(); 
        this.setLocationRelativeTo(null); 
        empleados = utilidades.ConexionDB.empleados(); //arrayList con los empleados     

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLTitulo = new javax.swing.JLabel();
        jLUsuario = new javax.swing.JLabel();
        jLPass = new javax.swing.JLabel();
        jTFUsuario = new javax.swing.JTextField();
        jPFPass = new javax.swing.JPasswordField();
        jBLogin = new javax.swing.JButton();
        jBCerrar = new javax.swing.JButton();
        jLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMaximumSize(new java.awt.Dimension(485, 600));
        setMinimumSize(new java.awt.Dimension(485, 600));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(485, 600));
        getContentPane().setLayout(null);

        jLTitulo.setFont(new java.awt.Font("Magneto", 2, 48)); // NOI18N
        jLTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLTitulo.setText("Clothy");
        getContentPane().add(jLTitulo);
        jLTitulo.setBounds(260, 70, 170, 80);

        jLUsuario.setFont(new java.awt.Font("Magneto", 0, 24)); // NOI18N
        jLUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLUsuario.setText("Usuario");
        getContentPane().add(jLUsuario);
        jLUsuario.setBounds(290, 290, 120, 20);

        jLPass.setFont(new java.awt.Font("Magneto", 0, 24)); // NOI18N
        jLPass.setForeground(new java.awt.Color(255, 255, 255));
        jLPass.setText("Contraseña");
        getContentPane().add(jLPass);
        jLPass.setBounds(280, 370, 150, 30);

        jTFUsuario.setBorder(null);
        getContentPane().add(jTFUsuario);
        jTFUsuario.setBounds(290, 330, 110, 20);

        jPFPass.setBorder(null);
        getContentPane().add(jPFPass);
        jPFPass.setBounds(290, 420, 110, 20);

        jBLogin.setFont(new java.awt.Font("Magneto", 0, 18)); // NOI18N
        jBLogin.setText("Login");
        jBLogin.setBorderPainted(false);
        jBLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBLoginActionPerformed(evt);
            }
        });
        getContentPane().add(jBLogin);
        jBLogin.setBounds(270, 480, 150, 39);

        jBCerrar.setForeground(new java.awt.Color(255, 255, 255));
        jBCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/icon_exit.png"))); // NOI18N
        jBCerrar.setBorder(null);
        jBCerrar.setBorderPainted(false);
        jBCerrar.setContentAreaFilled(false);
        jBCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBCerrar.setFocusable(false);
        jBCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(jBCerrar);
        jBCerrar.setBounds(462, 0, 30, 20);

        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/fondoLogin.png"))); // NOI18N
        getContentPane().add(jLFondo);
        jLFondo.setBounds(0, 0, 485, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //BOTÓN CERRAR LOGIN
    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        //CONFIRMAR CERRAR PROGRAMA
        int resp = JOptionPane.showConfirmDialog(null, "¿Salir del programa?", "Confirmar acción", JOptionPane.YES_NO_OPTION);
           if (resp == JOptionPane.YES_OPTION) {
               System.exit(0);               
           } else{
               
           }                
    }//GEN-LAST:event_jBCerrarActionPerformed

    
    //BOTÓN LOGIN
    private void jBLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBLoginActionPerformed

        String user = jTFUsuario.getText();
        String pass = jPFPass.getText();
        boolean isUser = false;
        boolean isAdmin = false;

        for (Empleado emp : empleados) { //BUCLE FOR EACH PARA RECORRER ARRAY DE EMPLEADOS

            String userAc = emp.getNom();

            if (user.equalsIgnoreCase(userAc) && pass.equalsIgnoreCase(emp.getPass())) {
                isUser = true;            
                user_actual = user;
                
            }
        }

        if (isUser == true) {
            menu = new Menu();
            //HACEMOS VISIBLE EL MENU
            menu.setVisible(true); 
            //RESET CAMPOS LOGIN
            jTFUsuario.setText("");     
            jPFPass.setText("");
            //OCULTAMOS LOGIN
            login.setVisible(false); 
        } else {
            //MENSAJE ERROR USER Y PASS
            JOptionPane.showMessageDialog(null,"Usuario o contraseña no válidos", "Error", JOptionPane.ERROR_MESSAGE);            
        }
    }//GEN-LAST:event_jBLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {      

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                //Establecer conexión con la DataBase
                try {
                    
                    new ConexionDB(); 
                    
                } catch (Exception e) {
                    e.printStackTrace();
                } 
                
                try {
                    
                   login = new Login();
                   login.setVisible(true);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }                
                              
            }
        });
        
        
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jBLogin;
    private javax.swing.JLabel jLFondo;
    private javax.swing.JLabel jLPass;
    private javax.swing.JLabel jLTitulo;
    private javax.swing.JLabel jLUsuario;
    private javax.swing.JPasswordField jPFPass;
    private javax.swing.JTextField jTFUsuario;
    // End of variables declaration//GEN-END:variables
}
