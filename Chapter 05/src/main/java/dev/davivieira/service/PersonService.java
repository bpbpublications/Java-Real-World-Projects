package dev.davivieira.service;

import dev.davivieira.entity.Person;
import dev.davivieira.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

   @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> getPerson(String email) {
        return personRepository.findByEmail(email);
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }
}
