package dev.davivieira.report.producer;

import dev.davivieira.report.entity.Person;
import dev.davivieira.report.entity.Organization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.davivieira.report.vo.Country.AUSTRALIA;
import static dev.davivieira.report.vo.Country.CANADA;

/**
 * It produces sample that we can use to generate reports
 */
public class DataProducer {

    public static List<Person> getPersons() {
        return List.of(
                new Person("Diego", AUSTRALIA, 25),
                new Person("Jerry", CANADA, 65),
                new Person("Smith", AUSTRALIA, 45),
                new Person("Smith", AUSTRALIA, 45),
                new Person("Diana", CANADA, 18)
        );
    }

    public static List<Organization> getOrganizations() {
        return List.of(
                new Organization("ACME", AUSTRALIA),
                new Organization("FOO", CANADA)
        );
    }
}
