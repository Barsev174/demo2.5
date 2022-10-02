
package com.homeWork.demo.controler;


import com.homeWork.demo.model.Employee;
import com.homeWork.demo.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam double salary) {
        return employeeService.add(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String lastName, @RequestParam String firstName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String lastName, @RequestParam String firstName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping("/findAll")
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/departments/max-salary")
    public Employee findMaxSalary(@RequestParam int department) {
        return employeeService.findMaxSalary(department);
    }

    @GetMapping("/departments/min-salary")
    public Employee findMinSalary(@RequestParam int department) {
        return employeeService.findMinSalary(department);
    }

    @GetMapping("/departments")
    public List<Employee>  allEmployeeOfDepartments(@RequestParam int department) {
        return employeeService.allEmployeeOfDepartments(department);
    }

    @GetMapping("/departments/all")
    public Collection<Employee> allEmployeeByDepartment() {
        return employeeService.allEmployeeByDepartment();
    }
}