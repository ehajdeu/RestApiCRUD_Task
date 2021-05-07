package com.example.rest.demo.service;

import com.example.rest.demo.entity.DoctorDTO;
import com.example.rest.demo.exceptionshandler.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class ValidateDoctorService {


    public Boolean validateDoctorName(String name) {
        return !name.isEmpty() && !name.contains(" ") &&
                name.length() >= 1
                && !name.matches(".*\\d.*");
    }

    public Boolean validateSpecialty(String specialty) {
        return !specialty.isEmpty() &&
                specialty.length() >= 3
                && !specialty.matches(".*\\d.*");
    }

    public Boolean validateOfficeNumber(Long number) throws CustomException {
        if (number >= 1 && number < 20) {
            return true;
        }
        throw new CustomException("You are trying to set an invalid office number", BAD_REQUEST);
    }

    public boolean validateDoctor(DoctorDTO doctorDTO) throws CustomException {
        try {
            if (validateDoctorName(doctorDTO.getFirstName())) {
                if (validateDoctorName(doctorDTO.getLastName())) {
                    if (validateSpecialty(doctorDTO.getSpecialty())) {
                        validateOfficeNumber(doctorDTO.getOfficeNumber());
                        return true;
                    } else {
                        throw new CustomException("You are trying to set an invalid Specialty", BAD_REQUEST);
                    }
                }
                throw new CustomException("You are trying to set an invalid Last Name", BAD_REQUEST);
            }
            throw new CustomException("You are trying to set an invalid First Name", BAD_REQUEST);
        } catch (NullPointerException npe) {
            throw new CustomException("Null values are not allowed", BAD_REQUEST);
        }
    }
}

