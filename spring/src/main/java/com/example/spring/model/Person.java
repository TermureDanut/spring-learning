package com.example.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

// model means who is making a request on the app, in this case is just a person
// jsonproperty tag is used to map json property names to the corresponding fields of the Person class.

public class Person {
    private final UUID id;
    private final String name;
    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
