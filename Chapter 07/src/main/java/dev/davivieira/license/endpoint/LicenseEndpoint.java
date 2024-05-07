package dev.davivieira.license.endpoint;

import dev.davivieira.license.entity.License;
import dev.davivieira.license.service.LicenseService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/license")
@Tag(name = "License API", description = "It allows managing licenses")
public class LicenseEndpoint {

    @Inject
    private LicenseService licenseService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "It creates a license", description = "A new license is created and persisted into the database")
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "A new license has been successfully created"
            )
    })
    public void createLicense(License license) {
        licenseService.createLicense(license);
    }

    @Path("/all")
    @GET
    @Operation(summary = "It retrieves all licenses", description = "It returns all non-expired and expired licenses")
    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "List of licenses retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = List.class, type = SchemaType.ARRAY)
                    )
            )
    })
    public List<License> getAllLicenses() {
        return licenseService.getAllLicenses();
    }
}
