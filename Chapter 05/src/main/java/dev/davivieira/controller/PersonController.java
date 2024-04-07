package dev.davivieira.controller;


import dev.davivieira.entity.Person;
import dev.davivieira.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    private List<Person> all() {
        return personService.getAllPersons();
    }

    @PostMapping("/person")
    private void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping("/person/{email}")
    private Person getPerson(@PathVariable String email) throws Exception {
        return personService.getPerson(email).orElseThrow(() -> new Exception("Person not found"));
    }
}
