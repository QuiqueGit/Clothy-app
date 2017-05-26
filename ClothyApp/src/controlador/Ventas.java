package controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import modelo.Venta;
import modelo.VentaArticulo;
import modelo.VentaLineaVenta;
import utilidades.ConexionDB;
import vista.Login;

/**
 *
 * @author Quique
 */
public class Ventas extends javax.swing.JFrame {
    
    static ArrayList<Venta> array_ventas = new ArrayList<>(); //CREO UN ARRAYLIST DE TIPO venta PARA ALMACENAR TODAS LAS VENTAS
    ArrayList<VentaArticulo> array_stockTotal = new ArrayList<>(); //ARRAYLIST PARA ALMACENAR TODOS LOS OBJETOS EN STOCK, INCLUIDA TALLA
    ArrayList<VentaLineaVenta> array_lineasVenta = new ArrayList<>();
    Connection conex = new ConexionDB().getCon(); //OBTENGO LA CONEXION QUE CREO EN OTRO PAQUETE 
    DefaultTableModel model;
    DefaultTableModel model2;   
    Statement s;
    ResultSet rs;  
    
    int viewRow, modelRow;

    /**
     * Creates new form Ventas
     */
    public Ventas() throws SQLException, ClassNotFoundException {
        
        initComponents();
        this.setLocationRelativeTo(null);
        //array_ventas.clear();//BORRAR TODO DEL ARRAY PARA CUANDO CIERRE ARTICULOS Y VUELVA A ABRIRLO NO SE DUPLIQUEN LOS DATOS
        model = (DefaultTableModel) jTable1.getModel();
        model2 = (DefaultTableModel) jTable2.getModel();
        //SETEA EL ANCHO DE LAS COLUMNAS TABLA 1     
        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(55);
        columnModel.getColumn(1).setPreferredWidth(60);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(120);
        //SETEA EL ANCHO DE LAS COLUMNAS TABLA 2
        TableColumnModel columnModel2 = jTable2.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(120);
        columnModel2.getColumn(1).setPreferredWidth(55);
        columnModel2.getColumn(2).setPreferredWidth(150);
        columnModel2.getColumn(3).setPreferredWidth(60);
        columnModel2.getColumn(5).setPreferredWidth(120);
        columnModel2.getColumn(6).setPreferredWidth(50);
       

        TFIdOculta.setVisible(false);
        TFEmpleado.setEnabled(false);
        statusText.setVisible(false);
        //statusText1.setVisible(false);
        jCBCli.setVisible(false);
        jCBTarjeta.setVisible(false);
        jCBEfectivo.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        Cantidad1.setVisible(false);
        Cantidad2.setVisible(false);
        Cantidad3.setVisible(false);
        
        CB3.setEnabled(false); //EL CHECKBOX DEL ARTICULO 3 EMPIEZA DISABLED Y SOLO CAMBIA A ENABLED SI EL ARTÍCULO 2 ESTÁ MARCADO
        
        //jTextFields artículos 2 y 3, más precios...
        TFArticulo1.setVisible(false);
        TFArticulo2.setVisible(false);
        TFTalla1.setVisible(false);
        TFTalla2.setVisible(false);
        TFPrecio1.setVisible(false);
        TFPrecio2.setVisible(false);
        TFStock1.setVisible(false);
        TFStock2.setVisible(false);
        TFNumLinea1.setVisible(false);
        TFNumLinea2.setVisible(false);
        //SEPARADORES ITEM 2
        jSeparator10.setVisible(false);
        jSeparator12.setVisible(false);
        jSeparator16.setVisible(false);
        jSeparator17.setVisible(false);
        jSeparator18.setVisible(false);
        jSeparator19.setVisible(false);         
        //SEPARADORES ITEM 3
        jSeparator20.setVisible(false);
        jSeparator21.setVisible(false);
        jSeparator22.setVisible(false);
        jSeparator23.setVisible(false);
        jSeparator24.setVisible(false);
        jSeparator25.setVisible(false);
        

        s = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM ventas";
        rs = s.executeQuery(query);

        while (rs.next()) { //CREO NUEVOS OBJETOS TIPO VENTA Y LLENO EL ARRAYLIST MIENTRAS TENGA RESULTSET
            int id = rs.getInt("id");
            int cliente = Integer.parseInt(rs.getString("cliente"));
            int empleado = Integer.parseInt(rs.getString("empleado"));
            String fecha = rs.getString("fecha");
            String metodo_pago = rs.getString("metodo_pago");

            array_ventas.add(new Venta(id, cliente, empleado, fecha, metodo_pago));
        }

        llenarClientes();  //MÉTODO PARA LLENAR COMBOBOX DE CLIENTES
        
       
        Statement s5;
        s5 = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query5 = "SELECT * FROM ventas_lineasVenta";
        ResultSet r5 = s5.executeQuery(query5);

        while (r5.next()) { //CREO NUEVOS OBJETOS TIPO VENTA Y LLENO EL ARRAYLIST MIENTRAS TENGA RESULTSET
            int id_venta = r5.getInt("ID_Venta");
            int num_linea = r5.getInt("Num_Linea");
            String cliente = r5.getString("Cliente");
            String emp = r5.getString("Empleado");
            String fecha = r5.getString("Fecha");
            String met_pago = r5.getString("Metodo_Pago");
            String artic = r5.getString("Articulo");
            int cant = r5.getInt("Cantidad");
            int imp = r5.getInt("Importe");

            array_lineasVenta.add(new VentaLineaVenta(id_venta, num_linea, cliente, emp, fecha, met_pago, artic, cant, imp));
        }
        
        añadirFilasTabla();
        

        jTable1.addMouseListener(new MouseAdapter() { //CREA UN LISTENER PARA EL RATÓN

            @Override
            public void mouseClicked(MouseEvent e) { //CUANDO SELECCIONAS UNA FILA DE LA TABLA, SETEA LOS jTextField PARA EDITARLOS              
                if (jTFBuscarVenta.getText().isEmpty()) {
                    int i = jTable1.getSelectedRow();
                    // 'i' HACE REFERENCIA AL NÚMERO DE LA FILA SELECCIONADA Y EL NÚMERO HACE REFERENCIA A LA COLUMNA (i,n)
                    TFIdOculta.setText(model.getValueAt(i, 0).toString());
                    TFCliente.setText(model.getValueAt(i, 1).toString());
                    TFEmpleado.setText(model.getValueAt(i, 2).toString());
                    TFFecha.setText(model.getValueAt(i, 3).toString());
                    TFPago.setText(model.getValueAt(i, 4).toString());

                } else {
                    int i = modelRow;
                    TFIdOculta.setText(model.getValueAt(i, 0).toString());
                    TFCliente.setText(model.getValueAt(i, 1).toString());
                    TFEmpleado.setText(model.getValueAt(i, 2).toString());
                    TFFecha.setText(model.getValueAt(i, 3).toString());
                    TFPago.setText(model.getValueAt(i, 4).toString());
                }
            }
        });
        
        Statement s2 = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query2 = "SELECT * FROM ventas_articulos";
        ResultSet r2 = s2.executeQuery(query2);

        while (r2.next()) { //CREO NUEVOS OBJETOS TIPO ARTICULO Y LLENO EL ARRAYLIST MIENTRAS TENGA RESULTSET
            String idArt = r2.getString("Articulo");
            String idTal = r2.getString("valor_talla");
            String desc = r2.getString("descripcion");
            float precio = r2.getFloat("precio");
            String cat = r2.getString("Categoria");
            String marca = r2.getString("Marca"); 
            int stock = r2.getInt("stock");
            
            array_stockTotal.add(new VentaArticulo(idArt, idTal, desc, precio, cat, marca, stock));
        }
        
        añadirFilasTabla2(); //TABLA2 VENTAS/ARTICULOS
        
        jTable2.addMouseListener(new MouseAdapter() { //CREA UN LISTENER PARA EL RATÓN

            @Override
            public void mouseClicked(MouseEvent e) { //CUANDO SELECCIONAS UNA FILA DE LA TABLA, SETEA LOS jTextField PARA EDITARLOS        

                if (CB3.isSelected()) {
                     TFNumLinea2.setText("3");
                     Cantidad3.setVisible(true);

                    if (jTFBuscarVentaArticulos.getText().isEmpty()) {
                        int i = jTable2.getSelectedRow();
                        // 'i' HACE REFERENCIA AL NÚMERO DE LA FILA SELECCIONADA Y EL NÚMERO HACE REFERENCIA A LA COLUMNA (i,n)
                        TFArticulo2.setText(model2.getValueAt(i, 0).toString());
                        TFTalla2.setText(model2.getValueAt(i, 1).toString());
                        TFPrecio2.setText(model2.getValueAt(i, 3).toString());
                        TFStock2.setText(model2.getValueAt(i, 6).toString());

                    } else {
                        int i = modelRow;
                        TFArticulo2.setText(model2.getValueAt(i, 0).toString());
                        TFTalla2.setText(model2.getValueAt(i, 1).toString());
                        TFPrecio2.setText(model2.getValueAt(i, 3).toString());
                        TFStock2.setText(model2.getValueAt(i, 6).toString());
                    }
                    //RELLENAR COMBOBOX DE CANTIDADES EN FUNCIÓN DEL SOTCK QUE LE QUEDE...
                    for(int index = 0; index < Integer.parseInt(TFStock2.getText()); index ++) {
                        Cantidad3.addItem(String.valueOf(index + 1));
                    }

                } else if (CB2.isSelected()) {
                    TFNumLinea1.setText("2");
                    Cantidad2.setVisible(true);

                    if (jTFBuscarVentaArticulos.getText().isEmpty()) {
                        int i = jTable2.getSelectedRow();
                        // 'i' HACE REFERENCIA AL NÚMERO DE LA FILA SELECCIONADA Y EL NÚMERO HACE REFERENCIA A LA COLUMNA (i,n)
                        TFArticulo1.setText(model2.getValueAt(i, 0).toString());
                        TFTalla1.setText(model2.getValueAt(i, 1).toString());
                        TFPrecio1.setText(model2.getValueAt(i, 3).toString());
                        TFStock1.setText(model2.getValueAt(i, 6).toString());
                    } else {
                        
                        int i = modelRow;
                        TFArticulo1.setText(model2.getValueAt(i, 0).toString());
                        TFTalla1.setText(model2.getValueAt(i, 1).toString());
                        TFPrecio1.setText(model2.getValueAt(i, 3).toString());
                        TFStock1.setText(model2.getValueAt(i, 6).toString());

                    }
                    for(int index = 0; index < Integer.parseInt(TFStock1.getText()); index ++) {
                        Cantidad2.addItem(String.valueOf(index + 1));
                    }
                } else if (jTFBuscarVentaArticulos.getText().isEmpty()) {
                    TFNumLinea.setText("1");
                    Cantidad1.setVisible(true);
                    Cantidad1.removeAllItems();
                    
                    int i = jTable2.getSelectedRow();
                    // 'i' HACE REFERENCIA AL NÚMERO DE LA FILA SELECCIONADA Y EL NÚMERO HACE REFERENCIA A LA COLUMNA (i,n)
                    TFArticulo.setText(model2.getValueAt(i, 0).toString());
                    TFTalla.setText(model2.getValueAt(i, 1).toString());
                    TFPrecio.setText(model2.getValueAt(i, 3).toString());
                    TFStock.setText(model2.getValueAt(i, 6).toString());    
                } else {
                    TFNumLinea.setText("1");
                    Cantidad1.setVisible(true);
                    int i = modelRow;
                    TFArticulo.setText(model2.getValueAt(i, 0).toString());
                    TFTalla.setText(model2.getValueAt(i, 1).toString());
                    TFPrecio.setText(model2.getValueAt(i, 3).toString());
                    TFStock.setText(model2.getValueAt(i, 6).toString());
                }
                //RELLENAR COMBOBOX DE CANTIDADES EN FUNCIÓN DEL SOTCK QUE LE QUEDE...
                    for(int index = 0; index < Integer.parseInt(TFStock.getText()); index ++) {
                        Cantidad1.addItem(String.valueOf(index + 1));
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
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TFIdOculta = new javax.swing.JTextField();
        TFCliente = new javax.swing.JTextField();
        TFEmpleado = new javax.swing.JTextField();
        TFFecha = new javax.swing.JTextField();
        TFPago = new javax.swing.JTextField();
        TFArticulo = new javax.swing.JTextField();
        TFTalla = new javax.swing.JTextField();
        TFPrecio = new javax.swing.JTextField();
        TFStock = new javax.swing.JTextField();
        TFNumLinea = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jTFBuscarVenta = new javax.swing.JTextField();
        jTFBuscarVentaArticulos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        statusText = new javax.swing.JTextField();
        statusText1 = new javax.swing.JTextField();
        jCBTarjeta = new javax.swing.JCheckBox();
        jCBEfectivo = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jCBCli = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        TFArticulo1 = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        TFTalla1 = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        TFPrecio1 = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        TFStock1 = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        TFNumLinea1 = new javax.swing.JTextField();
        jSeparator19 = new javax.swing.JSeparator();
        TFArticulo2 = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        TFTalla2 = new javax.swing.JTextField();
        jSeparator23 = new javax.swing.JSeparator();
        TFPrecio2 = new javax.swing.JTextField();
        jSeparator20 = new javax.swing.JSeparator();
        TFStock2 = new javax.swing.JTextField();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        TFNumLinea2 = new javax.swing.JTextField();
        jSeparator24 = new javax.swing.JSeparator();
        Precio1 = new javax.swing.JTextField();
        precio_total = new javax.swing.JTextField();
        CB2 = new javax.swing.JCheckBox();
        CB3 = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        precio2 = new javax.swing.JLabel();
        Precio2 = new javax.swing.JTextField();
        Precio3 = new javax.swing.JTextField();
        precio3 = new javax.swing.JLabel();
        Cantidad1 = new javax.swing.JComboBox<>();
        Cantidad2 = new javax.swing.JComboBox<>();
        Cantidad3 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1200, 715));
        setMinimumSize(new java.awt.Dimension(1200, 715));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1200, 715));
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
        jBCerrar.setBounds(1180, 0, 20, 20);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cliente");
        jLabel2.setFocusable(false);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(100, 130, 55, 22);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Empleado");
        jLabel3.setFocusable(false);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(100, 170, 78, 22);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha");
        jLabel4.setFocusable(false);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(100, 210, 45, 22);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Método pago");
        jLabel5.setFocusable(false);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(100, 250, 127, 22);

        jLabel8.setFont(new java.awt.Font("Magneto", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ventas");
        jLabel8.setFocusable(false);
        getContentPane().add(jLabel8);
        jLabel8.setBounds(190, 30, 140, 30);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/buscar_iconx24.png"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(510, 30, 40, 40);

        TFIdOculta.setBackground(new java.awt.Color(0, 0, 0));
        TFIdOculta.setEnabled(false);
        TFIdOculta.setFocusable(false);
        TFIdOculta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFIdOcultaActionPerformed(evt);
            }
        });
        getContentPane().add(TFIdOculta);
        TFIdOculta.setBounds(100, 80, 20, 24);

