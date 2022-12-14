package com.fargate.paf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Sachith Dickwella
 */
@ComponentScan({
        "com.auspost.paf.repository",
        "com.auspost.paf.controller"
})
@EnableJpaRepositories({ "com.auspost.paf.repository" })
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
