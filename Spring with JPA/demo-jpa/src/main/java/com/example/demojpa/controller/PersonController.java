package com.example.demojpa.controller;

import com.example.demojpa.exception.PersonNotFoundException;
import com.example.demojpa.model.Author;
import com.example.demojpa.model.CreatePersonRequest;
import com.example.demojpa.model.Person;
import com.example.demojpa.repository.AuthorRepository;
import com.example.demojpa.service.PersonService;
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

    @Autowired
    AuthorRepository authorRepository;

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @PostMapping("/person")
    public void createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest){
        logger.info("Create Person Request: {}", createPersonRequest);

        personService.createPerson(createPersonRequest);

    }

    @GetMapping("/person")
    public Person getPersonById(@RequestParam("id") int id) throws PersonNotFoundException {
        return  personService.getPerson(id);
    }

    @GetMapping("/person/all")
    public List<Person> getAllPerson(){
        return personService.getPeople();
    }

    @PostMapping("/author")
    public Author createAuthor(@RequestParam("name") String name, @RequestParam("age") Integer age){
        Author author = Author.builder()
                .authorName(name)
                .age(age)
                .build();

        return authorRepository.save(author);
    }

//    @DeleteMapping("person/{id}")
//    public Person deletePersonById(@PathVariable("id") int id) throws Exception {
//        return personService.deletePersonById(id);
//    }

}
