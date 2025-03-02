package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.AppointmentDto;
import com.AppointmentBookingApplication.Appointment.Booking.dto.DoctorDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Doctor;
import com.AppointmentBookingApplication.Appointment.Booking.service.DoctorService;
import com.AppointmentBookingApplication.Appointment.Booking.service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {

    private DoctorService doctorService;
    private PrescriptionService prescriptionService;


    @GetMapping("/docRegistration")
    public String createDoctor(@ModelAttribute("doctorDto") DoctorDto doctorDto, Model model){
        model.addAttribute("doctorDto", doctorDto);
        return "doctorRegistration";
    }


    @PostMapping("/createDoctor")
    public String createDoctor(@Valid @ModelAttribute("doctorDto") DoctorDto doctorDto, BindingResult result, Model model,
                               RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            model.addAttribute("doctorDto",doctorDto);
            return "doctorRegistration";
        }

        DoctorDto savedDoc = doctorService.createDoctor(doctorDto);
        model.addAttribute("doctorDto",savedDoc);
        redirectAttributes.addFlashAttribute("doctorDto",savedDoc);
        return "redirect:/doctor/docRegistration?Success";

    }


    @GetMapping("/viewAppointments")
    public String getAllAppointments(Model model){

        List<AppointmentDto> appointments =  doctorService.
                getAllAppointmentsforDoctor(SecurityContextHolder.getContext().getAuthentication().getName());


        model.addAttribute("patientAppointmentList",appointments);

        return "doctorAppointmentList";


    }


    @PostMapping("/createDoc")
    public ResponseEntity<DoctorDto> createDoc (@RequestBody DoctorDto doctorDto){
        DoctorDto createdDoctor = doctorService.createDoctor(doctorDto);
        return new ResponseEntity<DoctorDto>(createdDoctor, HttpStatus.CREATED);
    }

//    @PutMapping("/updateDocDept/{docId}")
//    public ResponseEntity<DoctorDto> updateDocDept(@PathVariable("docId") long docId, @RequestBody DoctorDto doctorDto){
//        DoctorDto updatedDoctor = doctorService.updateDoctor(docId,doctorDto);
//        return new ResponseEntity<DoctorDto>(updatedDoctor,HttpStatus.OK);
//    }

}
