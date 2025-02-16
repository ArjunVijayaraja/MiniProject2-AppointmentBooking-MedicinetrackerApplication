package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.DoctorDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Doctor;
import com.AppointmentBookingApplication.Appointment.Booking.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {

    private DoctorService doctorService;

    @PostMapping("/createDoc")
    public ResponseEntity<DoctorDto> createDoc (@RequestBody DoctorDto doctorDto){
        DoctorDto createdDoctor = doctorService.createDoctor(doctorDto);
        return new ResponseEntity<DoctorDto>(createdDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/updateDocDept/{docId}")
    public ResponseEntity<DoctorDto> updateDocDept(@PathVariable("docId") long docId, @RequestBody DoctorDto doctorDto){
        DoctorDto updatedDoctor = doctorService.updateDoctor(docId,doctorDto);
        return new ResponseEntity<DoctorDto>(updatedDoctor,HttpStatus.OK);
    }

}
