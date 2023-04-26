package com.dailytech.departmentservice.repository;

import com.dailytech.departmentservice.controller.DepartmentController;
import com.dailytech.departmentservice.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class DepartmentRepository {
    private List<Department> departments = new ArrayList<>();
    private static final Logger LOGGER =  LoggerFactory.getLogger(DepartmentController.class);

    public Department addDepartment(Department department){
        departments.add(department);
        return department;
    }

    public Department findById(Long id){
        return departments.stream().filter(department -> department.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Department> findAll(){
        return departments;
    }

    public boolean deleteById(Long id) {
        Optional<Department> departmentToRemove = departments.stream()
                .filter(department -> department.getId().equals(id))
                .findFirst();
        LOGGER.info("Department deleteById: {}", id);

        if (departmentToRemove.isPresent()) {
            departments.remove(departmentToRemove.get());
            return true;
        } else {
            return false;
        }
    }

}
