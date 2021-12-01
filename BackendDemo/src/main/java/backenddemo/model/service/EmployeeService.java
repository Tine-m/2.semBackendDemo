package backenddemo.model.service;

import backenddemo.datasource.DataFacade;
import backenddemo.model.Employee;

import java.util.List;

public class EmployeeService {
    private DataFacade facade = new DataFacade();

    public List<Employee> findAllEmployeesWithoutDeptInfo() {
        return facade.findAllEmployeesWithoutDeptInfo();
    }

    public List<Employee> findAllEmployeesWithDeptInfo() {
        return facade.findAllEmployeesWithDeptInfo();
    }

    public List<Employee> findEmployeesByDepartmentName(String departmentName) {
        return facade.findEmployeesByDepartmentName(departmentName);
    }
}
