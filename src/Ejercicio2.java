import java.sql.*;
import java.util.Scanner;

public class Ejercicio2 {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost/world";
    private static final String USERNAME = "world";
    private static final String PASSWORD = "World123";
    private static final String consulta_sql = "SELECT code, name FROM country LIMIT 50;";
    private static final String consulta_sql2 = "SELECT code, name FROM country WHERE code LIKE 'A%';";

    public static void main(String[] args) {
        // Abrimos conexion y preparamos la sentencia SQL
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)) {   //Iniciamos la conexión
            Scanner sc = new Scanner(System.in);
            PreparedStatement ps;
            int opcion = -1;
            while (opcion != 1 && opcion != 2) {    //Con este bucle decidimos que sentencia SQL cargar
                System.out.print("¿Que consulta quieres realizar? 1 o 2: ");
                opcion = sc.nextInt();
                if (opcion == 1) {
                    ps = conn.prepareStatement(consulta_sql);
                } else if (opcion == 2) {
                    ps = conn.prepareStatement(consulta_sql2);
                } else {
                    System.out.println("Opción incorrecta");
                }
            }

            PreparedStatement pst = conn.prepareStatement(consulta_sql);
            conn.setAutoCommit(false); // Cambiamos el modo de la conexion para que no haga autocommit
            ResultSet rs = pst.executeQuery(); // Ejecutamos la consulta
            while (rs.next()) {
                System.out.println("Nombre del pais: " + rs.getString(2) + " | Código: " + rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}