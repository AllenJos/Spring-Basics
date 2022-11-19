package com.example.demoredis.controller;

import com.example.demoredis.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final String PERSON_KEY_PREFIX = "per::";
    private static final String PERSON_LIST_KEY = "per_list::";
    private static final String PERSON_HASH_KEY = "per_hash::";

    //String Operations Start:
    @PostMapping("/string/person")
    public void savePerson(@RequestBody Person person){
        String key = getKey(person.getId());
        redisTemplate.opsForValue().set(key, person);
    }

    @GetMapping("/string/person")
    public Person getPerson(@RequestParam("id") long personId){
        return (Person) redisTemplate.opsForValue().get(getKey(personId));
    }
    //String Operations End.


    //List Operations Start::
    @PostMapping("/lpush/person")
    public void lpush(@RequestBody Person person){
        //List lpush operation
        redisTemplate.opsForList().leftPush(PERSON_LIST_KEY, person);
    }

    @PostMapping("/rpush/person")
    public void rpush(@RequestBody Person person){
        //List lpush operation
        redisTemplate.opsForList().rightPush(PERSON_LIST_KEY, person);
    }

    @GetMapping("/lrange/person")
    public List<Person> lrange(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                               @RequestParam(value = "end", required = false, defaultValue = "-1") int end){
        return redisTemplate.opsForList().range(PERSON_LIST_KEY, start, end)
                .stream().map(person->(Person)person)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/lpop/person")
    public Person lpop(){
        return (Person) redisTemplate.opsForList().leftPop(PERSON_LIST_KEY);
    }

    @DeleteMapping("/rpop/person")
    public Person rpop(){
        return (Person) redisTemplate.opsForList().rightPop(PERSON_LIST_KEY);
    }
//
//    @DeleteMapping("/lpop/person")
//    public List<Person> lpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
//        return redisTemplate.opsForList().leftPop(PERSON_LIST_KEY, count)
//                .stream().map(person -> (Person)person)
//                .collect(Collectors.toList());
//    }
//
//    @DeleteMapping("/rpop/person")
//    public List<Person> rpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
//        return redisTemplate.opsForList().rightPop(PERSON_LIST_KEY, count)
//                .stream().map(person -> (Person)person)
//                .collect(Collectors.toList());
//    }
    //List Operations End.


    //Hashes Operations Start:::
    @PostMapping("/hash/person")
    public void savePersonInHash(@RequestBody List<Person> personList){

        personList.stream()
                .filter(person -> person.getId()!=0)
                .forEach(person -> {
                    Map map = objectMapper.convertValue(person, Map.class);
                    redisTemplate.opsForHash().putAll(getHashKey(person.getId()), map);
                });
    }

    //localhost:8080/hash/person/all?ids=1,2,3
    @GetMapping("/hash/person/all")
    public List<Person> getPerson(@RequestParam("ids") List<Long> ids){
        return ids.stream().map(id-> redisTemplate.opsForHash().entries(getHashKey(id)))
                .map(entryMap -> objectMapper.convertValue(entryMap, Person.class))
                .collect(Collectors.toList());
    }



    public String getKey(long personId){
        return PERSON_KEY_PREFIX+personId;
    }

    public String getHashKey(long id){
        return PERSON_HASH_KEY+id;
    }
}
