package com.ahmed.veterinaryManagementSystem.dto.response;

import lombok.Data;
/**
 * The CustomerResponse class represents the response object for customer information.
 * It contains fields such as customer id, name, phone number, email address, address, and city.
 */
@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}