package backenddemo.model.service;

import backenddemo.datasource.DataFacade;
import backenddemo.model.Department;

public class DepartmentService {

  private DataFacade facade = new DataFacade();

  public Department findDepartmentByID(int ID) {
      return facade.findDepartmentByID(ID);
  }
}
