package backenddemo.datasource;

import backenddemo.model.Department;
import backenddemo.model.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeRepository {
    private DepartmentRepository deptRepo = new DepartmentRepository();

    public ArrayList<Employee> findAllEmployeesWithoutDeptInfo() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from emp";
            ResultSet rs = statement.executeQuery(sql);

            Employee emp;

            while (rs.next()) {
                emp = load(rs);
                employees.add(emp);
            }
        } //TODO error handling with custom exception type
        catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public ArrayList<Employee> findAllEmployeesWithDeptInfo() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM emp NATURAL JOIN dept";
            ResultSet rs = statement.executeQuery(sql);

            Employee emp;
            Department dept;

            while (rs.next()) {
                emp = load(rs);
                dept = deptRepo.load(rs);
                emp.setDept(dept);
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee load(ResultSet rs) throws SQLException {
        int empno = rs.getInt("empno");
        String eName = rs.getString("ename");
        String job = rs.getString("job");
        Date _hireDate = rs.getDate("hiredate");
        LocalDate hireDate = _hireDate.toLocalDate();
        int sal = rs.getInt("sal");

        return new Employee(empno, eName, job, hireDate, sal, null);
    }
}
