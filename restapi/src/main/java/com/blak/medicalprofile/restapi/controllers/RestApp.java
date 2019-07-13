package com.blak.medicalprofile.restapi.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.blak.medicalprofile")
@SpringBootApplication
public class RestApp {
    public static void main(String[] args) {
        SpringApplication.run(RestApp.class);
    }
}