        TFCliente.setBackground(new java.awt.Color(204, 204, 204));
        TFCliente.setForeground(new java.awt.Color(255, 255, 255));
        TFCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFCliente.setBorder(null);
        TFCliente.setCaretColor(new java.awt.Color(255, 255, 255));
        TFCliente.setOpaque(false);
        TFCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFClienteActionPerformed(evt);
            }
        });
        getContentPane().add(TFCliente);
        TFCliente.setBounds(210, 130, 142, 20);

        TFEmpleado.setBackground(new java.awt.Color(204, 204, 204));
        TFEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        TFEmpleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFEmpleado.setBorder(null);
        TFEmpleado.setCaretColor(new java.awt.Color(255, 255, 255));
        TFEmpleado.setOpaque(false);
        getContentPane().add(TFEmpleado);
        TFEmpleado.setBounds(210, 170, 142, 20);

        TFFecha.setBackground(new java.awt.Color(204, 204, 204));
        TFFecha.setForeground(new java.awt.Color(255, 255, 255));
        TFFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFFecha.setBorder(null);
        TFFecha.setCaretColor(new java.awt.Color(255, 255, 255));
        TFFecha.setOpaque(false);
        getContentPane().add(TFFecha);
        TFFecha.setBounds(210, 210, 142, 20);

        TFPago.setBackground(new java.awt.Color(204, 204, 204));
        TFPago.setForeground(new java.awt.Color(255, 255, 255));
        TFPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFPago.setBorder(null);
        TFPago.setCaretColor(new java.awt.Color(255, 255, 255));
        TFPago.setOpaque(false);
        getContentPane().add(TFPago);
        TFPago.setBounds(210, 250, 142, 20);

        TFArticulo.setBackground(new java.awt.Color(204, 204, 204));
        TFArticulo.setForeground(new java.awt.Color(255, 255, 255));
        TFArticulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFArticulo.setBorder(null);
        TFArticulo.setCaretColor(new java.awt.Color(255, 255, 255));
        TFArticulo.setOpaque(false);
        getContentPane().add(TFArticulo);
        TFArticulo.setBounds(90, 330, 110, 20);

        TFTalla.setBackground(new java.awt.Color(204, 204, 204));
        TFTalla.setForeground(new java.awt.Color(255, 255, 255));
        TFTalla.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFTalla.setBorder(null);
        TFTalla.setCaretColor(new java.awt.Color(255, 255, 255));
        TFTalla.setOpaque(false);
        getContentPane().add(TFTalla);
        TFTalla.setBounds(110, 370, 60, 20);

        TFPrecio.setBackground(new java.awt.Color(204, 204, 204));
        TFPrecio.setForeground(new java.awt.Color(255, 255, 255));
        TFPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFPrecio.setBorder(null);
        TFPrecio.setCaretColor(new java.awt.Color(255, 255, 255));
        TFPrecio.setOpaque(false);
        getContentPane().add(TFPrecio);
        TFPrecio.setBounds(110, 410, 60, 20);

        TFStock.setBackground(new java.awt.Color(204, 204, 204));
        TFStock.setForeground(new java.awt.Color(255, 255, 255));
        TFStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFStock.setBorder(null);
        TFStock.setCaretColor(new java.awt.Color(255, 255, 255));
        TFStock.setOpaque(false);
        getContentPane().add(TFStock);
        TFStock.setBounds(110, 450, 60, 20);

        TFNumLinea.setBackground(new java.awt.Color(204, 204, 204));
        TFNumLinea.setForeground(new java.awt.Color(255, 255, 255));
        TFNumLinea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFNumLinea.setBorder(null);
        TFNumLinea.setCaretColor(new java.awt.Color(255, 255, 255));
        TFNumLinea.setOpaque(false);
        getContentPane().add(TFNumLinea);
        TFNumLinea.setBounds(110, 530, 60, 20);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nº Linea");
        jLabel16.setFocusable(false);
        getContentPane().add(jLabel16);
        jLabel16.setBounds(20, 530, 90, 22);
        getContentPane().add(jSeparator15);
        jSeparator15.setBounds(20, 550, 170, 10);

        jTFBuscarVenta.setBackground(new java.awt.Color(204, 204, 204));
        jTFBuscarVenta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTFBuscarVenta.setForeground(new java.awt.Color(255, 255, 255));
        jTFBuscarVenta.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTFBuscarVenta.setBorder(null);
        jTFBuscarVenta.setCaretColor(new java.awt.Color(255, 255, 255));
        jTFBuscarVenta.setName("Buscar"); // NOI18N
        jTFBuscarVenta.setOpaque(false);
        jTFBuscarVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFBuscarVentaKeyReleased(evt);
            }
        });
        getContentPane().add(jTFBuscarVenta);
        jTFBuscarVenta.setBounds(540, 40, 120, 19);

        jTFBuscarVentaArticulos.setBackground(new java.awt.Color(204, 204, 204));
        jTFBuscarVentaArticulos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTFBuscarVentaArticulos.setForeground(new java.awt.Color(255, 255, 255));
        jTFBuscarVentaArticulos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTFBuscarVentaArticulos.setBorder(null);
        jTFBuscarVentaArticulos.setCaretColor(new java.awt.Color(255, 255, 255));
        jTFBuscarVentaArticulos.setName("Buscar"); // NOI18N
        jTFBuscarVentaArticulos.setOpaque(false);
        jTFBuscarVentaArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFBuscarVentaArticulosKeyReleased(evt);
            }
        });
        getContentPane().add(jTFBuscarVentaArticulos);
        jTFBuscarVentaArticulos.setBounds(540, 360, 120, 19);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Artículo");
        jLabel6.setFocusable(false);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 330, 80, 22);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(20, 350, 170, 10);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Talla");
        jLabel7.setFocusable(false);
        getContentPane().add(jLabel7);
        jLabel7.setBounds(20, 370, 50, 22);
        getContentPane().add(jSeparator9);
        jSeparator9.setBounds(20, 390, 170, 10);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Precio");
        jLabel12.setFocusable(false);
        getContentPane().add(jLabel12);
        jLabel12.setBounds(20, 410, 80, 22);
        getContentPane().add(jSeparator11);
        jSeparator11.setBounds(20, 430, 170, 10);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Stock");
        jLabel14.setFocusable(false);
        getContentPane().add(jLabel14);
        jLabel14.setBounds(20, 450, 60, 22);
        getContentPane().add(jSeparator13);
        jSeparator13.setBounds(20, 470, 170, 10);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Cantidad");
        jLabel15.setFocusable(false);
        getContentPane().add(jLabel15);
        jLabel15.setBounds(20, 490, 80, 22);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Lineas de venta");
        jLabel11.setFocusable(false);
        getContentPane().add(jLabel11);
        jLabel11.setBounds(670, 30, 140, 40);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Stock en tienda");
        jLabel13.setFocusable(false);
        getContentPane().add(jLabel13);
        jLabel13.setBounds(670, 350, 140, 40);
        getContentPane().add(jSeparator14);
        jSeparator14.setBounds(20, 510, 170, 10);

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/new_button.png"))); // NOI18N
        jButton5.setText("Nueva Venta");
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setFocusable(false);
        jButton5.setMargin(new java.awt.Insets(5, 14, 5, 14));
        jButton5.setPreferredSize(new java.awt.Dimension(77, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(180, 630, 140, 30);

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
        jButton8.setBounds(130, 670, 120, 30);

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
        jButton9.setBounds(240, 670, 120, 30);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(100, 150, 270, 10);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(100, 190, 270, 10);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(100, 230, 270, 10);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(100, 270, 270, 10);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(540, 60, 120, 10);
        getContentPane().add(statusText);
        statusText.setBounds(480, 260, 20, 24);
        getContentPane().add(statusText1);
        statusText1.setBounds(460, 680, 410, 24);

        jCBTarjeta.setForeground(new java.awt.Color(255, 255, 255));
        jCBTarjeta.setSelected(true);
        jCBTarjeta.setText("Tarjeta");
        jCBTarjeta.setBorder(null);
        jCBTarjeta.setContentAreaFilled(false);
        jCBTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBTarjetaActionPerformed(evt);
            }
        });
        getContentPane().add(jCBTarjeta);
        jCBTarjeta.setBounds(230, 250, 70, 20);

        jCBEfectivo.setForeground(new java.awt.Color(255, 255, 255));
        jCBEfectivo.setText("Efectivo");
        jCBEfectivo.setContentAreaFilled(false);
        jCBEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBEfectivoActionPerformed(evt);
            }
        });
        getContentPane().add(jCBEfectivo);
        jCBEfectivo.setBounds(300, 250, 80, 20);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/buscar_iconx24.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(510, 350, 40, 40);

        jCBCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jCBCli);
        jCBCli.setBounds(210, 130, 160, 20);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("1");
        jLabel17.setFocusable(false);
        getContentPane().add(jLabel17);
        jLabel17.setBounds(140, 300, 10, 20);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("2");
        jLabel18.setFocusable(false);
        getContentPane().add(jLabel18);
        jLabel18.setBounds(270, 300, 10, 20);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("3");
        jLabel19.setFocusable(false);
        getContentPane().add(jLabel19);
        jLabel19.setBounds(400, 300, 10, 20);
        getContentPane().add(jSeparator8);
        jSeparator8.setBounds(540, 380, 120, 10);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Venta", "NºLinea", "Cliente", "Empleado", "Fecha", "Método_Pago", "Artículo", "Cantidad", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
        jScrollPane1.setBounds(510, 70, 620, 260);

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setForeground(new java.awt.Color(0, 0, 0));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Artículo", "Talla", "Descripción", "Precio", "Categoria", "Marca", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setAutoscrolls(false);
        jTable2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable2.setFocusable(false);
        jTable2.setGridColor(new java.awt.Color(0, 0, 0));
        jTable2.setMaximumSize(new java.awt.Dimension(520, 0));
        jTable2.setOpaque(false);
        jTable2.setRowHeight(30);
        jTable2.setRowMargin(10);
        jTable2.setSelectionForeground(new java.awt.Color(51, 51, 51));
        jTable2.setShowVerticalLines(false);
        jTable2.setUpdateSelectionOnSort(false);
        jScrollPane2.setViewportView(jTable2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(510, 390, 620, 280);

        TFArticulo1.setBackground(new java.awt.Color(204, 204, 204));
        TFArticulo1.setForeground(new java.awt.Color(255, 255, 255));
        TFArticulo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFArticulo1.setBorder(null);
        TFArticulo1.setCaretColor(new java.awt.Color(255, 255, 255));
        TFArticulo1.setOpaque(false);
        getContentPane().add(TFArticulo1);
        TFArticulo1.setBounds(230, 330, 100, 20);
        getContentPane().add(jSeparator10);
        jSeparator10.setBounds(220, 350, 120, 10);

        TFTalla1.setBackground(new java.awt.Color(204, 204, 204));
        TFTalla1.setForeground(new java.awt.Color(255, 255, 255));
        TFTalla1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFTalla1.setBorder(null);
        TFTalla1.setCaretColor(new java.awt.Color(255, 255, 255));
        TFTalla1.setOpaque(false);
        getContentPane().add(TFTalla1);
        TFTalla1.setBounds(250, 370, 60, 20);
        getContentPane().add(jSeparator12);
        jSeparator12.setBounds(240, 390, 80, 10);

        TFPrecio1.setBackground(new java.awt.Color(204, 204, 204));
        TFPrecio1.setForeground(new java.awt.Color(255, 255, 255));
        TFPrecio1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFPrecio1.setBorder(null);
        TFPrecio1.setCaretColor(new java.awt.Color(255, 255, 255));
        TFPrecio1.setOpaque(false);
        getContentPane().add(TFPrecio1);
        TFPrecio1.setBounds(250, 410, 60, 20);
        getContentPane().add(jSeparator16);
        jSeparator16.setBounds(240, 430, 80, 10);

        TFStock1.setBackground(new java.awt.Color(204, 204, 204));
        TFStock1.setForeground(new java.awt.Color(255, 255, 255));
        TFStock1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFStock1.setBorder(null);
        TFStock1.setCaretColor(new java.awt.Color(255, 255, 255));
        TFStock1.setOpaque(false);
        getContentPane().add(TFStock1);
        TFStock1.setBounds(250, 450, 60, 20);
        getContentPane().add(jSeparator17);
        jSeparator17.setBounds(240, 470, 80, 10);
        getContentPane().add(jSeparator18);
        jSeparator18.setBounds(240, 510, 80, 10);

        TFNumLinea1.setBackground(new java.awt.Color(204, 204, 204));
        TFNumLinea1.setForeground(new java.awt.Color(255, 255, 255));
        TFNumLinea1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFNumLinea1.setBorder(null);
        TFNumLinea1.setCaretColor(new java.awt.Color(255, 255, 255));
        TFNumLinea1.setOpaque(false);
        getContentPane().add(TFNumLinea1);
        TFNumLinea1.setBounds(250, 530, 60, 20);
        getContentPane().add(jSeparator19);
        jSeparator19.setBounds(240, 550, 80, 10);

        TFArticulo2.setBackground(new java.awt.Color(204, 204, 204));
        TFArticulo2.setForeground(new java.awt.Color(255, 255, 255));
        TFArticulo2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFArticulo2.setBorder(null);
        TFArticulo2.setCaretColor(new java.awt.Color(255, 255, 255));
        TFArticulo2.setOpaque(false);
        getContentPane().add(TFArticulo2);
        TFArticulo2.setBounds(360, 330, 100, 20);
        getContentPane().add(jSeparator21);
        jSeparator21.setBounds(350, 350, 120, 10);

        TFTalla2.setBackground(new java.awt.Color(204, 204, 204));
        TFTalla2.setForeground(new java.awt.Color(255, 255, 255));
        TFTalla2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFTalla2.setBorder(null);
        TFTalla2.setCaretColor(new java.awt.Color(255, 255, 255));
        TFTalla2.setOpaque(false);
        getContentPane().add(TFTalla2);
        TFTalla2.setBounds(380, 370, 60, 20);
        getContentPane().add(jSeparator23);
        jSeparator23.setBounds(370, 390, 80, 10);

        TFPrecio2.setBackground(new java.awt.Color(204, 204, 204));
        TFPrecio2.setForeground(new java.awt.Color(255, 255, 255));
        TFPrecio2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFPrecio2.setBorder(null);
        TFPrecio2.setCaretColor(new java.awt.Color(255, 255, 255));
        TFPrecio2.setOpaque(false);
        getContentPane().add(TFPrecio2);
        TFPrecio2.setBounds(380, 410, 60, 20);
        getContentPane().add(jSeparator20);
        jSeparator20.setBounds(370, 430, 80, 10);

        TFStock2.setBackground(new java.awt.Color(204, 204, 204));
        TFStock2.setForeground(new java.awt.Color(255, 255, 255));
        TFStock2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFStock2.setBorder(null);
        TFStock2.setCaretColor(new java.awt.Color(255, 255, 255));
        TFStock2.setOpaque(false);
        getContentPane().add(TFStock2);
        TFStock2.setBounds(380, 450, 60, 20);
        getContentPane().add(jSeparator22);
        jSeparator22.setBounds(370, 470, 80, 10);
        getContentPane().add(jSeparator25);
        jSeparator25.setBounds(370, 510, 80, 10);

        TFNumLinea2.setBackground(new java.awt.Color(204, 204, 204));
        TFNumLinea2.setForeground(new java.awt.Color(255, 255, 255));
        TFNumLinea2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFNumLinea2.setBorder(null);
        TFNumLinea2.setCaretColor(new java.awt.Color(255, 255, 255));
        TFNumLinea2.setOpaque(false);
        getContentPane().add(TFNumLinea2);
        TFNumLinea2.setBounds(380, 530, 60, 20);
        getContentPane().add(jSeparator24);
        jSeparator24.setBounds(370, 550, 80, 10);

        Precio1.setBackground(new java.awt.Color(204, 204, 204));
        Precio1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Precio1.setForeground(new java.awt.Color(255, 255, 255));
        Precio1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Precio1.setBorder(null);
        Precio1.setCaretColor(new java.awt.Color(255, 255, 255));
        Precio1.setOpaque(false);
        getContentPane().add(Precio1);
        Precio1.setBounds(90, 560, 80, 20);

        precio_total.setBackground(new java.awt.Color(204, 204, 204));
        precio_total.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        precio_total.setForeground(new java.awt.Color(255, 255, 255));
        precio_total.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        precio_total.setBorder(null);
        precio_total.setCaretColor(new java.awt.Color(255, 255, 255));
        precio_total.setOpaque(false);
        getContentPane().add(precio_total);
        precio_total.setBounds(110, 600, 120, 20);

        CB2.setBorder(null);
        CB2.setContentAreaFilled(false);
        CB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB2ActionPerformed(evt);
            }
        });
        getContentPane().add(CB2);
        CB2.setBounds(280, 300, 20, 20);

        CB3.setBorder(null);
        CB3.setContentAreaFilled(false);
        CB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB3ActionPerformed(evt);
            }
        });
        getContentPane().add(CB3);
        CB3.setBounds(410, 300, 20, 20);

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("TOTAL");
        getContentPane().add(jLabel23);
        jLabel23.setBounds(20, 600, 70, 21);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(20, 620, 430, 10);

        precio2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(precio2);
        precio2.setBounds(270, 560, 0, 0);

        Precio2.setBackground(new java.awt.Color(204, 204, 204));
        Precio2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Precio2.setForeground(new java.awt.Color(255, 255, 255));
        Precio2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Precio2.setBorder(null);
        Precio2.setCaretColor(new java.awt.Color(255, 255, 255));
        Precio2.setOpaque(false);
        getContentPane().add(Precio2);
        Precio2.setBounds(230, 560, 80, 20);

        Precio3.setBackground(new java.awt.Color(204, 204, 204));
        Precio3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Precio3.setForeground(new java.awt.Color(255, 255, 255));
        Precio3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Precio3.setBorder(null);
        Precio3.setCaretColor(new java.awt.Color(255, 255, 255));
        Precio3.setOpaque(false);
        getContentPane().add(Precio3);
        Precio3.setBounds(360, 560, 80, 20);

        precio3.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(precio3);
        precio3.setBounds(400, 560, 0, 0);

        Cantidad1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Cantidad1.setMaximumRowCount(5);
        Cantidad1.setBorder(null);
        Cantidad1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cantidad1ItemStateChanged(evt);
            }
        });
        getContentPane().add(Cantidad1);
        Cantidad1.setBounds(130, 480, 40, 31);

        Cantidad2.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Cantidad2.setMaximumRowCount(5);
        Cantidad2.setSelectedIndex(-1);
        Cantidad2.setBorder(null);
        Cantidad2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cantidad2ItemStateChanged(evt);
            }
        });
        getContentPane().add(Cantidad2);
        Cantidad2.setBounds(270, 480, 40, 31);

        Cantidad3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Cantidad3.setMaximumRowCount(5);
        Cantidad3.setSelectedIndex(-1);
        Cantidad3.setBorder(null);
        Cantidad3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Cantidad3ItemStateChanged(evt);
            }
        });
        getContentPane().add(Cantidad3);
        Cantidad3.setBounds(400, 480, 40, 31);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/tab_fondo2.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1200, 715));
        jLabel1.setMinimumSize(new java.awt.Dimension(1200, 715));
        jLabel1.setPreferredSize(new java.awt.Dimension(1200, 715));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1200, 715);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TFIdOcultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFIdOcultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFIdOcultaActionPerformed

    private void TFClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFClienteActionPerformed

    private void jTFBuscarVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBuscarVentaKeyReleased
        //jTextField BÚSQUEDA Ventas/Clientes
        String query = jTFBuscarVenta.getText().toLowerCase();

        filtro(query);
    }//GEN-LAST:event_jTFBuscarVentaKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //BOTÓN NUEVO
        jTFBuscarVenta.setText("");
        jTFBuscarVenta.setEnabled(false);
        TFIdOculta.setText("");
        TFCliente.setText("");
        TFCliente.setVisible(false);
        String e = Login.getUser_actual(); //LA VENTA LA REALIZA EL EMPLEADO QUE ESTÁ LOGEADO
        TFEmpleado.setText(e);
        TFFecha.setText("");        
        TFPago.setText("");
        TFPago.setVisible(false);
        jCBCli.setVisible(true);
        jCBTarjeta.setVisible(true);
        jCBTarjeta.setEnabled(false);
        jCBEfectivo.setVisible(true);
        jButton5.setEnabled(false);
        jButton8.setVisible(true);
        jButton9.setVisible(true);          
        
        Precio1.setVisible(true);
        Precio2.setVisible(true);
        Precio3.setVisible(true);
        precio_total.setVisible(true);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //BOTÓN ACEPTAR       
        String vFecha = null;
        vFecha = TFFecha.getText();               
        try {
            int vId = 0, vCli, vEmp;
            String vMetodo_pago = null ,cli;            
            
            if(TFIdOculta.getText().isEmpty()){
                vId=1;
            } else{
                vId = (array_ventas.get(array_ventas.size() - 1).getId() + 1);
            }           
            vCli = getCodigoCliente((String) jCBCli.getSelectedItem());
            cli = (String) jCBCli.getSelectedItem();
            vEmp = getCodigoEmpleado(TFEmpleado.getText()); 
            if(array_ventas.isEmpty()){
                vId = 1;                
            }else{
            vId = (array_ventas.get(array_ventas.size() - 1).getId() + 1); 
            }
            if (jCBTarjeta.isSelected()) {
                vMetodo_pago = "Tarjeta";                
            }else if(jCBEfectivo.isSelected()){
                vMetodo_pago = "Efectivo";                
            }else{
                JOptionPane.showMessageDialog(null, "Seleccione un método de pago", "Error", JOptionPane.ERROR_MESSAGE);
            }           
            //INSERT EN LA BASE DE DATOS
            PreparedStatement ps = conex.prepareStatement("INSERT INTO ventas (id, cliente, empleado, fecha, metodo_pago) VALUES (?,?,?,?,?)");
            ps.setInt(1, vId);
            ps.setInt(2, vCli);
            ps.setInt(3, vEmp);
            ps.setString(4, vFecha);
            ps.setString(5, vMetodo_pago);
            ps.executeUpdate();
            //ACTUALIZA LA TABLA 
            updateView();           
            
            //INSERT EN EL ARRAY
            array_ventas.add(new Venta(vId, vCli, vEmp, vFecha, vMetodo_pago));
            
            /*******************************************************************/            
            //INSERT DE LA VENTA COMPLETA, A LA TABLA linea_ventas
            
            //INSERT EN LA BASE DE DATOS
            int venta_id = vId;
            int num_linea = Integer.parseInt(TFNumLinea.getText());
            int articulo = getCodigoArticulo(TFArticulo.getText());
            int cantidad = Cantidad1.getSelectedIndex()+1;
            float precio = Float.parseFloat(TFPrecio.getText());
            float importe = cantidad * precio;          
            
            PreparedStatement ps2 = conex.prepareStatement("INSERT INTO lineas_ventas (venta_id, num_linea, articulo, cantidad, importe) VALUES (?,?,?,?,?)");
            ps2.setInt(1, venta_id);
            ps2.setInt(2, num_linea);
            ps2.setInt(3, articulo);
            ps2.setInt(4, cantidad);
            ps2.setFloat(5, importe);
            ps2.executeUpdate();            
            //INSERT PARA MÁS DE UN ARTICULO EN LA MISMA VENTA...
            
            //INSERTAR ARTÍCULO 2 SI EL CHECKBOX2 ESTÁ ACTIVO
            if (CB2.isSelected()) { 
                int num_linea1 = Integer.parseInt(TFNumLinea1.getText());
                int articulo1 = getCodigoArticulo(TFArticulo1.getText());
                int cantidad1 = Cantidad2.getSelectedIndex()+1;
                float precio1 = Float.parseFloat(TFPrecio1.getText());
                float importe1 = cantidad * precio1;

                PreparedStatement ps3 = conex.prepareStatement("INSERT INTO lineas_ventas (venta_id, num_linea, articulo, cantidad, importe) VALUES (?,?,?,?,?)");
                ps3.setInt(1, vId);
                ps3.setInt(2, num_linea1);
                ps3.setInt(3, articulo1);
                ps3.setInt(4, cantidad1);
                ps3.setFloat(5, importe1);
                ps3.executeUpdate();     
                
                System.out.println(num_linea1);
                System.out.println(articulo1);
                        
            }
            //INSERTAR ARTÍCULO 3 SI EL CHECKBOX3 ESTÁ ACTIVO
            if (CB3.isSelected()) { 
                int num_linea2 = Integer.parseInt(TFNumLinea2.getText());
                int articulo2 = getCodigoArticulo(TFArticulo2.getText());
                int cantidad2 = Cantidad3.getSelectedIndex()+1;
                float precio2 = Float.parseFloat(TFPrecio2.getText());
                float importe2 = cantidad * precio2;

                PreparedStatement ps4 = conex.prepareStatement("INSERT INTO lineas_ventas (venta_id, num_linea, articulo, cantidad, importe) VALUES (?,?,?,?,?)");

                ps4.setInt(1, vId);
                ps4.setInt(2, num_linea2);
                ps4.setInt(3, articulo2);
                ps4.setInt(4, cantidad2);
                ps4.setFloat(5, importe2);
                ps4.executeUpdate(); 
                
            }            
          

            jTFBuscarVenta.setEnabled(true);
            jButton5.setEnabled(true);
            jButton8.setVisible(false);
            jButton9.setVisible(false);
            jCBCli.setVisible(false);
            jCBTarjeta.setVisible(false);
            jCBEfectivo.setVisible(false);
            jCBTarjeta.setEnabled(true);
            jCBEfectivo.setEnabled(true);
            TFCliente.setVisible(true);
            TFEmpleado.setVisible(true);
            TFCliente.setText("");
            TFEmpleado.setText("");
            TFFecha.setText("");
            TFPago.setText("");
            TFPago.setVisible(true);
                              
        } catch (SQLException ex) {
            Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        //BOTÓN CANCELAR
        jTFBuscarVenta.setEnabled(true);
        jButton8.setVisible(false);
        jButton9.setVisible(false);
        TFCliente.setVisible(true);
        TFPago.setVisible(true);
        jCBCli.setVisible(false);
        jCBTarjeta.setVisible(false);
        jCBEfectivo.setVisible(false);
        jCBTarjeta.setEnabled(true);
        jCBEfectivo.setEnabled(true);
        jCBTarjeta.setSelected(true);
        jCBEfectivo.setSelected(false);
        jButton5.setEnabled(true);
        TFIdOculta.setText("");
        TFCliente.setText("");
        TFEmpleado.setText("");
        TFFecha.setText("");
        TFPago.setText("");
        Cantidad1.setVisible(false);
        Cantidad2.setVisible(false);
        Cantidad3.setVisible(false); 
        CB2.setSelected(false);
        CB2.setEnabled(true);
        CB3.setSelected(false); 
        CB3.setEnabled(false);
        TFArticulo1.setVisible(false);
        TFArticulo2.setVisible(false);
        TFTalla1.setVisible(false);
        TFTalla2.setVisible(false);
        TFPrecio1.setVisible(false);
        TFPrecio2.setVisible(false);
        TFStock1.setVisible(false);
        TFStock2.setVisible(false);
        TFNumLinea1.setVisible(false);
        TFNumLinea2.setVisible(false);
        //SEPARADORES ITEM 2
        jSeparator10.setVisible(false);
        jSeparator12.setVisible(false);
        jSeparator16.setVisible(false);
        jSeparator17.setVisible(false);
        jSeparator18.setVisible(false);
        jSeparator19.setVisible(false);         
        //SEPARADORES ITEM 3
        jSeparator20.setVisible(false);
        jSeparator21.setVisible(false);
        jSeparator22.setVisible(false);
        jSeparator23.setVisible(false);
        jSeparator24.setVisible(false);
        jSeparator25.setVisible(false);
        
        Precio1.setText("");
        Precio1.setVisible(false);
        Precio2.setText("");
        Precio2.setVisible(false);
        Precio3.setText("");
        Precio3.setVisible(false);
        precio_total.setText("");
        precio_total.setVisible(false);
        
        TFArticulo.setText("");
        TFTalla.setText("");
        TFPrecio.setText("");
        TFStock.setText("");
        TFNumLinea.setText("");
        
        TFArticulo1.setText("");
        TFTalla1.setText("");
        TFPrecio1.setText("");
        TFStock1.setText("");
        TFNumLinea1.setText("");
        
        TFArticulo2.setText("");
        TFTalla2.setText("");
        TFPrecio2.setText("");
        TFStock2.setText("");
        TFNumLinea2.setText("");    
        
        
  
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jBCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCerrarActionPerformed
        //BOTÓN X, CERRAR VENTANA INDIVIDUAL
        this.dispose();
    }//GEN-LAST:event_jBCerrarActionPerformed

    private void jCBTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBTarjetaActionPerformed
        //CHECKBOX TARJETA
        if (jCBTarjeta.isSelected()) {
            jCBTarjeta.setEnabled(false);
            jCBEfectivo.setEnabled(true);
            jCBEfectivo.setSelected(false);
        }              
    }//GEN-LAST:event_jCBTarjetaActionPerformed

    private void jCBEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBEfectivoActionPerformed
        //CHECKBOX EFECTIVO
        if (jCBEfectivo.isSelected()) {            
            jCBEfectivo.setEnabled(false);
            jCBTarjeta.setEnabled(true);
            jCBTarjeta.setSelected(false);
        }
    }//GEN-LAST:event_jCBEfectivoActionPerformed

    private void jTFBuscarVentaArticulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBuscarVentaArticulosKeyReleased
        //jTextField BÚSQUEDA Ventas/Artículos
        String query = jTFBuscarVentaArticulos.getText().toLowerCase();

        filtro2(query);
    }//GEN-LAST:event_jTFBuscarVentaArticulosKeyReleased

    private void CB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB2ActionPerformed
        //CHECKBOX ITEM 2
        if (CB2.isSelected()) { 
            CB3.setEnabled(true);
            
            jSeparator10.setVisible(true);
            jSeparator12.setVisible(true);
            jSeparator16.setVisible(true);
            jSeparator17.setVisible(true);
            jSeparator18.setVisible(true);
            jSeparator19.setVisible(true);

            TFArticulo1.setVisible(true);
            TFTalla1.setVisible(true);
            TFPrecio1.setVisible(true);
            TFStock1.setVisible(true);
            TFNumLinea1.setVisible(true);
        }else{  
            CB3.setEnabled(false);
            
            jSeparator10.setVisible(false);
            jSeparator12.setVisible(false);
            jSeparator16.setVisible(false);
            jSeparator17.setVisible(false);
            jSeparator18.setVisible(false);
            jSeparator19.setVisible(false);

            TFArticulo1.setVisible(false);
            TFTalla1.setVisible(false);
            TFPrecio1.setVisible(false);
            TFStock1.setVisible(false);
            TFNumLinea1.setVisible(false);
            
            TFArticulo1.setText("");
            TFTalla1.setText("");
            TFPrecio1.setText("");
            TFStock1.setText("");
            TFNumLinea1.setText("");
            Precio2.setText("");
            
            Cantidad2.setVisible(false);
            
        }
    }//GEN-LAST:event_CB2ActionPerformed

    private void CB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB3ActionPerformed
        //CHECKBOX ITEM 3
        if (CB3.isSelected()) {
            CB2.setEnabled(false);
            
            jSeparator20.setVisible(true);
            jSeparator21.setVisible(true);
            jSeparator22.setVisible(true);
            jSeparator23.setVisible(true);
            jSeparator24.setVisible(true);
            jSeparator25.setVisible(true);

            TFArticulo2.setVisible(true);
            TFTalla2.setVisible(true);
            TFPrecio2.setVisible(true);
            TFStock2.setVisible(true);
            TFNumLinea2.setVisible(true);
        } else {
            CB2.setEnabled(true);
            
            jSeparator20.setVisible(false);
            jSeparator21.setVisible(false);
            jSeparator22.setVisible(false);
            jSeparator23.setVisible(false);
            jSeparator24.setVisible(false);
            jSeparator25.setVisible(false);

            TFArticulo2.setVisible(false);
            TFTalla2.setVisible(false);
            TFPrecio2.setVisible(false);
            TFStock2.setVisible(false);
            TFNumLinea2.setVisible(false);
            
            TFArticulo2.setText("");
            TFTalla2.setText("");
            TFPrecio2.setText("");
            TFStock2.setText("");
            TFNumLinea2.setText(""); 
            Precio3.setText("");
            
            Cantidad3.setVisible(false);
        }

    }//GEN-LAST:event_CB3ActionPerformed

    private void Cantidad1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cantidad1ItemStateChanged
        //COMBOBOX CANTIDAD1
        float v1 = Float.parseFloat(TFPrecio.getText());
        int v2 = Cantidad1.getSelectedIndex()+1;
        float resul = v1 * v2;  
        Precio1.setText(Float.toString(resul));
        
        float t2, t3;
        
        float t1 = Float.parseFloat(Precio1.getText());
        if(Precio2.getText().isEmpty()){
            t2 = 0;        
        }else{
            t2 = Float.parseFloat(Precio2.getText());            
        }
        if (Precio3.getText().isEmpty()) {
            t3 = 0;            
        } else {
            t3 = Float.parseFloat(Precio3.getText()); 
        }
        float precio_final = t1 + t2 + t3; 
        precio_total.setText(String.valueOf(precio_final));        
    }//GEN-LAST:event_Cantidad1ItemStateChanged

    private void Cantidad2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cantidad2ItemStateChanged
         //COMBOBOX CANTIDAD2
        float v1 = Float.parseFloat(TFPrecio1.getText());
        int v2 = Cantidad2.getSelectedIndex()+1;
        float resul = v1 * v2;  
        Precio2.setText(Float.toString(resul));
        
        float t2, t3;
        
        float t1 = Float.parseFloat(Precio1.getText());
        if(Precio2.getText().isEmpty()){
            t2 = 0;        
        }else{
            t2 = Float.parseFloat(Precio2.getText());            
        }
        if (Precio3.getText().isEmpty()) {
            t3 = 0;            
        } else {
            t3 = Float.parseFloat(Precio3.getText()); 
        }
        float precio_final = t1 + t2 + t3; 
        precio_total.setText(Float.toString(precio_final));
    }//GEN-LAST:event_Cantidad2ItemStateChanged

    private void Cantidad3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Cantidad3ItemStateChanged
        //COMBOBOX CANTIDAD3
        float v1 = Float.parseFloat(TFPrecio2.getText());
        int v2 = Cantidad3.getSelectedIndex()+1;
        float resul = v1 * v2;  
        Precio3.setText(Float.toString(resul));
        
        float t2, t3;
        
        float t1 = Float.parseFloat(Precio1.getText());
        if(Precio2.getText().isEmpty()){
            t2 = 0;        
        }else{
            t2 = Float.parseFloat(Precio2.getText());            
        }
        if (Precio3.getText().isEmpty()) {
            t3 = 0;            
        } else {
            t3 = Float.parseFloat(Precio3.getText()); 
        }
        float precio_final = t1 + t2 + t3; 
        precio_total.setText(Float.toString(precio_final));
    }//GEN-LAST:event_Cantidad3ItemStateChanged

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
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Ventas().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ventas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    //MÉTODO QUE DEVUELVE EL NÚMERO  DEL ARTICULO, PASANDO POR PARÁMETRO SU NOMBRE, (MÉTODO PARA INSERT)
    public int getCodigoArticulo(String nom) {
        int codArt = 0;
        
        try {                        
            Statement s3 = conex.createStatement(); 
            String queryNombre = "SELECT id from articulos WHERE nombre='" + nom +"'";
            ResultSet r3 = s3.executeQuery(queryNombre);
            r3.first();
            codArt = r3.getInt("id");           
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
         return codArt;
    }

    //MÉTODO QUE DEVUELVE EL NOMBRE DE LA TALLA, PASANDO POR PARÁMETRO SU INT
    public String getNombreCliente(int num) {
        String nombre = "";

        try {            
            Statement s3 = conex.createStatement();
            String queryNombre = "SELECT nombre from clientes WHERE id=" + num;
            ResultSet r3 = s3.executeQuery(queryNombre);
            r3.first();
            nombre = r3.getString("nombre");
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombre;
    }

    //MÉTODO QUE DEVUELVE EL NÚMERO  DE LA MARCA, PASANDO POR PARÁMETRO SU NOMBRE, (MÉTODO PARA INSERT)
    public int getCodigoCliente(String nom) {
        int codCliente = 0;

        try {           
            Statement s3 = conex.createStatement();
            String queryNombre = "SELECT id from clientes WHERE nombre='" + nom + "'";
            ResultSet r3 = s3.executeQuery(queryNombre);
            r3.first();
            codCliente = r3.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codCliente;
    }

    //MÉTODO QUE DEVUELVE EL NOMBRE DE LA MARCA, PASANDO POR PARÁMETRO SU INT
    public String getNombreEmpleado(int num) {
        String empleado = "";

        try {            
            Statement s3 = conex.createStatement();
            String queryNombre = "SELECT nombre from empleados WHERE id=" + num;
            ResultSet r3 = s3.executeQuery(queryNombre);
            r3.first();
            empleado = r3.getString("nombre");
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empleado;
    }

    //MÉTODO QUE DEVUELVE EL NÚMERO  DE LA MARCA, PASANDO POR PARÁMETRO SU NOMBRE, (MÉTODO PARA INSERT)
    public int getCodigoEmpleado(String nom) {
        int codEmp = 0;

        try {
            Statement s3 = conex.createStatement();
            String queryNombre = "SELECT id from empleados WHERE nombre='" + nom + "'";
            ResultSet r3 = s3.executeQuery(queryNombre);
            r3.first();
            codEmp = r3.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codEmp;
    }

    public void añadirFilasTabla() {
        Object datosFila[] = new Object[9]; //EL RANGO DEL ARRAY REPRESENTA LAS COLUMNAS DE LA TABLA, EN ESTE CASO 9

        for (int i = 0; i < array_lineasVenta.size(); i++) {
            datosFila[0] = array_lineasVenta.get(i).getId_venta();
            datosFila[1] = array_lineasVenta.get(i).getNlinea();
            datosFila[2] = array_lineasVenta.get(i).getCliente();
            datosFila[3] = array_lineasVenta.get(i).getEmpleado();
            datosFila[4] = array_lineasVenta.get(i).getFecha();
            datosFila[5] = array_lineasVenta.get(i).getMet_pago();
            datosFila[6] = array_lineasVenta.get(i).getArticulo();
            datosFila[7] = array_lineasVenta.get(i).getCantidad();
            datosFila[8] = array_lineasVenta.get(i).getImporte();

            model.addRow(datosFila);
        }
    }
    
    public void añadirFilasTabla2() {
        Object datosFila[] = new Object[7]; //EL RANGO DEL ARRAY REPRESENTA LAS COLUMNAS DE LA TABLA, EN ESTE CASO 7

        for (int i = 0; i < array_stockTotal.size(); i++) {
            datosFila[0] = array_stockTotal.get(i).getIdArt();
            datosFila[1] = array_stockTotal.get(i).getIdTal();
            datosFila[2] = array_stockTotal.get(i).getDesc();
            datosFila[3] = array_stockTotal.get(i).getPrecio();
            datosFila[4] = array_stockTotal.get(i).getCat();
            datosFila[5] = array_stockTotal.get(i).getMarca();
            datosFila[6] = array_stockTotal.get(i).getStock();

            model2.addRow(datosFila);
        }
    }

    public void llenarClientes() throws SQLException {

        String query2 = "SELECT * FROM clientes";
        Statement s2 = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet r2 = s2.executeQuery(query2);
        DefaultComboBoxModel value1 = new DefaultComboBoxModel();

        while (r2.next()) {
            value1.addElement(r2.getString("nombre"));
        }
        jCBCli.setModel(value1);
    }

    //MÉTODO FILTRAR RESULTADOS
    private void filtro(String query) { //FILTRO PARA CLIENTES

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
                    modelRow
                            = jTable1.convertRowIndexToModel(viewRow);
                    statusText.setText(
                            String.format("Selected Row in view: %d. "
                                    + "Selected Row in model: %d.",
                                    viewRow, modelRow));
                }
            }
        }
        );
    }
    
    private void filtro2(String query) { //FILTRO PARA ARTÍCULOS

        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model2);
        jTable2.setRowSorter(tr);

        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query));

        jTable2.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                viewRow = jTable2.getSelectedRow();
                if (viewRow < 0) {
                    //Selection got filtered away.
                    statusText.setText("");
                } else {
                    modelRow
                            = jTable2.convertRowIndexToModel(viewRow);
                    statusText.setText(
                            String.format("Selected Row in view: %d. "
                                    + "Selected Row in model: %d.",
                                    viewRow, modelRow));
                }
            }
        }
        );
    }   
       
    
    private void updateView() throws SQLException{
        Statement s2 = conex.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query2 = "SELECT * FROM ventas_articulos";
        ResultSet r2 = s2.executeQuery(query2);

        while (r2.next()) { //CREO NUEVOS OBJETOS TIPO ARTICULO Y LLENO EL ARRAYLIST MIENTRAS TENGA RESULTSET
            String idArt = r2.getString("Articulo");
            String idTal = r2.getString("valor_talla");
            String desc = r2.getString("descripcion");
            float precio = r2.getFloat("precio");
            String cat = r2.getString("Categoria");
            String marca = r2.getString("Marca"); 
            int stock = r2.getInt("stock");
            
            array_stockTotal.add(new VentaArticulo(idArt, idTal, desc, precio, cat, marca, stock));
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CB2;
    private javax.swing.JCheckBox CB3;
    private javax.swing.JComboBox<String> Cantidad1;
    private javax.swing.JComboBox<String> Cantidad2;
    private javax.swing.JComboBox<String> Cantidad3;
    private javax.swing.JTextField Precio1;
    private javax.swing.JTextField Precio2;
    private javax.swing.JTextField Precio3;
    private javax.swing.JTextField TFArticulo;
    private javax.swing.JTextField TFArticulo1;
    private javax.swing.JTextField TFArticulo2;
    private javax.swing.JTextField TFCliente;
    private javax.swing.JTextField TFEmpleado;
    private javax.swing.JTextField TFFecha;
    private javax.swing.JTextField TFIdOculta;
    private javax.swing.JTextField TFNumLinea;
    private javax.swing.JTextField TFNumLinea1;
    private javax.swing.JTextField TFNumLinea2;
    private javax.swing.JTextField TFPago;
    private javax.swing.JTextField TFPrecio;
    private javax.swing.JTextField TFPrecio1;
    private javax.swing.JTextField TFPrecio2;
    private javax.swing.JTextField TFStock;
    private javax.swing.JTextField TFStock1;
    private javax.swing.JTextField TFStock2;
    private javax.swing.JTextField TFTalla;
    private javax.swing.JTextField TFTalla1;
    private javax.swing.JTextField TFTalla2;
    private javax.swing.JButton jBCerrar;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jCBCli;
    private javax.swing.JCheckBox jCBEfectivo;
    private javax.swing.JCheckBox jCBTarjeta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTFBuscarVenta;
    private javax.swing.JTextField jTFBuscarVentaArticulos;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel precio2;
    private javax.swing.JLabel precio3;
    private javax.swing.JTextField precio_total;
    private javax.swing.JTextField statusText;
    private javax.swing.JTextField statusText1;
    // End of variables declaration//GEN-END:variables
}
