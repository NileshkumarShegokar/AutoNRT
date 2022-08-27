package com.ns.autoNRT.data.generator;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

import com.ns.autoNRT.data.entity.Environment;
import com.ns.autoNRT.data.repository.EnvironmentRepository;
import com.vaadin.flow.spring.annotation.SpringComponent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(EnvironmentRepository environmentRepository) {

        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (environmentRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");




            logger.info("... generating 50 Contact entities...");
            ExampleDataGenerator<Environment> contactGenerator = new ExampleDataGenerator<>(Environment.class,
                    LocalDateTime.now());
            contactGenerator.setData(Environment::setName, DataType.FIRST_NAME);
            contactGenerator.setData(Environment::setDomain, DataType.LAST_NAME);

            Random r = new Random(seed);
            List<Environment> environments = contactGenerator.create(50, seed).stream().peek(contact -> {

            }).collect(Collectors.toList());

            environmentRepository.saveAll(environments);

            logger.info("Generated demo data");
        };
    }

}
