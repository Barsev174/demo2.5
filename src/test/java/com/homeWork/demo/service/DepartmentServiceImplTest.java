package com.homeWork.demo.service;


import com.homeWork.demo.exception.EmployeeNotFoundException;
import com.homeWork.demo.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void beforeEach() {
        List<Employee> employees = List.of(
                new Employee("Александр", "Александров", 1, 70000),
                new Employee("Андрей", "Андреев", 1, 80000),
                new Employee("Алексей", "Алексеев", 1, 75000),
                new Employee("Тимур", "Тимуров", 2, 72000),
                new Employee("Артем", "Аремов", 2, 78000)
        );
        when(employeeService.getAll()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("employeeWithMaxSalaryParam")
    public void employeeWithMaxSalaryPositiveTest(int department, Employee expect) {
        Assertions.assertThat(departmentService.findMaxSalary(department)).isEqualTo(expect);
    }

    @Test
    public void employeeWithMaxSalaryNegativeTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findMaxSalary(3));
    }


    @ParameterizedTest
    @MethodSource("employeeWithMinSalaryParam")
    public void employeeWithMainSalaryPositiveTest(int department, Employee expected) {
        Assertions.assertThat(departmentService.findMinSalary(department)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMinSalaryNegativeTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findMinSalary(3));
    }


    @ParameterizedTest
    @MethodSource("employeeFromDepartmentParam")
    public void employeeFromDepartmentPositiveTest(int department, List<Employee> expected) {
        Assertions.assertThat(departmentService.allEmployeeOfDepartments(department)).containsExactlyElementsOf(expected);
    }


    @Test
    public void employeeGroupByDepartmentTest() {
        Assertions.assertThat(departmentService.allEmployeeByDepartment()).containsAllEntriesOf(
                Map.of(1, List.of(new Employee("Александр", "Александров", 1, 70000), new Employee("Андрей", "Андреев", 1, 80000), new Employee("Алексей", "Алексеев", 1, 75000)),
                        2, List.of(new Employee("Тимур", "Тимуров", 2, 72000), new Employee("Артем", "Аремов", 2, 78000))
                )
        );
    }

    public static Stream<Arguments> employeeWithMaxSalaryParam() {
        return Stream.of(
                Arguments.of(1, new Employee("Андрей", "Андреев", 1, 80000)),
                Arguments.of(2, new Employee("Артем", "Аремов", 2, 78000))
        );
    }

    public static Stream<Arguments> employeeWithMinSalaryParam() {
        return Stream.of(
                Arguments.of(1, new Employee("Александр", "Александров", 1, 70000)),
                Arguments.of(2, new Employee("Тимур", "Тимуров", 2, 72000))
        );
    }

    public static Stream<Arguments> employeeFromDepartmentParam() {
        return Stream.of(
                Arguments.of(1, List.of(new Employee("Александр", "Александров", 1, 70000), new Employee("Андрей", "Андреев", 1, 80000), new Employee("Алексей", "Алексеев", 1, 75000))),
                        Arguments.of(2, List.of(new Employee("Тимур", "Тимуров", 2, 72000), new Employee("Артем", "Аремов", 2, 78000))),
                                Arguments.of(3, Collections.emptyList())
                                );
    }


}

