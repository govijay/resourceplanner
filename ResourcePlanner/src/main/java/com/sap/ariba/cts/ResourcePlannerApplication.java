package com.sap.ariba.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ResourcePlannerApplication {

  public static void main(String[] args) {
    SpringApplication.run(ResourcePlannerApplication.class, args);
  }

}
