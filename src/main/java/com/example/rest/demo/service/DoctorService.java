package com.example.rest.demo.service;

import com.example.rest.demo.repository.DoctorRepository;
import com.example.rest.demo.entity.Doctor;
import com.example.rest.demo.entity.DoctorDTO;
import com.example.rest.demo.exceptionshandler.CustomException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ValidateDoctorService validateDoctorService;


    public List<DoctorDTO> getAllDoctors() {
        List<DoctorDTO> doctorsDTO = new ArrayList<>();
        List<Doctor> doctors = doctorRepository.findAll();
        for (Doctor doctor : doctors) {
            doctorsDTO.add(new DoctorDTO(doctor));
        }
        return doctorsDTO;
    }

    public void saveDoctor(DoctorDTO doctorDTO) throws CustomException {
        validateDoctorService.validateDoctor(doctorDTO);
        try {
            doctorRepository.save(new Doctor(doctorDTO));
        } catch (Exception ex) {
            throw new CustomException("The new doctor could not be saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void updateDoctor(DoctorDTO doctorDTO) throws CustomException {
        validateDoctorService.validateDoctor(doctorDTO);
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorDTO.getId());
        if (!optionalDoctor.isPresent()) {
            throw new CustomException("The doctor with id could not be found",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            Doctor doctorToBeUpdated = optionalDoctor.get();
            doctorToBeUpdated.setFirstName(doctorDTO.getFirstName());
            doctorToBeUpdated.setLastName(doctorDTO.getLastName());
            doctorToBeUpdated.setSpecialty(doctorDTO.getSpecialty());
            doctorToBeUpdated.setOfficeNumber(doctorDTO.getOfficeNumber());
            try {
                doctorRepository.save(doctorToBeUpdated);
            } catch (Exception ex) {
                throw new CustomException("The doctor could not be updated", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public void deleteDoctor(Long doctorId) throws CustomException {
        try {
            doctorRepository.deleteById(doctorId);
        } catch (Exception ex) {
            throw new CustomException("The doctor could not be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public DoctorDTO findDoctorById(Long doctorId) throws CustomException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            return new DoctorDTO(optionalDoctor.get());
        }
        throw new CustomException("The doctor with this ID could not be found!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
