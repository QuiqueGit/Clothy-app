package controlador;

import java.awt.Color;
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
import modelo.Categoria;
import utilidades.ConexionDB;

/**
 *
 * @author Quique
 */
public class Categorias extends javax.swing.JFrame {    
    
    ArrayList<Categoria> array_categorias = new ArrayList<>();
    Connection conex = new ConexionDB().getCon(); 
    DefaultTableModel model;
    Statement s;
    ResultSet rs;
    int x, y; //VARIABLES USADAS PARA EL MARCO DEL FRAME, PARA MOVERLO
    
    /**
     * Creates new form Categorias
     */
    public Categorias() throws SQLException, ClassNotFoundException {
        initComponents();
        this.setLocationRelativeTo(null); 
        jTextField1.setEnabled(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false); 
        //AWTUtilities.setWindowOpaque(this, false);//PARA EL MARCO MOVER FRAME       
        array_categorias.clear();//BORRAR ELEMENTOS DEL ARRAY PARA CUANDO CIERRE EMPLEADOS Y VUELVA A ABRIRLO NO SE DUPLIQUEN LOS DATOS
        this.model = (DefaultTableModel) jTable1.getModel();
        //SETEA EL ANCHO DE LAS COLUMNAS      
        TableColumnModel columnModel = jTable1.getColumnModel(); 
        columnModel.getColumn(0).setPreferredWidth(40);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(260);
        
        s = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM categorias";
        rs = s.executeQuery(query);
                
        while (rs.next()) { //CREO NUEVOS OBJETOS TIPO EMPLEADO Y LLENO EL ARRAYLIST MIENTRAS TENGA RESULTSET
            int id = Integer.parseInt(rs.getString("id"));
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");            
            
            array_categorias.add(new Categoria(id, nombre, descripcion));
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

        jBCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        info_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLMover = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(779, 335));
        setUndecorated(true);
        getContentPane().setLayout(null);

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
        jBCerrar.setBounds(760, 0, 20, 20);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 100, 20, 22);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        jLabel2.setFocusable(false);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 140, 63, 22);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Descripción");
        jLabel3.setFocusable(false);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 180, 91, 22);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Categorias");
        jLabel8.setFocusable(false);
        getContentPane().add(jLabel8);
        jLabel8.setBounds(90, 20, 220, 40);

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField1.setOpaque(false);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(130, 100, 140, 20);

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setBorder(null);
        jTextField2.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField2.setOpaque(false);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(130, 140, 142, 20);

        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField3.setBorder(null);
        jTextField3.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField3.setOpaque(false);
        getContentPane().add(jTextField3);
        jTextField3.setBounds(140, 180, 240, 20);

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/new_button.png"))); // NOI18N
        jButton5.setText("Nuevo");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusable(false);
        jButton5.setMargin(new java.awt.Insets(5, 14, 5, 14));
        jButton5.setMaximumSize(null);
        jButton5.setPreferredSize(new java.awt.Dimension(77, 23));
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/new_button_green.png"))); // NOI18N
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5MouseExited(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(30, 240, 100, 30);

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
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/edit_button_orange.png"))); // NOI18N
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton6MouseExited(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(130, 240, 100, 30);

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
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/delete_button_red.png"))); // NOI18N
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton7MouseExited(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(230, 240, 100, 30);

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/aceptar_button.png"))); // NOI18N
        jButton8.setText("Aceptar");
        jButton8.setBorder(null);
        jButton8.setContentAreaFilled(false);
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.setFocusable(false);
        jButton8.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/aceptar_button_green.png"))); // NOI18N
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton8MouseExited(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(50, 280, 120, 30);

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/cancel_button.png"))); // NOI18N
        jButton9.setText("Cancelar");
        jButton9.setBorder(null);
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setFocusable(false);
        jButton9.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/cancel_button_red.png"))); // NOI18N
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9MouseExited(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9);
        jButton9.setBounds(170, 280, 120, 30);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(30, 120, 240, 10);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(30, 160, 260, 10);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(30, 200, 290, 10);

        info_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/info_button.png"))); // NOI18N
        info_button.setBorder(null);
        info_button.setContentAreaFilled(false);
        info_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        info_button.setDisabledIcon(null);
        info_button.setFocusable(false);
        info_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                info_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(info_button);
        info_button.setBounds(310, 140, 30, 30);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
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
        jScrollPane1.setBounds(360, 20, 390, 280);

        jLMover.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLMover.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLMoverMouseDragged(evt);
            }
        });
        jLMover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLMoverMousePressed(evt);
            }
        });
        getContentPane().add(jLMover);
        jLMover.setBounds(0, 0, 760, 20);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/tab_fondo2.jpg"))); // NOI18N
        jLabel4.setMaximumSize(new java.awt.Dimension(779, 335));
        jLabel4.setMinimumSize(new java.awt.Dimension(779, 335));
        jLabel4.setPreferredSize(new java.awt.Dimension(779, 335));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 779, 335);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //BOTÓN NUEVO     
        jTextField1.setText(String.valueOf(array_categorias.get(array_categorias.size() - 1).getId()+1));        
        jTextField2.setText("");
        jTextField3.setText("");
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jButton8.setVisible(true);
        jButton9.setVisible(true);
        info_button.setEnabled(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        //BOTÓN EDITAR
        if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna categoria", "Error Update", JOptionPane.ERROR_MESSAGE);
        }else{
        try {
            String vNombre, vDescripcion;
            int vId = Integer.parseInt(jTextField1.getText());
            vNombre = jTextField2.getText();
            vDescripcion = jTextField3.getText();

            //REALIZA UPDATE EN LA BASE DE DATOS
            String url = "jdbc:mysql://localhost:3306/clothy";
            String user = "root";
            String pass = "";
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement st = connection.createStatement();
            String query = "update categorias set nombre='" + vNombre + "', descripcion='" + vDescripcion + "' WHERE id=" + vId;
            st.executeUpdate(query);
            //REALIZA UPDATE EN EL ARRAY
            int i = jTable1.getSelectedRow();
            array_categorias.get(i).setId(vId);
            array_categorias.get(i).setNombre(vNombre);
            array_categorias.get(i).setDescripcion(vDescripcion);
            //REALIZA UPDATE EN LA TABLA
            i = jTable1.getSelectedRow();
            model.setValueAt(jTextField1.getText(), i, 0);
            model.setValueAt(jTextField2.getText(), i, 1);
            model.setValueAt(jTextField3.getText(), i, 2);

        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //BOTÓN BORRAR
        int resp = 0, id = 0;
        
        if (jTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna categoria", "Error Delete", JOptionPane.ERROR_MESSAGE);
        } else {

                resp = JOptionPane.showConfirmDialog(null, "¿Desea borrar la categoria seleccionada?", "Confirmar acción", JOptionPane.YES_NO_OPTION);
            
            
            int i = 0;
             
            if (resp == JOptionPane.YES_OPTION) {                

                try {
                    //GUARDAMOS EN UNA VARIABLE EL ID QUE HAY QUE ELIMINAR DE LA BBDD, 'i' REPRESENTA LA FILA SELECCIONADA DE LA TABLA
                    // 'i' PUEDE REPRESENTAR EL MODELO O LA VISTA DE LA TABLA, DEPENDIENDO SI ES CUADRO DE BUSCAR ESTÁ VACIO O NO...
                    String vId = jTextField1.getText();
                    //REALIZA DELETE EN LA BASE DE DATOS
                    String url = "jdbc:mysql://localhost:3306/clothy";
                    String user = "root";
                    String pass = "";
                    Connection connection = DriverManager.getConnection(url, user, pass);
                    Statement st = connection.createStatement();                
                    String query = "DELETE FROM categorias WHERE id=" + vId;   
                    st.executeUpdate(query);                                        
                                        
                    //DELETE EN EL ARRAY
                    int idd = Integer.parseInt(jTextField1.getText());
                    for (int j = 0; j < array_categorias.size(); j++) {
                        if (array_categorias.get(j).getId() == idd) {                           
                           array_categorias.remove(j);
                        }                        
                    }                                             
                    //REALIZA DELETE EN LA TABLA, ELIMINA UNA FILA SELECCIONADA
                    i = jTable1.getSelectedRow();
                    if (i >= 0) {
                        model.removeRow(i);
                    }
                    
                    jTextField1.setText("");
                    jTextField2.setText("");
                    jTextField3.setText("");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "No puede eliminar una categoria que tenga artículos asignados", "Error Delete", JOptionPane.ERROR_MESSAGE);
                    //Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }                    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //BOTÓN CANCELAR
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        info_button.setEnabled(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //BOTÓN ACEPTAR
        try {
            int vId;
            String vNombre, vDescripcion;
            
            vId = Integer.parseInt(jTextField1.getText());           
            vNombre = jTextField2.getText();
            vDescripcion = jTextField3.getText();

            //INSERT EN LA BASE DE DATOS
            PreparedStatement ps = conex.prepareStatement("INSERT INTO categorias (id, nombre, descripcion) VALUES (?,?,?)");
            ps.setInt(1, vId);
            ps.setString(2, vNombre);
            ps.setString(3, vDescripcion);

            ps.executeUpdate();
            //INSERT EN LA TABLA            
            model.addRow(new Object[]{vId, vNombre,vDescripcion});
            //INSERT EN EL ARRAY
            array_categorias.add(new Categoria(vId,vNombre,vDescripcion));
            
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jButton5.setEnabled(true);
            jButton6.setEnabled(true);
            jButton7.setEnabled(true);
            jButton8.setVisible(false);
            jButton9.setVisible(false);
            info_button.setEnabled(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        //BOTÓN X, CERRAR VENTANA INDIVIDUAL
        this.dispose();
    }//GEN-LAST:event_jBCerrarActionPerformed
    //EVENTOS RATÓN SOBRE BOTONES...CAMBIO DE COLOR...
    private void jButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseEntered
        jButton5.setForeground(Color.green);
    }//GEN-LAST:event_jButton5MouseEntered

    private void jButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseExited
        jButton5.setForeground(Color.white);
    }//GEN-LAST:event_jButton5MouseExited

    private void jButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseEntered
        jButton6.setForeground(Color.orange);
    }//GEN-LAST:event_jButton6MouseEntered

    private void jButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseExited
        jButton6.setForeground(Color.white);
    }//GEN-LAST:event_jButton6MouseExited

    private void jButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseEntered
        jButton7.setForeground(Color.red);
    }//GEN-LAST:event_jButton7MouseEntered

    private void jButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseExited
        jButton7.setForeground(Color.white);
    }//GEN-LAST:event_jButton7MouseExited

    private void jButton8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseEntered
        jButton8.setForeground(Color.green);
    }//GEN-LAST:event_jButton8MouseEntered

    private void jButton8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseExited
        jButton8.setForeground(Color.white);
    }//GEN-LAST:event_jButton8MouseExited

    private void jButton9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseEntered
        jButton9.setForeground(Color.red);
    }//GEN-LAST:event_jButton9MouseEntered

    private void jButton9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseExited
        jButton9.setForeground(Color.white);
    }//GEN-LAST:event_jButton9MouseExited
    //FIN EVENTOS RATÓN SOBRE BOTONES
    
    private void info_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_info_buttonActionPerformed
        //BOTÓN INFO
        String nomCat = null;
        if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione una categoria para ver la cantidad de articulos", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                int cat = Integer.parseInt(jTextField1.getText());
                int count = 0;
                s = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                String query = "SELECT categoria FROM articulos";
                ResultSet r1 = s.executeQuery(query);

                while (r1.next()) {
                    if (cat == Integer.parseInt(r1.getString("categoria"))) {
                        count += 1;
                    }
                }
                try {
                    Articulos metodo = new Articulos();
                    nomCat = metodo.getNombreCateg(cat);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (count == 0) {
                    JOptionPane.showMessageDialog(null, "La categoria seleccionada '" + nomCat + "' no tiene artículos.", "Información", JOptionPane.INFORMATION_MESSAGE);                                        
                }else{
                    JOptionPane.showMessageDialog(null, "La categoria seleccionada '" + nomCat + "' tiene " + count + " artículos.", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException numberFormatException) {
            } catch (SQLException sQLException) {
            }
        }

    }//GEN-LAST:event_info_buttonActionPerformed

    private void jLMoverMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLMoverMouseDragged
        //EVENTO RATÓN ARRASTRAR (Drag)
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLMoverMouseDragged

    private void jLMoverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLMoverMousePressed
        //EVENTO RATÓN PULSADO (Press)
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jLMoverMousePressed
    
    
    public void añadirFilasTabla(){ 
        Object datosFila []= new Object [3]; //EL RANGO DEL ARRAY REPRESENTA LAS COLUMNAS DE LA TABLA, EN ESTE CASO 3
        
        for (int i = 0; i < array_categorias.size(); i++) {
            datosFila[0] = array_categorias.get(i).getId();
            datosFila[1] = array_categorias.get(i).getNombre();
            datosFila[2] = array_categorias.get(i).getDescripcion();

            model.addRow(datosFila);
        }
    }
    
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
            java.util.logging.Logger.getLogger(Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Categorias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Categorias();
                } catch (SQLException ex) {
                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton info_button;
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLMover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
