import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/estudiantes2024a";
        String user = "root";
        String password = "172843";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el nombre del estudiante: ");
        String nombre = scanner.nextLine();

        System.out.print("Introduce la cédula del estudiante: ");
        String cedula = scanner.nextLine();

        System.out.print("Introduce la nota de b1: ");
        double b1 = scanner.nextDouble();

        System.out.print("Introduce la nota de b2: ");
        double b2 = scanner.nextDouble();

        estudiante estudiante = new estudiante(cedula, nombre, b1, b2);

        String sql = "INSERT INTO estudiantes (Cedula, NOMBRE, b1, b2) VALUES (?, ?, ?, ?)";

        try {
            // Cargar el controlador JDBC explícitamente
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, user, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, estudiante.getCedula());
                preparedStatement.setString(2, estudiante.getNombre());
                preparedStatement.setDouble(3, estudiante.getB1());
                preparedStatement.setDouble(4, estudiante.getB2());

                preparedStatement.executeUpdate();

                System.out.println("Inserción exitosa en la base de datos.");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Controlador JDBC no encontrado.");
        } finally {
            scanner.close();
        }
    }
}
