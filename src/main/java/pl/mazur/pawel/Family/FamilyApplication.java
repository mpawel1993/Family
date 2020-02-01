package pl.mazur.pawel.Family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FamilyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyApplication.class, args);
        System.out.println("Swagger : https://localhost:8082/swagger-ui.html");
    }
}