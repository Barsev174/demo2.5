package com.homeWork.demo.service;

import com.homeWork.demo.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findMaxSalary(int department);

    Employee findMinSalary(int department);

    List<Employee> allEmployeeOfDepartments(int department);

    Map<Integer, List<Employee>> allEmployeeByDepartment();
}
