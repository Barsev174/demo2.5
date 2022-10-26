package com.homeWork.demo.service;

import com.homeWork.demo.service.EmployeeServiceImpl;
import com.homeWork.demo.exception.EmployeeAlreadyAddedException;
import com.homeWork.demo.exception.EmployeeNotFoundException;
import com.homeWork.demo.model.Employee;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;



import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class EmployeeServiceImplTest {

    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl();


    @ParameterizedTest
    @MethodSource("params")
    public void addTest(String firstName,
                        String lastName,
                        int department,
                        double salary) {

        Employee expected = new Employee(firstName, lastName, department, salary);

        assertThat(employeeService.getAll().isEmpty());
        assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(firstName, lastName, department, salary));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removeNegativeTest(String firstName,
                                   String lastName,
                                   int department,
                                   double salary) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("test", "test"));

        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void removePositiveTest(String firstName,
                                   String lastName,
                                   int department,
                                   double salary) {
        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);
        assertThat(employeeService.remove(firstName, lastName)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("params")
    public void fineNegativeTest(String firstName,
                                 String lastName,
                                 int department,
                                 double salary) {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test1", "test1"));

        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("test", "test"));
    }

    public static Stream< Arguments> params() {
        return Stream.of(
                Arguments.of("Sergey", "Sergeev", 1, 70000),
                Arguments.of("Andrey", "Andreev", 2, 80000),
                Arguments.of("Alex", "Alexandrov", 1, 75000)
        );
    }

    @ParameterizedTest
    @MethodSource("params")
    public void findPositiveTest(String firstName,
                                   String lastName,
                                   int department,
                                   double salary) {
        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);
        assertThat(employeeService.find(firstName, lastName)).isEqualTo(expected);

    }
}
