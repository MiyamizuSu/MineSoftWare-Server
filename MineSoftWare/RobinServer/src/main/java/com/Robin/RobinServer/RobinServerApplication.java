package com.Robin.RobinServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class RobinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobinServerApplication.class, args);
    }

}
