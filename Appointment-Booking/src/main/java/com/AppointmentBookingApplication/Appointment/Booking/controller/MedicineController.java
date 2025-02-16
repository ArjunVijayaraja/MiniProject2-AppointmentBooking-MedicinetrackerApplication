package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.MedicineDto;
import com.AppointmentBookingApplication.Appointment.Booking.service.MedicineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicine")
@AllArgsConstructor
public class MedicineController {

    private MedicineService medicineService;

    @PostMapping("/createMed")
    public ResponseEntity<MedicineDto> createMedicine(@RequestBody MedicineDto medicineDto){
        MedicineDto createdMedicine = medicineService.createMedicine(medicineDto);
        return new ResponseEntity<MedicineDto>(createdMedicine, HttpStatus.CREATED);
    }
}

