package dev.davivieira.report.entity;

import dev.davivieira.report.vo.Country;

import java.util.Objects;

public record Person(String name, Country country, int age) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
