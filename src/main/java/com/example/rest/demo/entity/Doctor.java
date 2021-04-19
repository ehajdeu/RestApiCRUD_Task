package com.example.rest.demo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = "First name may not be empty")
    @Size(min = 3, max = 15)
    private String firstName;

    @NotEmpty (message = "Last name may not be empty")
    @Size(min = 3, max = 15)
    private String lastName;

    @NotEmpty (message = "Specialty may not be empty")
    @Size(min = 3, max = 50)
    private String specialty;

    @NotNull
    @Min(1)
    @Max(20)
    private int officeNumber;

public Doctor(DoctorDTO doctorDTO){
    this.id = doctorDTO.getId();
    this.firstName = doctorDTO.getFirstName();
    this.lastName = doctorDTO.getLastName();
    this.specialty = doctorDTO.getSpecialty();
    this.officeNumber = doctorDTO.getOfficeNumber();

    }
}
