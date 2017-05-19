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
        //SETEA EL ANCHO DE LAS COLUMNAS      
        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(55);
        columnModel.getColumn(1).setPreferredWidth(120);
        columnModel.getColumn(2).setPreferredWidth(120);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(120);

        TFIdOculta.setVisible(false);
        TFEmpleado.setEnabled(false);
        statusText.setVisible(false);
        statusText1.setVisible(false);
        jCBCli.setVisible(false);
        jCBTarjeta.setVisible(false);
        jCBEfectivo.setVisible(false);
        jButton8.setVisible(false);
        jButton9.setVisible(false);

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

        //añadirFilasTabla(); //MÉTODO QUE LLENA LA TABLA CON LA INFO DEL ARRAYLIST, TABLA1 VENTAS/CLIENTES
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
                if (jTFBuscarVentaArticulos.getText().isEmpty()) {
                    int i = jTable2.getSelectedRow();
                    // 'i' HACE REFERENCIA AL NÚMERO DE LA FILA SELECCIONADA Y EL NÚMERO HACE REFERENCIA A LA COLUMNA (i,n)
                    TFArticulo.setText(model2.getValueAt(i, 0).toString());
                    TFTalla.setText(model2.getValueAt(i, 1).toString());
                    TFPrecio.setText(model2.getValueAt(i, 3).toString());
                    TFStock.setText(model2.getValueAt(i, 6).toString());

                } else {
                    int i = modelRow;
                    TFArticulo.setText(model2.getValueAt(i, 0).toString());
                    TFTalla.setText(model2.getValueAt(i, 1).toString());
                    TFPrecio.setText(model2.getValueAt(i, 3).toString());
                    TFStock.setText(model2.getValueAt(i, 6).toString());

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
        TFCantidad = new javax.swing.JTextField();
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
        jCBCli = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1045, 720));
        setMinimumSize(new java.awt.Dimension(1045, 720));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1045, 720));
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
        jBCerrar.setBounds(1020, 0, 20, 20);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cliente");
        jLabel2.setFocusable(false);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 120, 55, 22);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Empleado");
        jLabel3.setFocusable(false);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 160, 78, 22);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha");
        jLabel4.setFocusable(false);
        getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 200, 45, 22);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Método pago");
        jLabel5.setFocusable(false);
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 240, 127, 22);

        jLabel8.setFont(new java.awt.Font("Magneto", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ventas");
        jLabel8.setFocusable(false);
        getContentPane().add(jLabel8);
        jLabel8.setBounds(140, 30, 140, 30);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/vista/images/buscar_iconx24.png"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(500, 30, 40, 40);

        TFIdOculta.setBackground(new java.awt.Color(0, 0, 0));
        TFIdOculta.setEnabled(false);
        TFIdOculta.setFocusable(false);
        TFIdOculta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFIdOcultaActionPerformed(evt);
            }
        });
        getContentPane().add(TFIdOculta);
        TFIdOculta.setBounds(60, 70, 20, 24);

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
        TFCliente.setBounds(170, 120, 142, 20);

        TFEmpleado.setBackground(new java.awt.Color(204, 204, 204));
        TFEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        TFEmpleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFEmpleado.setBorder(null);
        TFEmpleado.setCaretColor(new java.awt.Color(255, 255, 255));
        TFEmpleado.setOpaque(false);
        getContentPane().add(TFEmpleado);
        TFEmpleado.setBounds(170, 160, 142, 20);

        TFFecha.setBackground(new java.awt.Color(204, 204, 204));
        TFFecha.setForeground(new java.awt.Color(255, 255, 255));
        TFFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFFecha.setBorder(null);
        TFFecha.setCaretColor(new java.awt.Color(255, 255, 255));
        TFFecha.setOpaque(false);
        getContentPane().add(TFFecha);
        TFFecha.setBounds(170, 200, 142, 20);

        TFPago.setBackground(new java.awt.Color(204, 204, 204));
        TFPago.setForeground(new java.awt.Color(255, 255, 255));
        TFPago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFPago.setBorder(null);
        TFPago.setCaretColor(new java.awt.Color(255, 255, 255));
        TFPago.setOpaque(false);
        getContentPane().add(TFPago);
        TFPago.setBounds(170, 240, 142, 20);

        TFArticulo.setBackground(new java.awt.Color(204, 204, 204));
        TFArticulo.setForeground(new java.awt.Color(255, 255, 255));
        TFArticulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFArticulo.setBorder(null);
        TFArticulo.setCaretColor(new java.awt.Color(255, 255, 255));
        TFArticulo.setOpaque(false);
        TFArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFArticuloActionPerformed(evt);
            }
        });
        getContentPane().add(TFArticulo);
        TFArticulo.setBounds(140, 300, 142, 20);

        TFTalla.setBackground(new java.awt.Color(204, 204, 204));
        TFTalla.setForeground(new java.awt.Color(255, 255, 255));
        TFTalla.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFTalla.setBorder(null);
        TFTalla.setCaretColor(new java.awt.Color(255, 255, 255));
        TFTalla.setOpaque(false);
        getContentPane().add(TFTalla);
        TFTalla.setBounds(140, 340, 142, 20);

        TFPrecio.setBackground(new java.awt.Color(204, 204, 204));
        TFPrecio.setForeground(new java.awt.Color(255, 255, 255));
        TFPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFPrecio.setBorder(null);
        TFPrecio.setCaretColor(new java.awt.Color(255, 255, 255));
        TFPrecio.setOpaque(false);
        getContentPane().add(TFPrecio);
        TFPrecio.setBounds(140, 380, 142, 20);

        TFStock.setBackground(new java.awt.Color(204, 204, 204));
        TFStock.setForeground(new java.awt.Color(255, 255, 255));
        TFStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFStock.setBorder(null);
        TFStock.setCaretColor(new java.awt.Color(255, 255, 255));
        TFStock.setOpaque(false);
        getContentPane().add(TFStock);
        TFStock.setBounds(140, 420, 142, 20);

        TFCantidad.setBackground(new java.awt.Color(204, 204, 204));
        TFCantidad.setForeground(new java.awt.Color(255, 255, 255));
        TFCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFCantidad.setBorder(null);
        TFCantidad.setCaretColor(new java.awt.Color(255, 255, 255));
        TFCantidad.setOpaque(false);
        getContentPane().add(TFCantidad);
        TFCantidad.setBounds(140, 460, 142, 20);

        TFNumLinea.setBackground(new java.awt.Color(204, 204, 204));
        TFNumLinea.setForeground(new java.awt.Color(255, 255, 255));
        TFNumLinea.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFNumLinea.setBorder(null);
        TFNumLinea.setCaretColor(new java.awt.Color(255, 255, 255));
        TFNumLinea.setOpaque(false);
        getContentPane().add(TFNumLinea);
        TFNumLinea.setBounds(140, 500, 142, 20);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nº Linea");
        jLabel16.setFocusable(false);
        getContentPane().add(jLabel16);
        jLabel16.setBounds(30, 500, 90, 22);
        getContentPane().add(jSeparator15);
        jSeparator15.setBounds(30, 520, 270, 10);

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
        jTFBuscarVenta.setBounds(530, 40, 120, 19);

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
        jTFBuscarVentaArticulos.setBounds(530, 360, 120, 19);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Artículo");
        jLabel6.setFocusable(false);
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 300, 80, 22);
        getContentPane().add(jSeparator6);
        jSeparator6.setBounds(30, 320, 270, 10);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Talla");
        jLabel7.setFocusable(false);
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 340, 50, 22);
        getContentPane().add(jSeparator9);
        jSeparator9.setBounds(30, 360, 270, 10);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Precio");
        jLabel12.setFocusable(false);
        getContentPane().add(jLabel12);
        jLabel12.setBounds(30, 380, 80, 22);
        getContentPane().add(jSeparator11);
        jSeparator11.setBounds(30, 400, 270, 10);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Stock");
        jLabel14.setFocusable(false);
        getContentPane().add(jLabel14);
        jLabel14.setBounds(30, 420, 60, 22);
        getContentPane().add(jSeparator13);
        jSeparator13.setBounds(30, 440, 270, 10);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Cantidad");
        jLabel15.setFocusable(false);
        getContentPane().add(jLabel15);
        jLabel15.setBounds(30, 460, 80, 22);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Lineas de venta");
        jLabel11.setFocusable(false);
        getContentPane().add(jLabel11);
        jLabel11.setBounds(660, 30, 140, 40);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Stock en tienda");
        jLabel13.setFocusable(false);
        getContentPane().add(jLabel13);
        jLabel13.setBounds(660, 350, 140, 40);
        getContentPane().add(jSeparator14);
        jSeparator14.setBounds(30, 480, 270, 10);

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
        jButton5.setBounds(110, 590, 140, 30);

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
        jButton8.setBounds(60, 640, 120, 30);

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
        jButton9.setBounds(160, 640, 120, 30);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(60, 140, 270, 10);
        getContentPane().add(jSeparator3);
        jSeparator3.setBounds(60, 180, 270, 10);
        getContentPane().add(jSeparator4);
        jSeparator4.setBounds(60, 220, 270, 10);
        getContentPane().add(jSeparator5);
        jSeparator5.setBounds(60, 260, 270, 10);
        getContentPane().add(jSeparator7);
        jSeparator7.setBounds(410, 60, 120, 10);

        statusText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusTextActionPerformed(evt);
            }
        });
        getContentPane().add(statusText);
        statusText.setBounds(470, 300, 20, 24);
        getContentPane().add(statusText1);
        statusText1.setBounds(470, 640, 20, 24);

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
        jCBTarjeta.setBounds(190, 240, 70, 20);

        jCBEfectivo.setForeground(new java.awt.Color(255, 255, 255));
        jCBEfectivo.setText("Efectivo");
        jCBEfectivo.setContentAreaFilled(false);
        jCBEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBEfectivoActionPerformed(evt);
            }
        });
        getContentPane().add(jCBEfectivo);
        jCBEfectivo.setBounds(260, 240, 80, 20);

        jCBCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jCBCli);
        jCBCli.setBounds(170, 120, 160, 20);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/vista/images/buscar_iconx24.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(500, 350, 40, 40);
        getContentPane().add(jSeparator8);
        jSeparator8.setBounds(410, 380, 120, 10);

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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/images/tab_fondo2.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(931, 386));
        jLabel1.setMinimumSize(new java.awt.Dimension(931, 386));
        jLabel1.setPreferredSize(new java.awt.Dimension(931, 386));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(390, -10, 1200, 720);

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
            vId = (array_ventas.get(array_ventas.size() - 1).getId() + 1);
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
            //INSERT EN LA TABLA
            if(array_ventas.isEmpty()){ //SI NO HAY NINGÚN PEDIDO AÚN, ES EL 0.
                vId=1;                
            }else{
            vId = (array_ventas.get(array_ventas.size() - 1).getId() + 1);
            }
            String emp = TFEmpleado.getText();
            model.addRow(new Object[]{vId, cli, emp, vFecha, vMetodo_pago});
            //INSERT EN EL ARRAY
            array_ventas.add(new Venta(vId, vCli, vEmp, vFecha, vMetodo_pago));
            
            /*******************************************************************/            
            //INSERT DE LA VENTA COMPLETA, A LA TABLA linea_ventas
            
            //INSERT EN LA BASE DE DATOS
            int venta_id = vId;
            int num_linea = Integer.parseInt(TFNumLinea.getText());
            int articulo = getCodigoArticulo(TFArticulo.getText());
            int cantidad = Integer.parseInt(TFCantidad.getText());
            float precio = Float.parseFloat(TFPrecio.getText());
            float importe = cantidad * precio;
            
            PreparedStatement ps2 = conex.prepareStatement("INSERT INTO lineas_ventas (id, venta_id, num_linea, articulo, cantidad, importe) VALUES (?,?,?,?,?,?)");
            ps2.setInt(1, venta_id);
            ps2.setInt(2, venta_id);
            ps2.setInt(3, num_linea);
            ps2.setInt(4, articulo);
            ps2.setInt(5, cantidad);
            ps2.setFloat(6, importe);
            ps2.executeUpdate();
            
            //INSERT PARA MÁS DE UN ARTICULO EN LA MISMA VENTA...
            
            
            
            
            
            
            
            
            
            
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

    private void statusTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusTextActionPerformed

    private void TFArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFArticuloActionPerformed

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
        Object datosFila[] = new Object[9]; //EL RANGO DEL ARRAY REPRESENTA LAS COLUMNAS DE LA TABLA, EN ESTE CASO 7

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TFArticulo;
    private javax.swing.JTextField TFCantidad;
    private javax.swing.JTextField TFCliente;
    private javax.swing.JTextField TFEmpleado;
    private javax.swing.JTextField TFFecha;
    private javax.swing.JTextField TFIdOculta;
    private javax.swing.JTextField TFNumLinea;
    private javax.swing.JTextField TFPago;
    private javax.swing.JTextField TFPrecio;
    private javax.swing.JTextField TFStock;
    private javax.swing.JTextField TFTalla;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
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
    private javax.swing.JTextField statusText;
    private javax.swing.JTextField statusText1;
    // End of variables declaration//GEN-END:variables
}
