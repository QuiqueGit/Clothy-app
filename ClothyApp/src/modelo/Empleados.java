package modelo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import utilidades.ConexionDB;

/**
 *
 * @author Quique
 */
public class Empleados extends javax.swing.JFrame {
    
        ArrayList<Empleado> array_empleados = new ArrayList<>(); //CREO UN ARRAYLIST DE TIPO EMPLEADO PARA ALMACENAR TODOS LOS EMPLEADOS   
        Connection conex = new ConexionDB().getCon(); //OBTENGO LA CONEXION QUE CREO EN OTRO PAQUETE        
        DefaultTableModel model;
        Statement s;
        ResultSet rs;
    
    public Empleados() throws SQLException, ClassNotFoundException {
        initComponents();
        //jTextField1.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);  
        
        this.setLocationRelativeTo(null);   
        array_empleados.clear();//BORRAR ELEMENTOS DEL ARRAY PARA CUANDO CIERRE EMPLEADOS Y VUELVA A ABRIRLO NO SE DUPLIQUEN LOS DATOS
        this.model = (DefaultTableModel) jTable1.getModel();
        //SETEA EL ANCHO DE LAS COLUMNAS      
        TableColumnModel columnModel = jTable1.getColumnModel(); 
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(105);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(220);
        columnModel.getColumn(4).setPreferredWidth(180);
        columnModel.getColumn(5).setPreferredWidth(150);
        columnModel.getColumn(6).setPreferredWidth(110);
        columnModel.getColumn(7).setPreferredWidth(110);
        
        s = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM empleados";
        rs = s.executeQuery(query);
                
        while (rs.next()) { //CREO NUEVOS OBJETOS TIPO EMPLEADO Y LLENO EL ARRAYLIST MIENTRAS TENGA RESULTSET
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String direccion = rs.getString("direccion");
            String email = rs.getString("email");            
            String telefono= rs.getString("telefono");                        
            String id_login = rs.getString("id_login");
            String pass = rs.getString("password");
            
            array_empleados.add(new Empleado(id, nombre, apellidos, direccion, email, telefono, id_login, pass));            
        }        
        
        añadirFilasTabla();
        
        jTable1.addMouseListener(new MouseAdapter() { //CREA UN LISTENER PARA EL RATÓN                           
           @Override
           public void mouseClicked (MouseEvent e){ //CUANDO SELECCIONAS UNA FILA DE LA TABLA, SETEA LOS jTextField PARA EDITARLOS        
               
               int i = jTable1.getSelectedRow();
               // 'i' HACE REFERENCIA AL NÚMERO DE LA FILA SELECCIONADA Y EL NÚMERO HACE REFERENCIA A LA COLUMNA (i,n)
               jTextField1.setText(model.getValueAt(i, 0).toString());
               jTextField2.setText(model.getValueAt(i, 1).toString());
               jTextField3.setText(model.getValueAt(i, 2).toString());
               jTextField4.setText(model.getValueAt(i, 3).toString());
               jTextField5.setText(model.getValueAt(i, 4).toString());
               jTextField6.setText(model.getValueAt(i, 5).toString());
               jTextField7.setText(model.getValueAt(i, 6).toString());
               jTextField8.setText(model.getValueAt(i, 7).toString());               
           }
       });    
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField7 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(810, 500));
        setMinimumSize(new java.awt.Dimension(810, 500));
        setPreferredSize(new java.awt.Dimension(810, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellidos", "Dirección", "Email", "Teléfono", "id_Login", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoscrolls(false);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.setMaximumSize(new java.awt.Dimension(520, 0));
        jTable1.setOpaque(false);
        jTable1.setRowHeight(30);
        jTable1.setRowMargin(10);
        jTable1.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jTable1.setShowVerticalLines(false);
        jTable1.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(280, 120, 500, 200);

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setBorder(null);
        jTextField2.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField2.setOpaque(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2);
        jTextField2.setBounds(120, 90, 142, 20);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        jLabel2.setFocusable(false);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 90, 63, 22);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(20, 110, 240, 10);

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setEnabled(false);
        jTextField1.setFocusable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(20, 50, 39, 24);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellidos");
        jLabel3.setFocusable(false);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 130, 73, 22);

        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setBorder(null);
        jTextField3.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField3.setOpaque(false);
        getContentPane().add(jTextField3);
        jTextField3.setBounds(120, 130, 142, 20);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(20, 150, 240, 10);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dirección");
        jLabel4.setFocusable(false);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 170, 80, 22);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(20, 190, 240, 10);

        jTextField4.setBackground(new java.awt.Color(204, 204, 204));
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(null);
        jTextField4.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField4.setOpaque(false);
        getContentPane().add(jTextField4);
        jTextField4.setBounds(120, 170, 142, 20);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email");
        jLabel5.setFocusable(false);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 210, 45, 22);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(20, 230, 240, 10);

        jTextField5.setBackground(new java.awt.Color(204, 204, 204));
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setBorder(null);
        jTextField5.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField5.setOpaque(false);
        getContentPane().add(jTextField5);
        jTextField5.setBounds(120, 210, 142, 20);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Teléfono");
        jLabel6.setFocusable(false);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 250, 68, 22);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(20, 270, 240, 10);

        jTextField6.setBackground(new java.awt.Color(204, 204, 204));
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setBorder(null);
        jTextField6.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField6.setOpaque(false);
        getContentPane().add(jTextField6);
        jTextField6.setBounds(120, 250, 142, 20);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Id_Login");
        jLabel7.setFocusable(false);
        getContentPane().add(jLabel7);
        jLabel7.setBounds(20, 290, 72, 22);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(20, 310, 240, 10);

        jTextField7.setBackground(new java.awt.Color(204, 204, 204));
        jTextField7.setForeground(new java.awt.Color(255, 255, 255));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setBorder(null);
        jTextField7.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField7.setOpaque(false);
        getContentPane().add(jTextField7);
        jTextField7.setBounds(120, 290, 142, 20);

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/newx24b.png"))); // NOI18N
        jButton5.setText("Nuevo");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusable(false);
        jButton5.setMargin(new java.awt.Insets(5, 14, 5, 14));
        jButton5.setMaximumSize(null);
        jButton5.setPreferredSize(new java.awt.Dimension(77, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(10, 390, 100, 30);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/edit_button.png"))); // NOI18N
        jButton6.setText("Editar");
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setFocusable(false);
        jButton6.setMaximumSize(null);
        jButton6.setMinimumSize(new java.awt.Dimension(96, 40));
        jButton6.setPreferredSize(new java.awt.Dimension(77, 23));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(110, 390, 100, 30);

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/delete_button.png"))); // NOI18N
        jButton7.setText("Borrar");
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setFocusable(false);
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton7.setPreferredSize(new java.awt.Dimension(77, 23));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(210, 390, 100, 30);

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/cancel_button.png"))); // NOI18N
        jButton9.setText("Cancelar");
        jButton9.setBorder(null);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setFocusable(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9);
        jButton9.setBounds(150, 430, 120, 30);

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/aceptar_button.png"))); // NOI18N
        jButton8.setText("Aceptar");
        jButton8.setBorder(null);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setFocusable(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(30, 430, 120, 30);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Empleados");
        jLabel8.setFocusable(false);
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 10, 190, 40);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Password");
        jLabel9.setFocusable(false);
        getContentPane().add(jLabel9);
        jLabel9.setBounds(20, 340, 80, 22);

        jTextField8.setBackground(new java.awt.Color(204, 204, 204));
        jTextField8.setForeground(new java.awt.Color(255, 255, 255));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setBorder(null);
        jTextField8.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField8.setOpaque(false);
        getContentPane().add(jTextField8);
        jTextField8.setBounds(120, 340, 142, 20);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(20, 360, 240, 10);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/tab_fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -20, 990, 590);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //BOTÓN NUEVO       
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");       
        jTextField6.setText("");      
        jTextField7.setText("");
        jTextField8.setText("");
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jButton8.setVisible(true);
        jButton9.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //BOTÓN EDITAR
        try {
            String vNombre, vApellidos, vDireccion, vEmail, vTelefono, vId_Login, vPass;
            
            int vId = Integer.parseInt(jTextField1.getText());
            vNombre = jTextField2.getText();
            vApellidos = jTextField3.getText();
            vDireccion = jTextField4.getText();
            vEmail = jTextField5.getText();
            vTelefono = jTextField6.getText();
            vId_Login = jTextField7.getText();
            vPass = jTextField8.getText();
            //REALIZA UPDATE EN LA BASE DE DATOS
            String url = "jdbc:mysql://localhost:3306/clothy";
            String user = "root";
            String pass = "123";
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement st = connection.createStatement();
            String query = "update empleados set nombre='" + vNombre + "', apellidos='" + vApellidos + "', direccion='" + vDireccion + "', email='" + vEmail + "', telefono='" + vTelefono + "', id_login='" + vId_Login + "', password='" + vPass + "' WHERE id=" + vId;
            st.executeUpdate(query);
            //REALIZA UPDATE EN EL ARRAY
            int i = jTable1.getSelectedRow();
            array_empleados.get(i).setNombre(vNombre);
            array_empleados.get(i).setApellidos(vApellidos);
            array_empleados.get(i).setDireccion(vDireccion);
            array_empleados.get(i).setEmail(vEmail);
            array_empleados.get(i).setTelefono(vTelefono);
            array_empleados.get(i).setId_login(vId_Login);
            array_empleados.get(i).setPassword(vPass);
            //REALIZA UPDATE EN LA TABLA
            i = jTable1.getSelectedRow();
            model.setValueAt(jTextField2.getText(), i, 1);
            model.setValueAt(jTextField3.getText(), i, 2);
            model.setValueAt(jTextField4.getText(), i, 3);
            model.setValueAt(jTextField5.getText(), i, 4);
            model.setValueAt(jTextField6.getText(), i, 5);
            model.setValueAt(jTextField7.getText(), i, 6);
            model.setValueAt(jTextField8.getText(), i, 7);
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //BOTÓN BORRAR
        if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún empleado", "Error Delete", JOptionPane.ERROR_MESSAGE);          
        }else{
        int i = 0;
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea borrar al empleado seleccionado?", "Confirmar acción", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            //REALIZA DELETE EN LA TABLA, ELIMINA UNA FILA SELECCIONADA
                i = jTable1.getSelectedRow();
                if (i >= 0) {
                    model.removeRow(i);
                } else {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado ningún empleado", "Error Delete", JOptionPane.ERROR_MESSAGE);
                }
            } 

            try {
                //GUARDAMOS EN UNA VARIABLE EL ID QUE HAY QUE ELIMINAR DE LA BBDD, 'i' REPRESENTA LA FILA SELECCIONADA DE LA TABLA
                // 'i' PUEDE REPRESENTAR EL MODELO O LA VISTA DE LA TABLA, DEPENDIENDO SI ES CUADRO DE BUSCAR ESTÁ VACIO O NO...
                int vId = Integer.parseInt(jTextField1.getText());
                //REALIZA DELETE EN LA BASE DE DATOS
                String url = "jdbc:mysql://localhost:3306/clothy";
                String user = "root";
                String pass = "123";
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement st = connection.createStatement();
                String query = "DELETE FROM empleados WHERE id=" + vId;
                st.executeUpdate(query);

                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField7.setText("");
                jTextField8.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (resp == JOptionPane.NO_OPTION) {
                //NO HACER NADA
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //BOTÓN CANCELAR
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //BOTÓN ACEPTAR
        try {
            String vNombre, vApellidos, vDireccion, vEmail, vTelefono, vId_Login, vPass;
            
            int vId;
            vNombre = jTextField2.getText();
            vApellidos = jTextField3.getText();
            vDireccion = jTextField4.getText();
            vEmail = jTextField5.getText();
            vTelefono = jTextField6.getText();
            vId_Login = jTextField7.getText();
            vPass = jTextField8.getText();
            
            //INSERT EN LA BASE DE DATOS
            PreparedStatement ps = conex.prepareStatement("INSERT INTO empleados (nombre, apellidos, direccion, email, telefono, id_login, password) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, vNombre);
            ps.setString(2, vApellidos);
            ps.setString(3, vDireccion);
            ps.setString(4, vEmail);
            ps.setString(5, vTelefono);
            ps.setString(6, vId_Login);
            ps.setString(7, vPass);
            ps.executeUpdate();
            //INSERT EN LA TABLA
            vId = (array_empleados.get(array_empleados.size()-1).getId()+1);
            model.addRow(new Object[]{vId, vNombre,vApellidos,vDireccion,vEmail,vTelefono,vId_Login,vPass});
            //INSERT EN EL ARRAY
            array_empleados.add(new Empleado(vId,vNombre,vApellidos,vDireccion,vEmail,vTelefono,vId_Login,vPass));

            jButton5.setEnabled(true);
            jButton6.setEnabled(true);
            jButton7.setEnabled(true);
            jButton8.setVisible(false);
            jButton9.setVisible(false);         

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Empleados();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Empleados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void añadirFilasTabla(){ 
        Object datosFila []= new Object [8]; //EL RANGO DEL ARRAY REPRESENTA LAS COLUMNAS DE LA TABLA, EN ESTE CASO 8
        
        for (int i = 0; i < array_empleados.size(); i++) {
            datosFila[0] = array_empleados.get(i).getId();
            datosFila[1] = array_empleados.get(i).getNombre();
            datosFila[2] = array_empleados.get(i).getApellidos();            
            datosFila[3] = array_empleados.get(i).getDireccion();
            datosFila[4] = array_empleados.get(i).getEmail();            
            datosFila[5] = array_empleados.get(i).getTelefono();
            datosFila[6] = array_empleados.get(i).getId_login();
            datosFila[7] = array_empleados.get(i).getPassword();

            model.addRow(datosFila);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
