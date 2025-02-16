package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.PatientDto;
import com.AppointmentBookingApplication.Appointment.Booking.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/patientApi")
@AllArgsConstructor
public class PatientController {
    @Autowired
    private PatientService patientService;


//    @PostMapping("/createPatient")
//    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto){
//        PatientDto savedPatienDto = patientService.createPatient(patientDto);
//        return new ResponseEntity<PatientDto>(savedPatienDto, HttpStatus.CREATED);
//    }

    @PostMapping("/createPatient/save")
    public String createPatient(@Valid @ModelAttribute("p_registration_dto") PatientDto patientDto, BindingResult result, Model model,
                                RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("p_registration_dto", patientDto);
            return "registration";
        }
        PatientDto savedPatientDto = patientService.createPatient(patientDto);
        model.addAttribute("p_registration_dto", savedPatientDto);
        return  "redirect:/registration?success";





    }


}
