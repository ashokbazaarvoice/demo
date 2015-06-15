package abc.mock;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/1/14
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeDemo {
    public static void main(String[] args) throws Exception {
        Employee emp = new Employee();
        emp.setName("First Last");
        DBService dbService = new DBServiceImpl();
        EmployeeManagerImpl employeeManager = new EmployeeManagerImpl();
        employeeManager.setDbService(dbService);
        employeeManager.createEmployee(emp); // give error as no real db but we now injected DB Service
        employeeManager.getEmployee("First Last");  // give error as no real db
    }
}
