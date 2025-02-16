package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.PatientDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@AllArgsConstructor
public class RegistrationController {

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("p_registration_dto") PatientDto patientDto, Model model){
        model.addAttribute("p_registration_dto",patientDto);
        return "registration";
    }
}
