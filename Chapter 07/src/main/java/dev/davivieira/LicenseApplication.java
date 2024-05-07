package dev.davivieira;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("api")
@OpenAPIDefinition(
        info = @Info(title = "License Management", version = "1.0.0")
)
public class LicenseApplication extends Application {

}