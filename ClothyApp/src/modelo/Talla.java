package modelo;

/**
 *
 * @author Quique
 */
public class Talla {
    private int id;
    private String valor_talla;    
    
    public Talla(int id, String valor){
        this.id = id;
        this.valor_talla = valor;                
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor_talla() {
        return valor_talla;
    }

    public void setValor_talla(String valor_talla) {
        this.valor_talla = valor_talla;
    }
    
}
