package com.homeWork.demo.service;

import com.homeWork.demo.model.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService    {
    Employee add(String firstName, String lastName, int department, double salary);
    Employee remove(String firstName, String lastName);
    Employee find(String firstName, String lastName);

    Collection<Employee> findAll();

    Employee findMaxSalary(int department);

    Employee findMinSalary(int department);

    List<Employee> allEmployeeOfDepartments(int department);

    Collection<Employee> allEmployeeByDepartment();
}
