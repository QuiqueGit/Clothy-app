package modelo;

/**
 *
 * @author Quique
 */
public class Cliente {
    private int id;
    private String nif;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    
    public Cliente(int id, String nif, String nom, String apell, String direc, String email, String telef){
        this.id = id;
        this.nif = nif;
        this.nombre = nom;
        this.apellidos = apell;
        this.direccion = direc;
        this.email = email;
        this.telefono = telef;        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
