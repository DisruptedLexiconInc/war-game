package com.hive.wargame.angularjs.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.hive.wargame.angularjs.server")
@EntityScan(basePackages = { "com.hive.wargame.angularjs.server.dao" })
@EnableJpaRepositories(basePackages = { "com.hive.wargame.angularjs.server.dao" })
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }
}
