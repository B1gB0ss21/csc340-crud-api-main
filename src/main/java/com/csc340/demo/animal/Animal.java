package com.csc340.demo.animal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Type is mandatory")
    private String type;

    private Integer age;

    @Column(length = 2000)
    private String description;

    private String imageUrl;

    public Animal() {}

    public Animal(String name, String type, Integer age, String description, String imageUrl) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // primary id accessor (mapped to DB animal_id)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // keep old-style accessors to avoid breaking code that expects getAnimalId()
    public Long getAnimalId() { return id; }
    public void setAnimalId(Long animalId) { this.id = animalId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}