package com.csc340.demo.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    List<Animal> findByAge(Integer age);
    List<Animal> findByNameContainingIgnoreCase(String name);
    List<Animal> findByTypeIgnoreCase(String type);

    @Query(value = "select * from animals a where lower(a.name) like lower(concat('%', :q, '%'))",
           nativeQuery = true)
    List<Animal> searchByName(@Param("q") String q);
}
