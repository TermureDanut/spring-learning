package com.example.spring.service;

import com.example.spring.dao.PersonDao;
import com.example.spring.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
    The @Service annotation is used to mark the class as a service component which means that spring knows that this class is a link between the presentation layer
    and the DAO. With this marking the class becomes eligible for Spring's dependency injection mechanism which allows to have its dependencies.
    A dependency is a mechanism that another object depends on to function properly. In this case the PersonService lets the PersonDao work on
    data access operations
    The @Autowired annotation on the constructor indicates that Spring should automatically inject an implementation of the PersonDao interface into the constructor when
    creating an instance of the PersonService class.
    The @Qualifier annotation is used to specify which implementation of the PersonDao interface should be injected (in this case, the "fakeDao" implementation).
 */
@Service
public class PersonService {
    private final PersonDao personDao;
    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }
    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }
    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }
    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }
    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }
    public int updatePerson(UUID id, Person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }
}
