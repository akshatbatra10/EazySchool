package com.project.eazy_school.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Profile {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile Number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Address must not be blank")
    @Size(min = 5, message = "Address must be at least 5 characters long")
    private String address1;

    private String address2;

    @NotBlank(message = "City must not be blank")
    @Size(min = 5, message = "City must be at least 5 characters long")
    private String city;

    @NotBlank(message = "State must not be blank")
    @Size(min = 5, message = "State must be at least 5 characters long")
    private String state;

    @NotBlank(message = "Zip Code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{6})", message = "Zip Code must be 6 digits")
    private String zipCode;
}
