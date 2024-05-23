package com.ahmed.veterinaryManagementSystem.dto.response;


import lombok.Data;

import java.time.LocalDate;

/**
 * The AnimalResponse class represents the response data containing information about an animal in the veterinary management system.
 * It includes fields such as the animal's ID, name, species, breed, gender, color, date of birth, and the ID of the customer who owns the animal.
 * This class is used to encapsulate the details of an animal when sending responses to client applications or services.
 */

@Data
public class AnimalResponse {
    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Long customerId;
}
