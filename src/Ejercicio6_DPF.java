import java.sql.*;
import java.util.Scanner;

public class Ejercicio6_DPF {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost/world";
    private static final String USERNAME = "world";
    private static final String PASSWORD = "World123";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //Read User Inputs
        System.out.print("Introduce el código del pais: ");
        String codigo = sc.nextLine();
        System.out.print("Introduce el nombre del pais: ");
        String nombre = sc.nextLine();

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)) {   //Iniciamos la conexión
            CallableStatement cSt = conn.prepareCall("{call insertCountry(?, ?, ?)}");
            cSt.setString(1, codigo);
            cSt.setString(2, nombre);

            //register the OUT parameter before calling the stored procedure
            cSt.registerOutParameter(3, java.sql.Types.VARCHAR);

            cSt.executeUpdate();

            //read the OUT parameter now
            String result = cSt.getString(3);

            System.out.println("Resultado del procedimiento: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
