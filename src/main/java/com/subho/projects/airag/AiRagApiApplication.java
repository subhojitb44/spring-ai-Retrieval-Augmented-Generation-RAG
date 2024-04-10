package com.subho.projects.airag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * Created by subho
 * Date: 4/10/2024
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.subho.projects"})
public class AiRagApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiRagApiApplication.class, args);
    }
}
