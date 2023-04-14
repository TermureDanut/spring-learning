package com.example.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.spring.controller.PersonController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.spring.model.Person;
import com.example.spring.repository.PersonRepository;

class PersonControllerTest {
    @InjectMocks
    private PersonController personController;
    @Mock
    private PersonRepository personRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John"));
        persons.add(new Person("Jane"));
        when(personRepository.findAll()).thenReturn(persons);
        ResponseEntity<List<Person>> response = personController.getAllPersons(null);
        verify(personRepository, times(1)).findAll();
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().size() == 2;
    }
    @Test
    void testGetPersonById() {
        Person person = new Person("John");
        person.setId(1L);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        ResponseEntity<Person> response = personController.getPersonById(1L);
        verify(personRepository, times(1)).findById(1L);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().getId() == 1L;
        assert response.getBody().getName().equals("John");
    }
    @Test
    void testCreatePerson() {
        Person person = new Person("John");
        when(personRepository.save(any(Person.class))).thenReturn(person);
        ResponseEntity<Person> response = personController.createPerson(person);
        verify(personRepository, times(1)).save(any(Person.class));
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() != null;
        assert response.getBody().getName().equals("John");
    }
    @Test
    public void testUpdatePerson() {
        Person person = new Person();
        person.setId(1L);
        person.setName("John");
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(any(Person.class))).thenReturn(person);
        Person updatedPerson = new Person();
        updatedPerson.setName("Jane");
        ResponseEntity<Person> response = personController.updatePerson(1L, updatedPerson);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Jane", response.getBody().getName());
        verify(personRepository, times(1)).findById(1L);
        verify(personRepository, times(1)).save(person);
    }
    @Test
    void testDeletePersonById() {
        long personId = 1L;
        doNothing().when(personRepository).deleteById(personId);
        ResponseEntity<HttpStatus> response = personController.deletePersonById(personId);
        verify(personRepository, times(1)).deleteById(personId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void testDeleteAllPersons() {
        doNothing().when(personRepository).deleteAll();
        ResponseEntity<HttpStatus> response = personController.deleteAllPersons();
        verify(personRepository, times(1)).deleteAll();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}