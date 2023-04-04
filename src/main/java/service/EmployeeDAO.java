package service;

import tableClass.Employee;
import java.util.List;

public interface EmployeeDAO {

    void addToTableEmployee(Employee employee);

    Employee getOneEmployee(int id);

    List<Employee> getAllEmployee();


    void updateMemberEmmployee(Employee employee);

    void delOneMemberEmployee(int id);
}
