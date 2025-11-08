package com.csc340.demo.animal;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    public List<Animal> getAnimalsByName(String name) {
        if (name == null || name.isBlank()) {
            return animalRepository.findAll();
        }
        return animalRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Animal> getAnimalsByType(String type) {
        if (type == null || type.isBlank()) {
            return animalRepository.findAll();
        }
        return animalRepository.findByTypeIgnoreCase(type);
    }
  
    public Animal addAnimal(Animal animal, MultipartFile picture) {
        if (picture != null && !picture.isEmpty()) {
            String fileName = saveImage(picture);
            animal.setImageUrl("/images/" + fileName);
        }
        return animalRepository.save(animal);
    }

    public boolean deleteAnimal(Long id) {
        if (!animalRepository.existsById(id)) {
            return false;
        } else {
            animalRepository.deleteById(id);
            return true;
        }
    }

    // changed to return Animal (or null) to match controller usage
    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal updateAnimal(Long id, Animal updated, MultipartFile picture) {
        Animal existing = getAnimalById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Animal not found: " + id);
        }
        existing.setName(updated.getName());
        existing.setType(updated.getType());
        existing.setAge(updated.getAge());
        existing.setDescription(updated.getDescription());

        if (picture != null && !picture.isEmpty()) {
            String fileName = saveImage(picture);
            existing.setImageUrl("/images/" + fileName);
        }
        return animalRepository.save(existing);
    }

    private String saveImage(MultipartFile picture) {
        try {
            String fileName = System.currentTimeMillis() + "_" + picture.getOriginalFilename();
            Path imagesDir = Path.of("src/main/resources/static/images");
            Files.createDirectories(imagesDir);
            try (InputStream in = picture.getInputStream()) {
                Files.copy(in, imagesDir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            }
            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }
}