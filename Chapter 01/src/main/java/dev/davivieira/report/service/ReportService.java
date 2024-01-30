package dev.davivieira.report.service;

import dev.davivieira.report.entity.Organization;
import dev.davivieira.report.entity.Person;
import dev.davivieira.report.entity.Report;
import dev.davivieira.report.exception.ReportGenerationException;
import dev.davivieira.report.producer.DataProducer;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * It generates a report and save it as a text file.
 */
public class ReportService {

    private static final Logger logger = Logger.getLogger(dev.davivieira.report.service.ReportService.class.getName());

    public void generateReport() {
        Map<Organization, Set<Person>> reporData = null;
        Report report = null;
        logger.log(Level.INFO, "Starting to generate the report");
        try {
            reporData = generateReportData();
            report = new Report(Instant.now(), reporData);
            saveReportToFile(report);
        } catch (ReportGenerationException | IOException e) {
            logger.log(Level.SEVERE, "Failure to generate e", e.getStackTrace());
        }
        logger.log(Level.SEVERE, "Report generated with success");
    }

    private Map<Organization, Set<Person>> generateReportData() throws ReportGenerationException {
        var organizations = DataProducer.getOrganizations();
        var persons = removeDuplicates(DataProducer.getPersons());
        var reporData = mapOrgWithPersonByCountry(organizations, persons);
        return reporData;
    }

    private void saveReportToFile(Report report) throws IOException {
        Path newFile = Path.of("report.txt");
        Files.writeString(
                newFile,
                report.toString(),
                StandardOpenOption.CREATE);
    }

    private Set<Person> removeDuplicates (List<Person> persons) {
        return persons.stream().collect(Collectors.toSet());
    }

    /**
     * It groups persons to their organizations based on the country where they are located.
     * @param organizations
     * @param persons
     * @return
     */
    private Map<Organization, Set<Person>> mapOrgWithPersonByCountry(List<Organization> organizations, Set<Person> persons) {
        return organizations
                .stream()
                .map(organization -> {
                    var personsByOrgCountry = persons
                            .stream()
                            .filter(person -> person.country().equals(organization.country()))
                            .collect(Collectors.toSet());
                    return Map.entry(organization, personsByOrgCountry);
                })
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }
}
