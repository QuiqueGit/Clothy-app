package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Quique
 */
public class ConexionDB {    
    
    static ArrayList<Empleado> empleados = new ArrayList<>();       
    
    static Statement s;
    
    Connection connection;

    public ConexionDB() throws SQLException, ClassNotFoundException {     
        
        Class.forName("com.mysql.jdbc.Driver"); //Importar driver
        
        String url = "jdbc:mysql://localhost:3306/clothy";
        String user = "root";
        String pass = "123";
        connection = DriverManager.getConnection(url, user, pass);
        s = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        System.out.println("Conexión establecida!");   
        
        
    }    

    public Connection getCon() {
        return connection;
    }
    
    public static ArrayList<Empleado> empleados() throws SQLException {     

        ResultSet rs = s.executeQuery("SELECT * FROM empleados");  
        
        while (rs.next()) {  //RECORRER RESULTSET PARA GUARDAR NOMBRE Y PASS EN EL ARRAY

            String nom = rs.getString("nombre");
            String pass = rs.getString("password");            

            empleados.add(new Empleado(nom,pass));
              
        }     
 
        return empleados;

    }
    
    public static void cerrarConex (Connection connection) throws SQLException {
        connection.close();
    }
    
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    
}
