import java.sql.*;
import java.util.Scanner;

public class Ejercicio7_DPF {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost/world";
    private static final String USERNAME = "world";
    private static final String PASSWORD = "World123";
    private static final String consulta_sql = "SELECT code, name FROM country;";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD)) {   //Iniciamos la conexión
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);     //ResultSet actualizable
            ResultSet rs = st.executeQuery(consulta_sql);

            conn.setAutoCommit(false);

            rs.moveToInsertRow();
            rs.updateString("code", "ZZZ");
            rs.updateString("name", "Zambia");
            rs.insertRow();

            conn.commit();
            System.out.println("Consulta actualizada con éxito");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
