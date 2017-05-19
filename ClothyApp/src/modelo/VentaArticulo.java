package modelo;

/**
 *
 * @author Quique
 */
public class VentaArticulo {

    private String idArt;
    private String idTal; 
    private String desc;
    private float precio;
    private String cat; 
    private String marca;  
    private int stock; 
            
    public VentaArticulo(String idArt, String idTal, String desc, float precio, String cat,String marca,int stock) {
        this.idArt=idArt;
        this.idTal=idTal;
        this.desc=desc;
        this.precio=precio;
        this.cat=cat;
        this.marca=marca;
        this.stock=stock;

    }

    public String getIdArt() {
        return idArt;
    }

    public void setIdArt(String idArt) {
        this.idArt = idArt;
    }

    public String getIdTal() {
        return idTal;
    }

    public void setIdTal(String idTal) {
        this.idTal = idTal;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
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
