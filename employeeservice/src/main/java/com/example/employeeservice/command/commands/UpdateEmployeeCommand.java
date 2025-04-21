package com.example.employeeservice.command.commands;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeCommand {
    @TargetAggregateIdentifier
    private String id;
    private String firstName;
    private String LastName;
    private String Kin;
    private Boolean isDisciplined;
}
