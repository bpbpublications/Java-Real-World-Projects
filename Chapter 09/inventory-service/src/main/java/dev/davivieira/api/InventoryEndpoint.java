package dev.davivieira.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryEndpoint.class);

    @GetMapping(path = "/all")
    public List<String> getAllInventory() {
        LOGGER.info("Getting all inventory items");
        return List.of("Inventory Item 1", "Inventory Item 2", "Inventory Item 3");
    }
}
