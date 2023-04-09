package com.example.spring.repository;

import com.example.spring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findById(long id);
    List<Person> findByName(String name);
}
