package com.example.demojpa.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="first_name", length = 30) //default length is 255
    private String firstName;
    private String lastName;
    private Integer age;
    private String dob;

}

