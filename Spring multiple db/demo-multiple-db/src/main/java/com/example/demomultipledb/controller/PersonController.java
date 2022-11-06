package com.example.demomultipledb.controller;

import com.example.demomultipledb.exception.PersonNotFoundException;
import com.example.demomultipledb.authordb.Author;
import com.example.demomultipledb.model.CreatePersonRequest;
import com.example.demomultipledb.persondb.Person;
import com.example.demomultipledb.authordb.AuthorRepository;
import com.example.demomultipledb.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
