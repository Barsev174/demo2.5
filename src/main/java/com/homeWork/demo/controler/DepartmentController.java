package com.homeWork.demo.controler;

import com.homeWork.demo.model.Employee;
import com.homeWork.demo.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping("/max-salary")
    public Employee findMaxSalary(@RequestParam int department) {
        return departmentService.findMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee findMinSalary(@RequestParam int department) {
        return departmentService.findMinSalary(department);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> allEmployeeOfDepartments(@RequestParam("departmentId") int department) {
        return departmentService.allEmployeeOfDepartments(department);
    }

    @GetMapping("/departments/all")
    public Map<Integer, List<Employee>> allEmployeeByDepartment() {
        return departmentService.allEmployeeByDepartment();
    }
}
