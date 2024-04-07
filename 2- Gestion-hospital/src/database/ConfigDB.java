package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    //Variable que va a contener el estado de la conexión
    static Connection objConnection = null;

    //Método para abrir la conexión entre Java y la base de datos

    public static Connection openConnection(){

        try {

            // Class.forName permite obtener o implementar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creamos variables con nuestras credenciales de la base de datos
            String url = "jdbc:mysql://b6d2rkr8iq3erpnck8zl-mysql.services.clever-cloud.com:3306/b6d2rkr8iq3erpnck8zl";
            String user = "ugtjx4zadxqlymuj";
            String password = "8nKmLIJRll4l2ZDDbbCk";

/*
            String url = "jdbc:mysql://localhost:3306/hospital";
            String user = "root";
            String password = "Rlwl2023.";
*/

            //Establecemos la conexion
            objConnection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida");

        }catch (ClassNotFoundException e){
            System.out.println("Error >> Driver no instalados \n"+e.getMessage());
        }catch (SQLException e){
            System.out.println("Error >> La conexión a BD no fue establecida\n"+e.getMessage());
        }

        return objConnection;
    }


    public static void closeConnection(){

        //Si hay una conexión activa, la cerramos
        try{
            if (objConnection != null) objConnection.close();


            //
        }catch (SQLException e){
            System.out.println("Error >> " + e.getMessage());
        }
    }




}
