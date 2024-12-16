package com.salesianostriana.dam.monumentosv3;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(description = "Una API de Monumentos del Mundo",
        version = "1.0",
        contact = @Contact(
                email = "daniel1martinezleon@gmail.com",
                name = "Daniel Martínez León"),
        license = @License(
                name = "Libre uso"),
        title = "API sobre monumentos")
)
public class MonumentosV3Application {

    public static void main(String[] args) {
        SpringApplication.run(MonumentosV3Application.class, args);
    }

}
