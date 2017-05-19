package modelo;

/**
 *
 * @author Quique
 */
public class VentaLineaVenta {
    
    private int id_venta;
    private int Nlinea;
    private String cliente;
    private String empleado;
    private String fecha;
    private String met_pago;
    private String articulo;
    private int cantidad;
    private int importe;

    public VentaLineaVenta(int id_venta, int NLinea, String cliente, String  empleado, String fecha, String met_pago, String articulo, int cantidad, int importe) {
        this.id_venta = id_venta;
        this.Nlinea = NLinea;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fecha = fecha;
        this.met_pago = met_pago;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getNlinea() {
        return Nlinea;
    }

    public void setNlinea(int Nlinea) {
        this.Nlinea = Nlinea;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMet_pago() {
        return met_pago;
    }

    public void setMet_pago(String met_pago) {
        this.met_pago = met_pago;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

      
}
