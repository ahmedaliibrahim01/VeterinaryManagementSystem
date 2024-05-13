package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.model.Animal;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal save(@RequestBody Animal animal) {
        return this.animalService.save(animal);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal findById(@PathVariable("id") Long id) {
        return this.animalService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> findAll() {
        return this.animalService.findAll();
    }

    @PutMapping()
    public Animal update(@RequestBody Animal animal) {
        return this.animalService.update(animal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
    }
}