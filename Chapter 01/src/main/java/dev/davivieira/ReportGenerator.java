package dev.davivieira;

import dev.davivieira.report.service.ReportService;

public class ReportGenerator {

    public static void main(String... args) {
        new ReportService().generateReport();
    }
}
