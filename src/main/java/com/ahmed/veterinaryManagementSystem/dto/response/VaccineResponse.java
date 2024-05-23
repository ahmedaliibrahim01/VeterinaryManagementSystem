package com.ahmed.veterinaryManagementSystem.dto.response;

import lombok.Data;

import java.time.LocalDate;
/**
 * The VaccineResponse class represents the response data for retrieving information about a vaccine in the veterinary management system.
 * It contains fields such as the vaccine's ID, name, code, start and finish dates of protection, and the ID of the animal to which the vaccine is administered.
 * This class is used to encapsulate vaccine data when responding to client requests.
 */
@Data
public class VaccineResponse {
    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private Long animalId;
}
