package com.homeWork.demo.service;

import com.homeWork.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class DepartmentServiceImpl implements DepartmentService{

    public final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
     Map<String, Employee> employees;

    @Override
    public Employee findMaxSalary(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow();
    }

    @Override
    public Employee findMinSalary(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow();
    }

    @Override
    public List<Employee> allEmployeeOfDepartments(int department) {
            return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> allEmployeeByDepartment() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
