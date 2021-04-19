package com.example.rest.demo.restcontroller;

import com.example.rest.demo.entity.DoctorDTO;
import com.example.rest.demo.service.DoctorService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/doctors")

public class RestDoctor {

    private final DoctorService doctorService;

    @GetMapping("/")
    public ResponseEntity<Object> doctors() {
        try {
            return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addNewDoctor(@RequestBody DoctorDTO doctorDTO) {
        try {
            doctorService.saveDoctor(doctorDTO);
            return new ResponseEntity<>("The new Doctor successfully saved", HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{doctorId}")
    public ResponseEntity<Object> updateDoctorInfo(@PathVariable Long doctorId, @RequestBody DoctorDTO doctorDTO) {
        try {
            doctorDTO.setId(doctorId);
            doctorService.updateDoctor(doctorDTO);
            return new ResponseEntity<>("Doctor info updated successfully!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<Object> removeDoctor(@PathVariable Long doctorId) {
        try {
            doctorService.deleteDoctor(doctorId);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Object> getDoctorById(@PathVariable Long doctorId) {
        try {
            return new ResponseEntity<>(doctorService.findDoctorById(doctorId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}


