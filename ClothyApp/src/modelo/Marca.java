package modelo;

/**
 *
 * @author Quique
 */
public class Marca {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String contacto;    
    
    public Marca(int id, String nom, String direc, String tel, String email, String contac){
        this.id = id;
        this.nombre = nom;
        this.direccion = direc;
        this.telefono = tel;
        this.email = email;
        this.contacto = contac;       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
}
