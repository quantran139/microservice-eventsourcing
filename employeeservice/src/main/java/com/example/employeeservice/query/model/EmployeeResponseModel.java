package com.example.employeeservice.query.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseModel {
    private String id;
    private String firstName;
    private String LastName;
    private String Kin;
    private Boolean isDisciplined;
}
