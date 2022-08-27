package com.ns.autoNRT.data.service;

import com.ns.autoNRT.data.entity.Environment;
import com.ns.autoNRT.data.repository.EnvironmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentService {

    private final EnvironmentRepository environmentRepository;

    public EnvironmentService(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    public List<Environment> findAllEnvironments(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return environmentRepository.findAll();
        } else {
            return environmentRepository.search(stringFilter);
        }
    }

    public long countEnvironments() {
        return environmentRepository.count();
    }

    public void deleteEnvironment(Environment environment) {
        environmentRepository.delete(environment);
    }

    public void saveEnvironment(Environment environment) {
        if (environment == null) {
            System.err.println("Environment is null. Are you sure you have connected your form to the application?");
            return;
        }
        environmentRepository.save(environment);
    }


}
