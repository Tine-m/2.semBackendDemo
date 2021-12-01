package backenddemo.datasource;

import backenddemo.model.Department;
import backenddemo.model.Employee;

import java.sql.*;

public class DepartmentRepository {
    private EmployeeRepository empRepo;

    public void setEmpRepo(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    public Department findDepartmentByID(int ID) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM dept WHERE deptno = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Department department = loadRow(rs);
                return department;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Department findDepartmentWithEmployees(int ID) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM dept natural join emp WHERE deptno = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();
            Department department = null;

            if (rs.next()) {
                department = loadRow(rs);
            }

            while (rs.next()) {
                loadEmployeeData(department, rs);
            }

            return department;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Department loadRow(ResultSet rs) throws SQLException {
        int deptno = rs.getInt("deptno");
        String dname = rs.getString("dname");
        String loc = rs.getString("loc");
        return new Department(deptno, dname, loc);
    }

   public void loadEmployeeData(Department department, ResultSet rs) throws SQLException {
        department.addEmployee(empRepo.loadRow(rs));
    }
}
