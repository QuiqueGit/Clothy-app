package modelo;

/**
 *
 * @author Quique
 */
public class VentaArticulo {
    
    private String idArticulo;
    private String idTalla;
    private String descripcion;
    private float precio;
    private String categoria;
    private String marca;
    private int stock;

    public VentaArticulo(String idArt, String idTal, String desc, float precio, String cat, String marca, int stock) {
        this.idArticulo = idArt;
        this.idTalla = idTal;
        this.descripcion = desc;
        this.precio = precio;
        this.categoria = cat;
        this.marca = marca;
        this.stock = stock;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(String idTalla) {
        this.idTalla = idTalla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    
    
}
