import java.sql.*;
import java.util.Scanner;

public class Ejercicio3_DPF {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost/world";
    private static final String USERNAME = "world";
    private static final String PASSWORD = "World123";
    private static final String consulta_aniadir = "INSERT INTO country (code, name) values (?, ?);";
    private static final String consulta_editar = "UPDATE country set code = ?, name = ? WHERE code = ? AND name = ?;";
    private static final String consulta_borrar = "DELETE from country WHERE code = ? AND name = ?;";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Abrimos conexion y preparamos la sentencia SQL
        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)) {   //Iniciamos la conexión
            PreparedStatement ps;
            conn.setAutoCommit(false); // Cambiamos el modo de la conexion para que no haga autocommit
            int opcion = -1;
            while (opcion != 1 && opcion != 2) {    //Con este bucle decidimos que sentencia SQL cargar
                System.out.println("¿Que consulta quieres realizar?\n1.- Añadir\n2.-Editar\n3.- Borrar\n");
                opcion = Integer.parseInt(sc.nextLine());
                if (opcion == 1) {
                    ps = conn.prepareStatement(consulta_aniadir);
                    aniadirPais(ps);
                } else if (opcion == 2) {
                    ps = conn.prepareStatement(consulta_editar);
                    editarPais(ps);
                } else if (opcion == 3) {
                    ps = conn.prepareStatement(consulta_borrar);
                    borrarPais(ps);
                } else {
                    System.out.println("Opción incorrecta");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void aniadirPais(PreparedStatement ps) throws SQLException {
        int numFilas;
        System.out.print("Introduce el código del pais: ");
        ps.setString(1, sc.nextLine());
        System.out.print("Introduce el nómbre del pais: ");
        ps.setString(2, sc.nextLine());
        numFilas = ps.executeUpdate();
        System.out.println("El pais ha sido añadido correctamente, " + numFilas + " filas afectadas");
    }

    private static void editarPais(PreparedStatement ps) throws SQLException {
        int numFilas;
        System.out.print("Introduce el código del pais que quieres editar: ");
        ps.setString(3, sc.nextLine());
        System.out.print("Introduce el nómbre del pais que quieres editar: ");
        ps.setString(4, sc.nextLine());
        System.out.print("Introduce el nuevo código del pais: ");
        ps.setString(1, sc.nextLine());
        System.out.print("Introduce el nuevo nómbre del pais: ");
        ps.setString(2, sc.nextLine());
        numFilas = ps.executeUpdate();
        System.out.println("El pais ha sido editado correctamente, " + numFilas + " filas afectadas");
    }

    private static void borrarPais(PreparedStatement ps) throws SQLException {
        int numFilas;
        System.out.print("Introduce el código del pais: ");
        ps.setString(1, sc.nextLine());
        System.out.print("Introduce el nómbre del pais: ");
        ps.setString(2, sc.nextLine());
        numFilas = ps.executeUpdate();
        System.out.println("El pais ha sido eliminado correctamente, " + numFilas + " filas afectadas");
    }


}
