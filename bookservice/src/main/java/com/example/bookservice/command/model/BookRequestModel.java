package com.example.bookservice.command.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestModel {
    private String id;

    @NotBlank(message = "name is mandatory")
    @Size(min = 2, max = 30, message = "name must be between 2 and 30 characters")
    private String name;

    @NotBlank(message = "author is mandatory")
    private String author;

    private Boolean isReady;
}
