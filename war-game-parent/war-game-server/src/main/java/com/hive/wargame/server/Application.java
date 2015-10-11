package com.hive.wargame.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hive.wargame.server.dao.User;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.hive.wargame.server")
@ImportResource("classpath:populator.xml")
@EntityScan(basePackages = { "com.hive.wargame.server.dao" })
@EnableJpaRepositories(basePackages = { "com.hive.wargame.server.dao" })
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        User user = new User();
        user.setPassword("password");
        user.setUsername("gsugambit");
        System.out.println(user.toString());
    }
}
