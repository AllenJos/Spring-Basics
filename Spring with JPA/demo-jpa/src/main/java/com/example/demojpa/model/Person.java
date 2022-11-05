package com.example.demojpa.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Person {
    @Id
    private Integer id;
    @Column(name="first_name", length = 30) //default length is 255
    private String firstName;
    private String lastName;
    private Integer age;
    private String dob;

}

