package com.jbksoft.hadoop;

import com.abc.mock.DBService;
import com.abc.mock.Employee;
import com.abc.mock.EmployeeManagerImpl;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/2/14
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmployeeManagerImplTest {

    EmployeeManagerImpl employeeManager = null;

    //This is the mocking of EmployeeManagerImpl using proxy i.e. using mocking the dependent service with proxy.
    @Test
    public void testDeleteEmployee() {
        Employee emp = new Employee();
        emp.setName("First Last");
        // created mock DBService
        DBService dbService = new DBService() {
            public void myDelMethod() {
                System.out.println("MockDBService using interface");
            }
        };
        employeeManager = new EmployeeManagerImpl();
        employeeManager.setDbService(dbService);
        employeeManager.deleteEmployee(emp);
    }

    public void testEasyMock() {
//        Employee emp = new Employee();
//        employeeManager = new EmployeeManagerImpl();
//        mockDBService = createStrictMock(DBService.class);
//        employeeManager.setDbService(mockDBService);
//        Easymock.expect(mockDBService.myDelMethod(eq(userName), eq(passwordHash)))
//                .andReturn(results);
//
//        replay(myDelMethod);
//        assertTrue(employeeManager.deleteEmployee(emp));
//        verify(myDelMethod);
    }

    @Test
    public void testJMockit() {
        Employee emp = new Employee();
        emp.setName("First Last");
        DBService dbService = new MockUp<DBService>() {
            @Mock
            public void myDelMethod() {
                System.out.println("MockDBService from JMockit using Interface");
            }
        }.getMockInstance();

        employeeManager = new EmployeeManagerImpl();
        employeeManager.setDbService(dbService);
        employeeManager.deleteEmployee(emp);
    }


}
