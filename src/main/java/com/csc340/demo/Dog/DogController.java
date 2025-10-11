package com.csc340.demo.dog;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    // Get all dogs
    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    // Get dog by ID
    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable Long id) {
        return dogService.getDogById(id);
    }

    // Add new dog
    @PostMapping
    public Dog addDog(@RequestBody Dog dog) {
        return dogService.addDog(dog);
    }

    // Update dog by ID
    @PutMapping("/{id}")
    public Dog updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        return dogService.updateDog(id, dog);
    }

    // Delete dog by ID
    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
    }

    // Get dogs by breed
    @GetMapping("/breed/{breed}")
    public List<Dog> getDogsByBreed(@PathVariable String breed) {
        return dogService.getDogsByBreed(breed);
    }

    // Search dogs by name substring
    @GetMapping("/search")
    public List<Dog> searchDogsByName(@RequestParam String name) {
        return dogService.searchDogsByName(name);
    }
}
