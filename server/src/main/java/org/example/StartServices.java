package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class StartServices {
    public static void main(String[] args) {
        SpringApplication.run(StartServices.class, args);
    }

    @Bean(name="props")
    @Primary
    public Properties dbProperties() {
        Properties serverProps=new Properties();
        try {
            serverProps.load(StartServices.class.getResourceAsStream("/bd.config"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find chatserver.properties "+e);
        }
        return serverProps;
    }
}