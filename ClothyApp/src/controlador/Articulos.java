package controlador;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import modelo.Articulo;
import utilidades.ConexionDB;


/**
 *
 * @author Quique
 */
public class Articulos extends javax.swing.JFrame {
    
    static ArrayList<Articulo> array_articulos = new ArrayList<>(); //CREO UN ARRAYLIST DE TIPO ARTICULO PARA ALMACENAR TODOS LOS ARTICULOS
   
    Connection conex = new ConexionDB().getCon(); //OBTENGO LA CONEXION QUE CREO EN OTRO PAQUETE
   
    DefaultTableModel model;    
    Statement s;
    ResultSet rs;
    
    int viewRow, modelRow;

    /**
     * Creates new form Articulos
     */
    public Articulos() throws SQLException, ClassNotFoundException {        
        initComponents();         
        this.setLocationRelativeTo(null);   
        array_articulos.clear();//BORRAR TODO DEL ARRAY PARA CUANDO CIERRE ARTICULOS Y VUELVA A ABRIRLO NO SE DUPLIQUEN LOS DATOS
        this.model = (DefaultTableModel) jTable1.getModel();
        //SETEA EL ANCHO DE LAS COLUMNAS      
        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(45);
        columnModel.getColumn(1).setPreferredWidth(180);
        columnModel.getColumn(2).setPreferredWidth(180);
        columnModel.getColumn(3).setPreferredWidth(65);
        columnModel.getColumn(4).setPreferredWidth(130);
        columnModel.getColumn(5).setPreferredWidth(150);
        columnModel.getColumn(6).setPreferredWidth(60);
        //SETEA HACIA LA DERECHA LA COLUMNA 2 DE LA TABLA(ES UN STRING, PARA LA ORDENACIÓN)
        //LOS TIPO NÚMEROS EN GENERAL(INT, FLOAT...) LOS SETEA AUTOMÁTICAMENTE HACIA LA DERECHA
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
  
        jTextField1.setVisible(false);
        statusText.setVisible(false);
        jCBCat.setVisible(false);
        jCBMar.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);          
        
        s = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM articulos";
        rs = s.executeQuery(query);
                
        while (rs.next()) { //CREO NUEVOS OBJETOS TIPO ARTICULO Y LLENO EL ARRAYLIST MIENTRAS TENGA RESULTSET
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            float precio = Float.parseFloat(rs.getString("precio"));
            int categoria = Integer.parseInt(rs.getString("categoria"));            
            int marca= Integer.parseInt(rs.getString("marca"));                        
            int existencias = Integer.parseInt(rs.getString("existencias"));
            
            array_articulos.add(new Articulo(id, nombre, descripcion, precio, categoria, marca, existencias));            
        }            
                       
        añadirFilasTabla(); //MÉTODO QUE LLENA LA TABLA CON LA INFO DEL ARRAYLIST
        llenarCategorias(); //MÉTODO PARA LLENAR COMBOBOX DE CATEGORIAS
        llenarMarcas();     //MÉTODO PARA LLENAR COMBOBOX DE MARCAS
                
