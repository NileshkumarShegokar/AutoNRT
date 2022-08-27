package com.ns.autoNRT.data.repository;

import com.ns.autoNRT.data.entity.Environment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnvironmentRepository extends JpaRepository<Environment, Integer> {

    @Query("select e from Environment e " +
        "where lower(e.name) like lower(concat('%', :searchTerm, '%')) " +
        "or lower(e.domain) like lower(concat('%', :searchTerm, '%'))")
    List<Environment> search(@Param("searchTerm") String searchTerm);
}
