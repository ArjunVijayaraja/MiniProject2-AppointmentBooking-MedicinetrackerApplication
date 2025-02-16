package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.*;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Prescription;
import com.AppointmentBookingApplication.Appointment.Booking.service.*;
import com.AppointmentBookingApplication.Appointment.Booking.utilsMethods.CommonFunctions;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

//@RestController
@Controller
//@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

    private PatientService patientService;
    private AppointmentService appointmentService;
    private SlotService slotService;
    private PrescriptionService prescriptionService;
    private LoginController loginController;
    private DoctorService doctorService;
    private CommonFunctions commonFunctions;
    private DepartmentService departmentService;

//    @PostMapping("/creatAppoint")
//    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody AppointmentDto appointmentDto){
//        AppointmentDto newAppointment = appointmentService.createAppointment(appointmentDto.getPatient().getPatientId(),appointmentDto);
//        return new ResponseEntity<AppointmentDto>(newAppointment, HttpStatus.CREATED);
//    }

    @PostMapping("/createAppointment")
    public String createAppointment(@Valid @ModelAttribute("appointmentFormObject") AppointmentSaveDto appointmentSaveDto,
                                    BindingResult result, Model model) throws Exception {

        System.out.println("UerMail from authentication: " + commonFunctions.getUserMailFromAuthentication());
        String userMail = commonFunctions.getUserMailFromAuthentication();
        PatientDto patientDto = patientService.findPatientByMail(userMail);
        List<AppointmentDto> appointmentDTOLst = appointmentService.getAllAppointmentByPatientMail(patientDto.getPatientMail());
        List<DoctorDto> doctorDtoList = doctorService.findAllDoctors();
        List<DepartmentDto> departDtoLst = departmentService.getALlDepartment();
        Set<SlotDto> availableSlots = slotService.getAllSlots();

        if (appointmentSaveDto.getDoctorId() == -1) {
            result.rejectValue("doctorId", "error.appointmentDto", "Please select a doctor");
        }
        if (appointmentSaveDto.getDepartmentId() == -1) {
            result.rejectValue("departmentId", "error.appointmentDto", "Please select a department");
        }

        if(appointmentSaveDto.getSlotId() <= 0){
            result.rejectValue("slotId","error.appointmentDto","Select a Slot");
        }

        if (result.hasErrors()) {
            model.addAttribute("appointmentFormObject", appointmentSaveDto);
            model.addAttribute("appointmentsDtoLst_model", appointmentDTOLst);
//            model.addAttribute("appointmentDtoLst", appointmentDTOLst);
            model.addAttribute("doctorDtoLst",doctorDtoList);
            model.addAttribute("departmentDtoLst_model",departDtoLst);
            model.addAttribute("slotDto_lst", availableSlots);
            return "appointment";
        }
        try {
            AppointmentDto createdAppointDto = appointmentService.createAppointment(patientDto.getPatientId(),appointmentSaveDto);
            return "redirect:/home?Success";

        } catch (Exception ex) {
            model.addAttribute("appointmentFormObject", appointmentSaveDto);
            model.addAttribute("appointmentDtoLst", appointmentDTOLst);
            model.addAttribute("slotDtoLst", availableSlots);
            model.addAttribute("error", "Something went wrong try again..");
            return "appointment";
        }
    }


    @RequestMapping("/home/cancelAppointment/{appointId}")
    public String cancelAppointment(@PathVariable("appointId") long appointId){

        PrescriptionDto prescriptionDto = prescriptionService.findPrescriptionByAppointmentId(appointId);
       AppointmentDto appointmentDto1 = appointmentService.cancelAppointment(appointId);
       return "redirect:/home?cancelled";

    }


    @GetMapping("/AllAppointments")
    public ResponseEntity<List<AppointmentDto>> getAppointments(){
        List<AppointmentDto> appointmentDtoList = appointmentService.getAllAppointments();
        return new ResponseEntity<List<AppointmentDto>>(appointmentDtoList,HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<AppointmentDto>> getPatientAppointments(@PathVariable("patientId") long patientId){
        List<AppointmentDto> patientAppointments = appointmentService.getAppointmentsByPatientId(patientId);
        return new ResponseEntity<List<AppointmentDto>>(patientAppointments,HttpStatus.OK);
    }

    @GetMapping("/getAppoint/{appointDate}")
    public ResponseEntity<List<AppointmentDto>> getAppointmentByAppointDate(@PathVariable("appointDate")LocalDate appointDate){
        List<AppointmentDto>  appointmentDtoList = appointmentService.getAppointmentByDate(appointDate);
        return new ResponseEntity<List<AppointmentDto>>(appointmentDtoList,HttpStatus.OK);
    }



}
