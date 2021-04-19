package com.example.rest.demo.service;

import com.example.rest.demo.dao.DoctorDAO;
import com.example.rest.demo.entity.Doctor;
import com.example.rest.demo.entity.DoctorDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorDAO doctorDAO;

    public List<DoctorDTO> getAllDoctors() {
        List<DoctorDTO> doctorsDTO = new ArrayList<>();
        List<Doctor> doctors = doctorDAO.findAll();
        for (Doctor doctor : doctors) {
            doctorsDTO.add(new DoctorDTO(doctor));
        }
        return doctorsDTO;
    }

    public void saveDoctor(DoctorDTO doctorDTO) throws Exception {
        try {
            doctorDAO.save(new Doctor(doctorDTO));
        } catch (Exception ex) {
            throw new Exception("The new doctor could not be saved");
        }
    }

    public void updateDoctor(DoctorDTO doctorDTO) throws Exception {
        Optional<Doctor> optionalDoctor = doctorDAO.findById(doctorDTO.getId());
        Doctor doctorToBeUpdated = optionalDoctor.get();
        doctorToBeUpdated.setFirstName(doctorDTO.getFirstName());
        doctorToBeUpdated.setLastName(doctorDTO.getLastName());
        doctorToBeUpdated.setSpecialty(doctorDTO.getSpecialty());
        doctorToBeUpdated.setOfficeNumber(doctorDTO.getOfficeNumber());
        try {
            doctorDAO.save(doctorToBeUpdated);
        } catch (Exception ex) {
            throw new Exception("The doctor could not be updated");

        }
    }

    public void deleteDoctor(Long doctorId) throws Exception {
        try {
            doctorDAO.deleteById(doctorId);
        } catch (Exception ex) {
            throw new Exception("The doctor could not be deleted");
        }
    }

    public DoctorDTO findDoctorById(Long doctorId) throws Exception {
        Optional<Doctor> optionalDoctor = doctorDAO.findById(doctorId);
        if (optionalDoctor.isPresent()) {
            return new DoctorDTO(optionalDoctor.get());
        }
        throw new Exception("The doctor with this ID could not be found!");
    }
}
