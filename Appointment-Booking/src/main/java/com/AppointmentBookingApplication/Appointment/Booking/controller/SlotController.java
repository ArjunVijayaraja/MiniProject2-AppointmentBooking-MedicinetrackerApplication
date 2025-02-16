package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.SlotDto;
import com.AppointmentBookingApplication.Appointment.Booking.service.SlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/slot")
@AllArgsConstructor
public class SlotController {

    private SlotService slotService;

    @GetMapping("/getAllSlots")
    public ResponseEntity<Set<SlotDto>> getAllSlots(){
       Set<SlotDto> slotDtoSet = slotService.getAllSlots();
       return   new ResponseEntity<Set<SlotDto>>(slotDtoSet, HttpStatus.OK);
    }

}
