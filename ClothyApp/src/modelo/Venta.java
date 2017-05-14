package modelo;

import java.util.Date;

/**
 *
 * @author Quique
 */
public class Venta {
    private int id;
    private int cliente;
    private int empleado;
    private String fecha; //PASARLO A DATE
    private String metodo_pago;    
    
    public Venta(int id, int cliente, int empleado, String fecha, String metodo){
        this.id = id;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fecha = fecha;
        this.metodo_pago = metodo;       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }
    
}
