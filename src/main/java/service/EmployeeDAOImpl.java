package service;

import tableClass.City;
import tableClass.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dataConnection.DataConnection.getConnection;

public class EmployeeDAOImpl implements EmployeeDAO {

    public Employee setNewEmployeeFromTable(ResultSet resultSet) throws SQLException {

        int idEmployee = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String gender = resultSet.getString("gender");
        int age = resultSet.getInt("age");
        int cityId = resultSet.getInt("city_id");
        String cityName = resultSet.getString("city_name");
        if (cityName == null) {
            cityName = "рождён в CCCP ..";
        }
        return new Employee(idEmployee, firstName, lastName, gender, age, new City(cityId, cityName));
    }

    @Override
    public void addToTableEmployee(Employee employee) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO employee (id, first_name, last_name, gender, age, city_id) " +
                             "VALUES ((?),(?),(?),(?),(?),(?))")
        ) {
            statement.setInt(1, (getLastNumberIdEmployee() + 1));
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setInt(5, employee.getAge());
            statement.setInt(6, employee.getCity().getCityId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getLastNumberIdEmployee() {

        int id = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT MAX(id) FROM employee")
        ) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getInt("max");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Employee getOneEmployee(int id) {

        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM employee \n" +
                             "LEFT JOIN city c ON c.city_id = employee.city_id \n" +
                             "WHERE id = (?)")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            employee = setNewEmployeeFromTable(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {

        List<Employee> employeeList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM employee \n" +
                             "LEFT JOIN city c ON c.city_id = employee.city_id \n" +
                             "ORDER BY id")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employeeList.add(setNewEmployeeFromTable(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void updateMemberEmmployee(Employee employee) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE employee SET last_name = (?), age = (?), city_id = (?) WHERE id = (?)")
        ) {
            statement.setString(1, employee.getLastName());
            statement.setInt(2, employee.getAge());
            statement.setInt(3, employee.getCity().getCityId());
            statement.setInt(4, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delOneMemberEmployee(int id) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM employee WHERE id = (?)")
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
