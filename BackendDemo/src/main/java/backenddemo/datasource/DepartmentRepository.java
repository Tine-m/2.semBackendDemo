package backenddemo.datasource;

import backenddemo.model.Department;
import java.sql.*;

public class DepartmentRepository {

    public Department findDepartmentByID(int ID) {
        try {
            Connection connection = DBManager.getConnection();
            String sql = "SELECT * FROM dept WHERE deptno = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();


            if (rs.next()) {
                return loadRow(rs);
            }

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
}
