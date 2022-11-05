package com.example.demomysql.controller;

import com.example.demomysql.model.CreatePersonRequest;
import com.example.demomysql.model.Person;
import com.example.demomysql.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @PostMapping("/person")
    public void createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest){
        logger.info("Create Person Request: {}", createPersonRequest);

        personService.createPerson(createPersonRequest);

//        MultiValueMap<String, String> headers = new HttpHeaders();
//        headers.add("sample-response-header", "Person-response-type");
//        return new ResponseEntity(new Person(), HttpStatus.CREATED);
    }

    @GetMapping("/person")
    public Person getPersonById(@RequestParam("id") int id){
        return  personService.getPerson(id);
    }

    @GetMapping("/person/all")
    public List<Person> getAllPerson(){
        return personService.getPeople();
    }

    @DeleteMapping("person/{id}")
    public Person deletePersonById(@PathVariable("id") int id) throws Exception {
        return personService.deletePersonById(id);
    }

//    public static void main(String[] args) {
//        Person p = Person.builder()
//                .firstName("xyz")
//                .id(1)
//                .build();
//    }
}
