package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.core.config.modelMapper.ModelMapperService;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.animal.AnimalUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.CursorResponse;
import com.ahmed.veterinaryManagementSystem.dto.response.animal.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.model.Animal;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AnimalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final AnimalService animalService;
    private final ModelMapperService modelMapper;

    public AnimalController(AnimalService animalService, ModelMapperService modelMapper) {
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        Animal saveAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        this.animalService.save(saveAnimal);
        return ResultInfo.created(this.modelMapper
                .forResponse()
                .map(saveAnimal, AnimalResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> findById(@PathVariable("id") Long id) {
        Animal animal = this.animalService.findById(id);
        return ResultInfo.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @GetMapping("/name/{name}")
    public ResultData<List<AnimalResponse>> findByName(@PathVariable("name") String name) {
        List<Animal> animals = this.animalService.findByName(name);
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultInfo.success(animalResponses);
    }

    @GetMapping()
    public ResultData<CursorResponse<AnimalResponse>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
        return ResultInfo.cursor(animalResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        Animal updatedAnimal = modelMapper.forRequest()
                .map(animalUpdateRequest, Animal.class);
        return ResultInfo.success(modelMapper.forResponse()
                .map(animalService.update(updatedAnimal), AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultInfo.ok();
    }

    @GetMapping("/customer/{customerId}")
    public ResultData<List<AnimalResponse>> findByOwnerId(@PathVariable("customer") Long ownerId) {
        List<Animal> animals = this.animalService.findByOwnerId(ownerId);
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultInfo.success(animalResponses);
    }

}