package com.csc340.demo.dog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {

    // Find by breed (category)
    List<Dog> findByBreed(String breed);

    // Find dogs whose name contains a substring (case insensitive)
    @Query("SELECT d FROM Dog d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Dog> searchByName(String name);
}
