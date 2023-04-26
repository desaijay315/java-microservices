package com.dailytech.employeeservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record Employee(
        Long id,
        Long departmentId,
        @NotNull(message = "Employee name cannot be null")
        @NotBlank(message = "Employee name cannot be blank")
        @Size(min = 2, max = 100, message = "Employee name must be between 2 and 100 characters")
        String name,
        @Min(value = 18, message = "Employee must be at least 18 years old")
        int age,
        @NotNull(message = "Employee position cannot be null")
        @NotBlank(message = "Employee position cannot be blank")
        @Size(min = 2, max = 100, message = "Employee position must be between 2 and 100 characters")
        String position) {
}
