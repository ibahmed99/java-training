package server;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import server.Auth.Controller.AccountService;
import server.Auth.model.Role;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner run(AccountService accountService) {
        return args -> {
//            long id = 1;
            accountService.saveRole(new Role(null, "USER"));
        };
    }

}