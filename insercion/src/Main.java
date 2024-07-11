import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/estudiantes2024a";
        String user = "root";
        String password = "172843";

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce la cédula del estudiante: ");
        String cedulaInput = scanner.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                System.out.println("Conectado a la base de datos");

                String query = "SELECT Cedula, NOMBRE, b1, b2 FROM estudiantes WHERE Cedula = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cedulaInput);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String cedula = resultSet.getString("Cedula");
                    String nombre = resultSet.getString("NOMBRE");
                    double b1 = resultSet.getDouble("b1");
                    double b2 = resultSet.getDouble("b2");

                    double notaActual = (b1 + b2) / 2;
                    double notaNecesaria = 60 - notaActual;

                    System.out.println("El estudiante con cédula " + cedula + " y nombre " + nombre + " tiene un promedio actual de: " + notaActual);
                    if (notaNecesaria <= 0) {
                        System.out.println(nombre + " ya ha alcanzado la nota mínima para pasar.");
                    } else if (notaNecesaria > 100) {
                        System.out.println(nombre + " necesita más de 100 en el supletorio, lo cual es imposible.");
                    } else {
                        System.out.println(nombre + " necesita una nota de " + notaNecesaria + " en el supletorio para alcanzar la nota mínima de 60.");
                    }
                } else {
                    System.out.println("No se encontró ningún estudiante con la cédula: " + cedulaInput);
                }

            } catch (Exception e) {
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
