import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException{

        final String user = "postgres";
        final String password = "clover";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT *" +
                     "FROM employee\n" +
                     "LEFT JOIN city c ON c.city_id = employee.city_id\n" +
                     "WHERE id = 4;")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){

                System.out.println(resultSet.getString("last_name") + " "
                + resultSet.getString("gender") + " "
                + resultSet.getString("city_name"));
            }

        }catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД !!!");
            e.printStackTrace();
        }
    }
}
