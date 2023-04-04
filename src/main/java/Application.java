import service.EmployeeDAO;
import service.EmployeeDAOImpl;
import tableClass.City;
import tableClass.Employee;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

// Создание (добавление) сущности Employee в таблицу.
        employeeDAO.addToTableEmployee(new Employee(1, "Екатерина", "Свиридова", "жен.", 33,
                new City(1, null)));

// Получение конкретного объекта Employee по id.
        System.out.println(employeeDAO.getOneEmployee(6));

// Получение списка всех объектов Employee из базы.
        employeeDAO.getAllEmployee().stream()
                .forEachOrdered(System.out::println);

// Изменение конкретного объекта Employee в базе по id.
        employeeDAO.updateMemberEmmployee(new Employee(7,"Екатерина","Матвеева","жен.",18,
                new City(3, null)));

// Удаление конкретного объекта Employee из базы по id.
        employeeDAO.delOneMemberEmployee(7);

    }
}
