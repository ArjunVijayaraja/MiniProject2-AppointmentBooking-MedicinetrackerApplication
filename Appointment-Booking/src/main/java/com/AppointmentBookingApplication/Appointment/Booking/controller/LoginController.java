package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.*;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Appointment;
import com.AppointmentBookingApplication.Appointment.Booking.repository.AppointmentRepository;
import com.AppointmentBookingApplication.Appointment.Booking.service.*;
import com.AppointmentBookingApplication.Appointment.Booking.utilsMethods.CommonFunctions;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class LoginController {

    private AppointmentService appointmentService;
    private DepartmentService departmentService;
    private SlotService slotService;
    private PatientService patientService;
    private DoctorService doctorService;
    private MedicineService medicineService;
    private CommonFunctions cm;

//    public String m_getUserNameMail() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userMail = authentication.getName();
//        System.out.println("UerMail from authentication: " + userMail);
//        return userMail;
//    }


    @GetMapping("/login")
    public String login() {
        System.out.println("SUCCESS LOGIN");
        return "appointment";
    }

    @GetMapping("/home")
    public String displayHome(Model model) throws Exception {
       // AppointmentDto appointmentDto = new AppointmentDto();
        AppointmentSaveDto appointmentSaveDto = new AppointmentSaveDto();
        PatientDto patientDto = patientService.findPatientByMail(cm.getUserMailFromAuthentication());
        appointmentSaveDto.setPatientId(patientDto.getPatientId());
        model.addAttribute("appointmentFormObject", appointmentSaveDto);
        List<DoctorDto> doctorDtoList = doctorService.findAllDoctors();
        model.addAttribute("doctorDtoLst",doctorDtoList);
        Set<SlotDto> availableSlotDtoLst = slotService.getAllSlots();
        model.addAttribute("slotsDtoLst", availableSlotDtoLst);
        //PatientDto patientDto = patientService.findPatientByMail(m_getUserNameMail());
        List<AppointmentDto> appointmentDTOLst = appointmentService.getAllAppointmentByPatientMail(patientDto.getPatientMail());
        model.addAttribute("appointmentsDtoLst_model", appointmentDTOLst);
        List<DepartmentDto> departmentDtoList = departmentService.getALlDepartment();
        model.addAttribute("departmentDtoLst_model",departmentDtoList);
        Set<SlotDto> slotDtoList = slotService.getAllSlots();
        model.addAttribute("slotDto_lst",slotDtoList);
       // List<AppointmentDto> appointmentDtoList = appointmentService.getAllAppointments();
        // model.addAttribute("departmentDtoLst_model",appointmentDtoList);

        return "appointment";
    }



}
