package com.project.eazy_school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJdbcRepositories("com.project.eazy_school.repository")
@EntityScan("com.project.eazy_school.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class EazySchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(EazySchoolApplication.class, args);
    }

}
