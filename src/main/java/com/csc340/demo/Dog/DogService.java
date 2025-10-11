package com.csc340.demo.dog;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public Dog getDogById(Long id) {
        return dogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dog not found with id " + id));
    }

    public Dog addDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public Dog updateDog(Long id, Dog updatedDog) {
        Dog existingDog = getDogById(id);
        existingDog.setName(updatedDog.getName());
        existingDog.setDescription(updatedDog.getDescription());
        existingDog.setBreed(updatedDog.getBreed());
        existingDog.setAge(updatedDog.getAge());
        existingDog.setActiveDate(updatedDog.getActiveDate());
        return dogRepository.save(existingDog);
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }

    public List<Dog> getDogsByBreed(String breed) {
        return dogRepository.findByBreed(breed);
    }

    public List<Dog> searchDogsByName(String name) {
        return dogRepository.searchByName(name);
    }
}
