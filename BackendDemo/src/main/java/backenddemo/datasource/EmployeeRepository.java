package backenddemo.datasource;

import backenddemo.model.Department;
import backenddemo.model.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeRepository {
    private DepartmentRepository deptRepo;

    public void setDeptRepo(DepartmentRepository deptRepo) {
        this.deptRepo = deptRepo;
    }

    public ArrayList<Employee> findAllEmployeesWithoutDeptInfo() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            Statement statement = connection.createStatement();
            String sql = "select * from emp";
            ResultSet rs = statement.executeQuery(sql);

            Employee emp;

            while (rs.next()) {
                emp = loadRow(rs);
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
                emp = loadRow(rs);
                dept = deptRepo.loadRow(rs);
                emp.addDepartment(dept);
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public ArrayList<Employee> findEmployeesByDepartmentName(String departmentName) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM emp NATURAL JOIN dept where dname = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, departmentName);
            ResultSet rs = ps.executeQuery();

            Employee employee;
            Department department;

            while (rs.next()) {
                employee = loadRow(rs);
                loadDepartmentData(employee, rs);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee loadRow(ResultSet rs) throws SQLException {
        int empno = rs.getInt("empno");
        String eName = rs.getString("ename");
        String job = rs.getString("job");
        Date _hireDate = rs.getDate("hiredate");
        LocalDate hireDate = _hireDate.toLocalDate();
        int sal = rs.getInt("sal");
        return new Employee(empno, eName, job, hireDate, sal, null);
    }

    public void loadDepartmentData(Employee employee, ResultSet rs) throws SQLException {
         employee.addDepartment(deptRepo.loadRow(rs));
    }

}
