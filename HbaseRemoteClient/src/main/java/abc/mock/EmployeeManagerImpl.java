package abc.mock;

/**
 * Created with IntelliJ IDEA.
 * The class in order to be tested by unit testing must have mock DBService not actual.
 * As the DBService is already tested by API provider.
 * <p/>
 * User: ashok.agarwal
 * Date: 4/1/14
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeManagerImpl implements EmployeeManager {

    /// Wrong from unit testing perspective the dependency must be injected using setter or constructor.
    //public DBService dbService = new DBServiceImpl();
    public DBService dbService = null;

    public void setDbService(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public Employee getEmployee(String name) {
        //get employee from db
        dbService.myDelMethod();
        return null;
    }

    @Override
    public void deleteEmployee(Employee emp) {
        //delete employee in db
        dbService.myDelMethod();
    }

    @Override
    public void updateEmployee(Employee emp) {
        //update employee in db
        dbService.myDelMethod();
    }

    @Override
    public void createEmployee(Employee emp) {
        //create employee in db
        dbService.myDelMethod();
    }
}
