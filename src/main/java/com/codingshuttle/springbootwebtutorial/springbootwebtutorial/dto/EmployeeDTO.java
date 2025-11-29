package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotation.CheckPrimeNumberOrNot;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotation.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name of the employee cannot be blank")
    private String name;

    private AddressOfEmployee addressOfEmployee;

    @NotBlank(message = "Email of the employee cannot be blank")
    private String email;

    @NotNull(message = "Age of the employee cannot be blank")
    @CheckPrimeNumberOrNot
    private Integer age;

    @NotBlank(message = "Role of the employee cannot be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can either be USER or ADMIN")
    @EmployeeRoleValidation
    private String role; //ADMIN, USER

    @NotNull(message = "Salary of Employee should be not null")
    private Double salary;

    @PastOrPresent(message = "DateOfJoining field in Employee cannot be in the future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;
}
