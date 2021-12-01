package backenddemo.model;

import java.time.LocalDate;

public class Employee {
    private int empno;
    private String eName;
    private String job;
    private LocalDate hireDate;
    private int sal;
    private Department dept;

    public Employee(int empno, String eName, String job, LocalDate hireDate, int sal, Department dept) {
        this.empno = empno;
        this.eName = eName;
        this.job = job;
        this.hireDate = hireDate;
        this.sal = sal;
        this.dept = dept;
    }

    public Employee(int empno, String eName, String job, LocalDate hireDate, int sal) {
        this(empno, eName, job, hireDate, sal, null);
    }

    public void addDepartment(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empno=" + empno +
                ", eName='" + eName + '\'' +
                ", job='" + job + '\'' +
                ", hireDate=" + hireDate +
                ", sal=" + sal +
                ", dept=" + dept +
                '}' + '\n';
    }
}
