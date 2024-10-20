package dev.davivieira.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/report")
public class ReportEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportEndpoint.class);

    private final RestTemplate restTemplate;

    @Autowired
    public ReportEndpoint(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${inventoryService.baseUrl}")
    private String baseUrl;

    @GetMapping(path = "/generate")
    public List<String> generateReport() {
        LOGGER.info("Generating report");
        return getInventoryItems();
    }

    private List<String> getInventoryItems() {
        LOGGER.info("Getting inventory items");
        ResponseEntity<String[]> response = restTemplate.getForEntity(baseUrl + "/inventory", String[].class);
        return List.of(Objects.requireNonNull(response.getBody()));
    }
}
