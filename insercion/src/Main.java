import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/estudiantes2024a";
        String user = "root";
        String password = "172843";

        String nombre = "Paul Cabrera";
        String cedula = "0022342658";
        Double b1 = 11.0;
        Double b2 = 18.0;

        String sql = "INSERT INTO estudiantes (Cedula, NOMBRE, b1, b2) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, nombre);
            preparedStatement.setDouble(3, b1);
            preparedStatement.setDouble(4, b2);

            preparedStatement.executeUpdate();

            System.out.println("Inserción exitosa en la base de datos.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