        jTable1.addMouseListener(new MouseAdapter() { //CREA UN LISTENER PARA EL RATÓN
                           
           @Override
           public void mouseClicked (MouseEvent e){ //CUANDO SELECCIONAS UNA FILA DE LA TABLA, SETEA LOS jTextField PARA EDITARLOS              
               if (jTFBuscar.getText().isEmpty()) {
               int i = jTable1.getSelectedRow();
               // 'i' HACE REFERENCIA AL NÚMERO DE LA FILA SELECCIONADA Y EL NÚMERO HACE REFERENCIA A LA COLUMNA (i,n)
               jTextField1.setText(model.getValueAt(i, 0).toString());
               jTextField2.setText(model.getValueAt(i, 1).toString());
               jTextField3.setText(model.getValueAt(i, 2).toString());
               jTextField4.setText(model.getValueAt(i, 3).toString());
               jTextField5.setText(model.getValueAt(i, 4).toString());
               jTextField6.setText(model.getValueAt(i, 5).toString());
               jTextField7.setText(model.getValueAt(i, 6).toString());
               }else{
                    int i = modelRow;
                    jTextField1.setText(model.getValueAt(i, 0).toString());
                    jTextField2.setText(model.getValueAt(i, 1).toString());
                    jTextField3.setText(model.getValueAt(i, 2).toString());
                    jTextField4.setText(model.getValueAt(i, 3).toString());
                    jTextField5.setText(model.getValueAt(i, 4).toString());
                    jTextField6.setText(model.getValueAt(i, 5).toString());
                    jTextField7.setText(model.getValueAt(i, 6).toString());
               }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTFBuscar = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jCBCat = new javax.swing.JComboBox<>();
        jCBMar = new javax.swing.JComboBox<>();
        statusText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1013, 460));
        setMinimumSize(new java.awt.Dimension(1013, 460));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1013, 460));
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
        jBCerrar.setBounds(990, 0, 30, 20);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        jLabel2.setFocusable(false);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 120, 63, 22);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Descripción");
        jLabel3.setFocusable(false);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 160, 91, 22);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Precio");
        jLabel4.setFocusable(false);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 200, 49, 22);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Categoria");
        jLabel5.setFocusable(false);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 240, 76, 22);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Marca");
        jLabel6.setFocusable(false);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 280, 50, 22);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Existencias");
        jLabel7.setFocusable(false);
        getContentPane().add(jLabel7);
        jLabel7.setBounds(50, 320, 85, 22);

        jLabel8.setFont(new java.awt.Font("Magneto", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Artículos");
        jLabel8.setFocusable(false);
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 20, 200, 30);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/vista/images/buscar_iconx24.png"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(140, 60, 40, 40);

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setEnabled(false);
        jTextField1.setFocusable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(50, 80, 39, 24);

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
        jTextField2.setBounds(150, 120, 142, 20);

        jTextField3.setBackground(new java.awt.Color(204, 204, 204));
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setBorder(null);
        jTextField3.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField3.setOpaque(false);
        getContentPane().add(jTextField3);
        jTextField3.setBounds(150, 160, 142, 20);

        jTextField4.setBackground(new java.awt.Color(204, 204, 204));
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(null);
        jTextField4.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField4.setOpaque(false);
        getContentPane().add(jTextField4);
        jTextField4.setBounds(150, 200, 142, 20);

        jTextField5.setBackground(new java.awt.Color(204, 204, 204));
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setBorder(null);
        jTextField5.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField5.setOpaque(false);
        getContentPane().add(jTextField5);
        jTextField5.setBounds(150, 240, 142, 20);

        jTextField6.setBackground(new java.awt.Color(204, 204, 204));
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setBorder(null);
        jTextField6.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField6.setOpaque(false);
        getContentPane().add(jTextField6);
        jTextField6.setBounds(150, 280, 142, 20);

        jTextField7.setBackground(new java.awt.Color(204, 204, 204));
        jTextField7.setForeground(new java.awt.Color(255, 255, 255));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setBorder(null);
        jTextField7.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField7.setOpaque(false);
        getContentPane().add(jTextField7);
        jTextField7.setBounds(150, 320, 142, 20);

        jTFBuscar.setBackground(new java.awt.Color(204, 204, 204));
        jTFBuscar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTFBuscar.setForeground(new java.awt.Color(255, 255, 255));
        jTFBuscar.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTFBuscar.setBorder(null);
        jTFBuscar.setCaretColor(new java.awt.Color(255, 255, 255));
        jTFBuscar.setName("Buscar"); // NOI18N
        jTFBuscar.setOpaque(false);
        jTFBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(jTFBuscar);
        jTFBuscar.setBounds(170, 70, 120, 19);

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
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(20, 370, 100, 30);

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
        jButton6.setBounds(120, 370, 100, 30);

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
        jButton7.setBounds(220, 370, 100, 30);

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
        jButton8.setBounds(40, 410, 120, 30);

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
        jButton9.setBounds(160, 410, 120, 30);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(50, 340, 240, 10);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(50, 140, 240, 10);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(50, 180, 240, 10);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(50, 220, 240, 10);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(50, 260, 240, 10);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(50, 300, 240, 10);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(170, 90, 120, 10);

        jCBCat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jCBCat);
        jCBCat.setBounds(170, 240, 140, 20);

        jCBMar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jCBMar);
        jCBMar.setBounds(170, 280, 140, 20);
        getContentPane().add(statusText);
        statusText.setBounds(290, 410, 30, 24);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Precio", "Categoria", "Marca", "Existencias"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jScrollPane1.setBounds(330, 20, 660, 420);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/tab_fondo2.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1013, 460));
        jLabel1.setMinimumSize(new java.awt.Dimension(1013, 460));
        jLabel1.setPreferredSize(new java.awt.Dimension(1013, 460));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1020, 470);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //BOTÓN NUEVO 
        jTFBuscar.setText("");
        jTFBuscar.setEnabled(false);        
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField5.setVisible(false);
        jCBCat.setVisible(true);
        jTextField6.setText("");
        jTextField6.setVisible(false);        
        jCBMar.setVisible(true);
        jTextField7.setText("");         
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jButton8.setVisible(true);
        jButton9.setVisible(true);    
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       //BOTÓN EDITAR         
            try {
            String vNombre, vDescripcion, cat, mar;
            int vId, vMarca, vExistencias, vCategoria;
            float vPrecio;

            vId = Integer.parseInt(jTextField1.getText());
            vNombre = jTextField2.getText();
            vDescripcion = jTextField3.getText();
            vPrecio = Float.parseFloat(jTextField4.getText());
            cat = jTextField5.getText();
            vCategoria = getCodigoCateg(cat);
            mar = jTextField6.getText();
            vMarca = getCodigoMarca(mar);
            vExistencias = Integer.parseInt(jTextField7.getText());
            //REALIZA UPDATE EN LA BASE DE DATOS
            String url = "jdbc:mysql://localhost:3306/clothy";
            String user = "root";
            String pass = "";
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement st = connection.createStatement();
            String query = "update articulos set nombre='" + vNombre + "', descripcion='" + vDescripcion + "', precio=" + vPrecio + ", categoria=" + vCategoria + ", marca=" + vMarca + ", existencias=" + vExistencias + " WHERE id=" + vId;
            st.executeUpdate(query);            
            //REALIZA UPDATE EN LA TABLA
            if(jTFBuscar.getText().isEmpty()){
            int i = jTable1.getSelectedRow();
            model.setValueAt(jTextField2.getText(), i, 1);
            model.setValueAt(jTextField3.getText(), i, 2);
            model.setValueAt(jTextField4.getText(), i, 3);
            model.setValueAt(jTextField5.getText(), i, 4);
            model.setValueAt(jTextField6.getText(), i, 5);
            model.setValueAt(jTextField7.getText(), i, 6);
            }else{
                int i = modelRow;
                model.setValueAt(jTextField2.getText(), i, 1);
                model.setValueAt(jTextField3.getText(), i, 2);
                model.setValueAt(jTextField4.getText(), i, 3);
                model.setValueAt(jTextField5.getText(), i, 4);
                model.setValueAt(jTextField6.getText(), i, 5);
                model.setValueAt(jTextField7.getText(), i, 6);
            }

            } catch (SQLException ex) {
                Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
            }    
            
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //BOTÓN CANCELAR
        jTFBuscar.setEnabled(true); 
        jCBCat.setVisible(false);
        jCBMar.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        jTextField5.setVisible(true);
        jTextField6.setVisible(true);
        jButton5.setEnabled(true);
        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField7.setText("");
        
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //BOTÓN ACEPTAR
       try {            
            String vNombre, vDescripcion, cat, mar;
            int vId, vMarca, vExistencias, vCategoria;
            float vPrecio;            
            
            vNombre = jTextField2.getText();
            vDescripcion = jTextField3.getText();
            vPrecio = Float.parseFloat(jTextField4.getText());
            cat = (String) jCBCat.getSelectedItem();
            vCategoria = getCodigoCateg(cat);
            mar = (String) jCBMar.getSelectedItem();
            vMarca = getCodigoMarca(mar);
            vExistencias = Integer.parseInt(jTextField7.getText());             
            //INSERT EN LA BASE DE DATOS               
            PreparedStatement ps = conex.prepareStatement("INSERT INTO articulos (nombre, descripcion, precio, categoria, marca, existencias) VALUES (?,?,?,?,?,?)");
            ps.setString(1, vNombre);
            ps.setString(2, vDescripcion);
            ps.setFloat(3, vPrecio);
            ps.setInt(4, vCategoria);
            ps.setInt(5, vMarca);
            ps.setInt(6, vExistencias);
            ps.executeUpdate();           
            //INSERT EN LA TABLA      
            vId = (array_articulos.get(array_articulos.size()-1).getId()+1);
            model.addRow(new Object[]{vId, vNombre,vDescripcion,vPrecio,cat,mar,vExistencias});
            //INSERT EN EL ARRAY            
            array_articulos.add(new Articulo(vId, vNombre, vDescripcion, vPrecio, vCategoria, vMarca, vExistencias));
            
            jTFBuscar.setEnabled(true); 
            jButton5.setEnabled(true);
            jButton6.setEnabled(true);
            jButton7.setEnabled(true);
            jButton8.setVisible(false);
            jButton9.setVisible(false);
            jCBCat.setVisible(false);
            jCBMar.setVisible(false);
            jTextField5.setVisible(true);
            jTextField6.setVisible(true);
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField7.setText("");
            

            //estadoInicial();           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        //BOTÓN BORRAR
        int i = 0;
        if (jTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún artículo", "Error Delete", JOptionPane.ERROR_MESSAGE);
        }else{
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea borrar el artículo seleccionado?", "Confirmar acción", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            //REALIZA DELETE EN LA TABLA, ELIMINA UNA FILA SELECCIONADA            
            if (jTFBuscar.getText().isEmpty()) {
                i = jTable1.getSelectedRow();
                if (i >= 0) {
                    model.removeRow(i);
                } else {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado un artículo", "Error Delete", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                i = modelRow;
                model.removeRow(i);
            }

            try {
                //GUARDAMOS EN UNA VARIABLE EL ID QUE HAY QUE ELIMINAR DE LA BBDD, 'i' REPRESENTA LA FILA SELECCIONADA DE LA TABLA
                // 'i' PUEDE REPRESENTAR EL MODELO O LA VISTA DE LA TABLA, DEPENDIENDO SI ES CUADRO DE BUSCAR ESTÁ VACIO O NO...
                int vId = Integer.parseInt(jTextField1.getText());                
                //REALIZA DELETE EN LA BASE DE DATOS
                String url = "jdbc:mysql://localhost:3306/clothy";
                String user = "root";
                String pass = "";
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement st = connection.createStatement();
                String query = "DELETE FROM articulos WHERE id=" + vId;
                st.executeUpdate(query);

                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField7.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

            if (resp == JOptionPane.NO_OPTION) {
                //NO HACER NADA
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTFBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBuscarKeyReleased
        //jTextField BÚSQUEDA
        String query = jTFBuscar.getText().toLowerCase();
        
        filtro(query);
    }//GEN-LAST:event_jTFBuscarKeyReleased

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        //BOTÓN X, CERRAR VENTANA INDIVIDUAL
        this.dispose();
    }//GEN-LAST:event_jBCerrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {  //MÉTODO MAIN
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
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Articulos().setVisible(true);
                    //this.articulos.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
           
    //MÉTODO QUE DEVUELVE EL NOMBRE DE LA TALLA, PASANDO POR PARÁMETRO SU INT
    public String getNombreCateg(int num) {
        String categ = "";
        
        try {            
            Statement s3 = conex.createStatement(); 
            String queryNombre = "SELECT nombre from categorias WHERE id=" + num;
            ResultSet r3 = s3.executeQuery(queryNombre);
            r3.first();
            categ = r3.getString("nombre");           
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categ;              
    }
    
    //MÉTODO QUE DEVUELVE EL NÚMERO  DE LA MARCA, PASANDO POR PARÁMETRO SU NOMBRE, (MÉTODO PARA INSERT)
    public int getCodigoCateg(String nom) {
        int codCateg = 0;
        
        try {                        
            Statement s3 = conex.createStatement(); 
            String queryNombre = "SELECT id from categorias WHERE nombre='" + nom +"'";
            ResultSet r3 = s3.executeQuery(queryNombre);
            r3.first();
            codCateg = r3.getInt("id");           
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return codCateg;
    }
    
     //MÉTODO QUE DEVUELVE EL NOMBRE DE LA MARCA, PASANDO POR PARÁMETRO SU INT
    public  String getNombreMarca(int num) {
        String marca = "";
        
       try {           
            Statement s3 = conex.createStatement(); 
            String queryNombre = "SELECT nombre from marcas WHERE id=" + num;
            ResultSet r3 = s3.executeQuery(queryNombre);
            r3.first();
            marca = r3.getString("nombre");           
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return marca;
    }
    
    //MÉTODO QUE DEVUELVE EL NÚMERO  DE LA MARCA, PASANDO POR PARÁMETRO SU NOMBRE, (MÉTODO PARA INSERT)
    public int getCodigoMarca(String nom) {
        int codMarca = 0;
        
        try {                       
            Statement s3 = conex.createStatement(); 
            String queryNombre = "SELECT id from marcas WHERE nombre='" + nom +"'";
            ResultSet r3 = s3.executeQuery(queryNombre);
            r3.first();
            codMarca = r3.getInt("id");           
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }         
   
        return codMarca;
    }
    
    public void añadirFilasTabla(){ 
        Object datosFila []= new Object [7]; //EL RANGO DEL ARRAY REPRESENTA LAS COLUMNAS DE LA TABLA, EN ESTE CASO 7
        
        for (int i = 0; i < array_articulos.size(); i++) {
            datosFila[0] = array_articulos.get(i).getId();
            datosFila[1] = array_articulos.get(i).getNombre();
            datosFila[2] = array_articulos.get(i).getDescripcion();
            datosFila[3] = array_articulos.get(i).getPrecio();             
            datosFila[4] = getNombreCateg(array_articulos.get(i).getCategoria());
            datosFila[5] = getNombreMarca(array_articulos.get(i).getMarca());
            datosFila[6] = array_articulos.get(i).getExistencias();

            model.addRow(datosFila);
        }
    }
    
    public void llenarCategorias() throws SQLException {
        
        String query2 = "SELECT * FROM categorias";                
        Statement s2 = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet r2 = s2.executeQuery(query2);
        DefaultComboBoxModel value1 = new DefaultComboBoxModel();
        
        while (r2.next()) {
            value1.addElement(r2.getString("nombre"));             
        }
        jCBCat.setModel(value1);            
    }
        
    public void llenarMarcas() throws SQLException{
        
        String query3 = "SELECT * FROM marcas";
        Statement s3 = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet r3 = s3.executeQuery(query3);
        DefaultComboBoxModel value2 = new DefaultComboBoxModel();
        
        while (r3.next()) {           
            value2.addElement(r3.getString("nombre"));           
        }

        jCBMar.setModel(value2);            
    }
    
    //MÉTODO FILTRAR RESULTADOS
    private void filtro(String query){
        
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        jTable1.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        
        jTable1.getSelectionModel().addListSelectionListener(
        new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                 viewRow = jTable1.getSelectedRow();
                if (viewRow < 0) {
                    //Selection got filtered away.
                    statusText.setText("");
                } else {
                    modelRow = 
                        jTable1.convertRowIndexToModel(viewRow);
                    statusText.setText(
                        String.format("Selected Row in view: %d. " +
                            "Selected Row in model: %d.", 
                            viewRow, modelRow));
                }
            }            
        }
);    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCBCat;
    private javax.swing.JComboBox<String> jCBMar;
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
    private javax.swing.JTextField jTFBuscar;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField statusText;
    // End of variables declaration//GEN-END:variables
}
