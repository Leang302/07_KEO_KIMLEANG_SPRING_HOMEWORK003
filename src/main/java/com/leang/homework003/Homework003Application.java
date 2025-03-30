package com.leang.homework003;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Event Ticketing System",
                description = "Efficiently manage events with our streamlined ticketing system."
        )
)
public class Homework003Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework003Application.class, args);
    }

}
