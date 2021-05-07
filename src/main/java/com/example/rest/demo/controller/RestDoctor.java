package com.example.rest.demo.controller;

import com.example.rest.demo.entity.DoctorDTO;
import com.example.rest.demo.exceptionshandler.ExceptionsHandler;
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
    private final ExceptionsHandler exceptionsHandler;

    @GetMapping
    public ResponseEntity<Object> doctors() {
        return new ResponseEntity<>(doctorService.getAllDoctors(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addNewDoctor(@RequestBody DoctorDTO doctorDTO) throws Exception {
        doctorService.saveDoctor(doctorDTO);
        return new ResponseEntity<>("The new Doctor successfully saved", HttpStatus.CREATED);
    }

    @PutMapping("/{doctorId}")
    public ResponseEntity<Object> updateDoctorInfo(@PathVariable Long doctorId, @RequestBody DoctorDTO doctorDTO) throws Exception {
        doctorDTO.setId(doctorId);
        doctorService.updateDoctor(doctorDTO);
        return new ResponseEntity<>("Doctor info updated successfully!", HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Object> removeDoctor(@PathVariable Long doctorId) throws Exception {
        doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Object> getDoctorById(@PathVariable Long doctorId) throws Exception {
        return new ResponseEntity<>(doctorService.findDoctorById(doctorId), HttpStatus.OK);
    }
}


