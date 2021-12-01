package backenddemo.model.service;

import backenddemo.datasource.DataFacade;
import backenddemo.model.Department;

public class DepartmentService {

  private DataFacade facade = new DataFacade();

  public Department findDepartmentByID(int ID) {
      return facade.findDepartmentByID(ID);
  }

  public Department findDepartmentWithEmployees(int ID)  {
      return facade.findDepartmentWithEmployees(ID);
  }

    public static void main(String[] args) {
        Department testDepartment = new DepartmentService().findDepartmentWithEmployees(30);
        System.out.println(testDepartment);
    }
}
