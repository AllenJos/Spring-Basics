package com.example.demojpa.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreatePersonRequest {

    @NotBlank(message = "First name cannot be empty.")
    private String firstName;
    private String lastName;
    @NotBlank(message = "DOB cannot be empty.")
    private String dob;


    public Person to(){
        Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dob(dob)
                .build();

        return person;
    }
}

