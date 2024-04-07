package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    //Variable que va a contener el estado de la conexión
    static Connection connection = null;

    //Método para abrir la conexión entre Java y la base de datos

    public static Connection openConnection(){

        try {

            // Class.forName permite obtener o implementar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creamos variables con nuestras credenciales de la base de datos
/*            String url = "jdbc:mysql://bdnckmdcc3karwgnao8f-mysql.services.clever-cloud.com:3306/bdnckmdcc3karwgnao8f";
            String user = "uinthcgonveaou2w";
            String password = "aMxjwt2w0pZ4eRP6G7Ip";*/

/*            String url = "jdbc:mysql://localhost:3306/aerolinea";
            String user = "root";
            String password = "Rlwl2023.";*/

            String url = "jdbc:mysql://localhost:3306/aerolinea";
            String user = "root";
            String password = "Root";

            //Establecemos la conexion
            connection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver no instalados \n"+e.getMessage());
        }catch (SQLException e){
            System.out.println("Error >> La conexión a BD no fue establecida\n"+e.getMessage());
        }

        return connection;
    }


    public static void closeConnection(){

        //Si hay una conexión activa, la cerramos
        try{
            if (connection != null) connection.close();

        }catch (SQLException e){
            System.out.println("Error >> " + e.getMessage());
        }
    }




}
