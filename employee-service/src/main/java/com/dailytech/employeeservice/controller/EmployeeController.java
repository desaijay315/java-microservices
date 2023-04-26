package com.dailytech.employeeservice.controller;

import com.dailytech.employeeservice.model.Employee;
import com.dailytech.employeeservice.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository repository;

    @PostMapping
    public ResponseEntity<Employee> add(@Valid @RequestBody Employee employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("Validation errors: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(null);
        }

        Employee employee = new Employee(
                employeeDTO.id(),
                employeeDTO.departmentId(),
                employeeDTO.name(),
                employeeDTO.age(),
                employeeDTO.position()
        );

        LOGGER.info("Employee add: {}", employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.addEmployee(employee));
    }

    @GetMapping
    public List<Employee> findAll() {
        LOGGER.info("Employee find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable Long departmentId) {
        LOGGER.info("Employee find by department: departmentId={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable Long id) {
        LOGGER.info("Employee delete: id={}", id);
        return repository.deleteById(id);
    }
}
