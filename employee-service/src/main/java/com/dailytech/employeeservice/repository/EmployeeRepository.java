package com.dailytech.employeeservice.repository;

import com.dailytech.employeeservice.controller.EmployeeController;
import com.dailytech.employeeservice.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();
    private static final Logger LOGGER =  LoggerFactory.getLogger(EmployeeController.class);

    public Employee addEmployee(Employee employee){
        employees.add(employee);
        return employee;
    }

    public Employee findById(Long id){
        return employees.stream().filter(employee -> employee.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Employee> findAll(){
        return employees;
    }

    public boolean deleteById(Long id) {
        Optional<Employee> departmentToRemove = employees.stream()
                .filter(employee -> employee.id().equals(id))
                .findFirst();
        LOGGER.info("Department deleteById: {}", id);

        if (departmentToRemove.isPresent()) {
            employees.remove(departmentToRemove.get());
            return true;
        } else {
            return false;
        }
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employees.stream()
                .filter(employee -> departmentId.equals(employee.departmentId()))
                .collect(Collectors.toList());
    }

}
