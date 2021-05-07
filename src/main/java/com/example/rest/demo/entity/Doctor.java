package com.example.rest.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "office_number")
    private Long officeNumber;

public Doctor(DoctorDTO doctorDTO){
    this.id = doctorDTO.getId();
    this.firstName = doctorDTO.getFirstName();
    this.lastName = doctorDTO.getLastName();
    this.specialty = doctorDTO.getSpecialty();
    this.officeNumber = doctorDTO.getOfficeNumber();

    }
}
