package com.blak.medicalprofile.restapi.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.blak.medicalprofile.services",
})
public class RestApp {
    public static void main(String[] args) {
        SpringApplication.run(RestApp.class);
    }
}
