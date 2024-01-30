package dev.davivieira.report.entity;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record Report(Instant creationInstant, Map<Organization, Set<Person>> orgToPersons) {}
