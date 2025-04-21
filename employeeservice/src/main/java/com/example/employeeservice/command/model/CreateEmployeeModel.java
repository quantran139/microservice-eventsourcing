package com.example.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeModel {
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String LastName;
    @NotBlank(message = "Kin is mandatory")
    private String Kin;
}
