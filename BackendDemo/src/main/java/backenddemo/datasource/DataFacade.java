package backenddemo.datasource;

import backenddemo.model.Department;
import backenddemo.model.Employee;

import java.util.ArrayList;

public class DataFacade {

    DepartmentRepository deptRepo = new DepartmentRepository();
    EmployeeRepository empRepo = new EmployeeRepository();

    public Department findDepartmentByID(int ID) {
        return deptRepo.findDepartmentByID(ID);
    }

    public ArrayList<Employee> findAllEmployeesWithoutDeptInfo() {
        return empRepo.findAllEmployeesWithoutDeptInfo();
    }

    public ArrayList<Employee> findAllEmployeesWithDeptInfo() {
        return empRepo.findAllEmployeesWithDeptInfo();
    }

    public ArrayList<Employee> findEmployeesByDepartmentName(String departmentName) {
        return empRepo.findEmployeesByDepartmentName(departmentName);
    }
}
