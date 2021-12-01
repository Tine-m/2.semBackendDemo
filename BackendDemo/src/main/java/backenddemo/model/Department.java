package backenddemo.model;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private int deptno;
    private String dname;
    private String loc;
    private List<Employee> employees;

    public Department(int deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                ", employees=" + employees +
                '}';
    }
}
