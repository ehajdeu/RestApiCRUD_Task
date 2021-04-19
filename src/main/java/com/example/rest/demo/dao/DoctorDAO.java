package com.example.rest.demo.dao;

import com.example.rest.demo.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorDAO extends JpaRepository<Doctor, Long> {
    List<Doctor> findDoctorBySpecialty(String specialty);
}
