package com.abc.mock;

/**
 * Created with IntelliJ IDEA.
 * User: ashok.agarwal
 * Date: 4/1/14
 * Time: 4:59 PM
 * To change this template use File | Settings | File Templates.
 */
public interface EmployeeManager {
    Employee getEmployee(String name);

    void deleteEmployee(Employee emp);

    void updateEmployee(Employee emp);

    void createEmployee(Employee emp);
}
