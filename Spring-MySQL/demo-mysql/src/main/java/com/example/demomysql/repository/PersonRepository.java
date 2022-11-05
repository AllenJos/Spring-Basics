package com.example.demomysql.repository;

import com.example.demomysql.DBConfig;
import com.example.demomysql.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

//    @Autowired
    private Connection connection;
    private Logger logger = LoggerFactory.getLogger(PersonRepository.class);

    @Autowired
    public PersonRepository(Connection connection){
        this.connection = connection;
        createTable();
    }

    public void createPerson(Person person) {
        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql:localhost:3306/jbdl36_person", "root", "password");
            // 2. Write Query.
            PreparedStatement statement = connection.prepareStatement("insert into person(first_name, " +
                    "last_name, age, dob) VALUES(?, ?, ?, ?)");
//            statement.setInt(1, person.getId());
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getDob());


            // 3. execute the query.

            int result = statement.executeUpdate();

//            logger.info("Insert statement result {}", result >= 1 ? true : false);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(){
        Statement statement = null;
        try{
            statement = connection.createStatement();
            statement.execute("create table if not exists person(id int primary key auto_increment, " +
                    "first_name varchar(30), last_name varchar(30), age int, dob varchar(12))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person getPersonById(int pId) {
        try{
            PreparedStatement statement = connection.prepareStatement("select * from Person where id=?");
            statement.setInt(1, pId);

            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Person person = getPersonFromResultSet(rs);
                return person;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Person> getPeople() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from person");
            List<Person> personList = new ArrayList<>();
            while(rs.next()){
               Person person = getPersonFromResultSet(rs);
                personList.add(person);
            }
            return personList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person getPersonFromResultSet(ResultSet rs) throws SQLException {
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        int id = rs.getInt("id");
        int age = rs.getInt("age");
        String dob = rs.getString("dob");

        Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .id(id)
                .age(age)
                .dob(dob)
                .build();

        return person;
    }

    public boolean deletePersonById(int pId) {
        try{
            PreparedStatement statement = connection.prepareStatement("delete from Person where id=?");
            statement.setInt(1, pId);

            int result = statement.executeUpdate();
            logger.info("Delete statement result: {} ", result>=1? true: false);
            return result>=1? true: false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
