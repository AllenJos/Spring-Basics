package com.example.demomysql.controller;

import com.example.demomysql.model.CreatePersonRequest;
import com.example.demomysql.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PersonController {

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @PostMapping("/person")
    public ResponseEntity createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest){
        logger.info("Create Person Request: {}", createPersonRequest);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("sample-response-header", "Person-response-type");
        return new ResponseEntity(new Person(), HttpStatus.CREATED);
    }

//    public static void main(String[] args) {
//        Person p = Person.builder()
//                .firstName("xyz")
//                .id(1)
//                .build();
//    }
}
