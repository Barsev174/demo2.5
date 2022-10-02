package com.homeWork.demo.service;

import com.homeWork.demo.exception.EmployeeNotFoundException;
import com.homeWork.demo.exception.EmployeeAlreadyAddedException;
import com.homeWork.demo.model.Employee;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    Map<String, Employee> employees = new HashMap<>(Map.of(
            "VasjPupkin",
            new Employee(
                    "Vasj",
                    "Pupkin",
                    1,
                    100000),
            "AlexMihaylov",
            new Employee(
                    "Alex",
                    "Mihaylov",
                    2,
                    90000),
            "ArtemZakirov",
            new Employee(
                    "Artem",
                    "Zakirov",
                    2,
                    120000),
            "EvgeniyZarov",
            new Employee(
                    "Evgeniy",
                    "Zarov",
                    1,
                    87000)
    ));


    @Override
    public Employee add(String firstName, String lastName, int department, double salary) {
        Employee employee = new Employee(firstName, lastName, department, salary);
        List<Employee> listEmployee = new ArrayList<>(employees.values());
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;

    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        String fullName = firstName + " " + lastName;
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();

    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        List<Employee> listEmployee = new ArrayList<>(employees.values());
        Optional<Employee> optional = listEmployee.stream()
                .filter(e -> e.getFullName().equals(employee.getFullName()))
                .findAny();
        return optional.orElseThrow();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    @Override
    public Employee findMaxSalary(int department) {
        return employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow();
    }

    @Override
    public Employee findMinSalary(int department) {
        return employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow();
    }

    @Override
    public List<Employee> allEmployeeOfDepartments(int department) {
        return employees.values().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Employee> allEmployeeByDepartment() {
        return null;
    }

}