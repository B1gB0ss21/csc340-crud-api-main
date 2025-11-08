package com.csc340.demo.animal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.List;


@Controller
public class AnimalMvcController {

    private final AnimalService animalService;

    public AnimalMvcController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping(value = "/animals", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Animal> createAnimalJson(@RequestBody Animal animal) {
        Animal saved = animalService.addAnimal(animal, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    
    @GetMapping(value = "/animals/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Animal a = animalService.getAnimalById(id);
        if (a == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(a);
    }
    
    @GetMapping(value = "/animals", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Animal> listAnimalsJson(@RequestParam(value = "q", required = false) String q) {
        if (q != null && !q.isBlank()) {
            return animalService.getAnimalsByName(q);
        }
        return animalService.getAllAnimals();
    }

    @DeleteMapping("/animals/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        boolean deleted = animalService.deleteAnimal(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/animals/{id}")
    public String updateAnimalMvc(@PathVariable Long id,
                              @ModelAttribute Animal animal,
                              @RequestParam(value = "picture", required = false) MultipartFile picture) {
        animalService.updateAnimal(id, animal, picture);
        return "redirect:/index.html";
    }

    // Filter by type
    @GetMapping("/animals/type/{type}")
    public String listByType(@PathVariable String type, Model model) {
        model.addAttribute("animals", animalService.getAnimalsByType(type));
        model.addAttribute("selectedType", type);
        return "animals/list";
    }

    // Detail MVC view: changed path to avoid conflict with JSON GET
    @GetMapping("/animals/view/{id}")
    public String getOne(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalService.getAnimalById(id));
        return "animals/detail";
    }

    // Show create form
    @GetMapping("/animals/new")
    public String newForm(Model model) {
        model.addAttribute("animal", new Animal()); 
        return "animals/form";
    }

    // Handle create
    @PostMapping("/animals")
    public String createAnimal(@ModelAttribute Animal animal,
                         @RequestParam(value = "picture", required = false) MultipartFile picture) {
        animalService.addAnimal(animal, picture);
        return "redirect:/animals/";
    }

    // Show edit form
    @GetMapping("/animals/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalService.getAnimalById(id));
        return "animals/form";
    }

    // Handle update
    @PostMapping("/animals/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Animal animal,
                         @RequestParam(value = "picture", required = false) MultipartFile picture) {
        animalService.updateAnimal(id, animal, picture);
        return "redirect:/animals/view/" + id;
    }

    // Delete
    @GetMapping("/animals/delete/{id}")
    public String delete(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return "redirect:/animals";
    }
}