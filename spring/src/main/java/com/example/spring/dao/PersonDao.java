package com.example.spring.dao;

import com.example.spring.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// The PersonDao interface in this example is created to define a contract for the data access operations that can be performed on the Person entity.
// That means that in this class we define all of the operations(methods) that we can apply on a Person entity.
// DAO - data access oobject

public interface PersonDao {
    int insertPerson(UUID id, Person person);
    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    List<Person> selectAllPeople();
    Optional<Person> selectPersonById(UUID id);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id, Person person);

}
