package com.example.employeeservice.command.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDeletedEvent {
    private String id;
}
