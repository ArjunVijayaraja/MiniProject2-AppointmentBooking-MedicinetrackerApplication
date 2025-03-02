package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.*;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Prescription;
import com.AppointmentBookingApplication.Appointment.Booking.service.*;
import com.AppointmentBookingApplication.Appointment.Booking.utilsMethods.CommonFunctions;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import java.util.List;

@Controller
//@RequestMapping("/prescription")
@AllArgsConstructor
public class PrescriptionController {
    private PrescriptionService prescriptionService;
    private AppointmentService appointmentService;
    private MedicineService medicineService;
    private PatientService patientService;
    private CommonFunctions cm;
    private DoctorService doctorService;

//    @PostMapping("/createPrescription")
//    public ResponseEntity<PrescriptionDto> createPrescription(@RequestBody List<PrescribedMedicineDto> prescribedMedicineDtoList){
//        PrescriptionDto savedPrescriptionDto = prescriptionService.createPrescription(prescribedMedicineDtoList);
//        return new ResponseEntity<PrescriptionDto>(savedPrescriptionDto, HttpStatus.CREATED);
//    }



//    @PostMapping("/appointId/{appID}/createPrescription")
//    public ResponseEntity<PrescriptionDto> createPrescription(@PathVariable("appID") long appointId, @RequestBody List<PrescribedMedicineDto> prescribedMedicineDtoList){
//        PrescriptionDto savedPrescriptionDto = prescriptionService.createPrescription(appointId,prescribedMedicineDtoList);
//        return new ResponseEntity<PrescriptionDto>(savedPrescriptionDto, HttpStatus.CREATED);
//    }


    @GetMapping("/prescription/{appID}")
    public String displayPrescriptionPage(@PathVariable("appID") long appointID, Model model){

        PrescriptionDto prescriptionDto1 = prescriptionService.findPrescriptionByAppointmentId(appointID);
        if(prescriptionDto1 != null && prescriptionDto1.getAppointment().getAppointmentId() == appointID){
            model.addAttribute("prescriptionDtoModel",prescriptionDto1);
            model.addAttribute("viewType","existingPrescription");
            return "prescription";
        }
        else {


            PrescriptionDto prescriptionDto = new PrescriptionDto();
            AppointmentDto appointmentDto = appointmentService.findByAppointmentId(appointID);
            prescriptionDto.setAppointment(appointmentDto);
            model.addAttribute("prescriptionDtoModel", prescriptionDto);
            model.addAttribute("appointmentId", appointID);
            List<MedicineDto> medicineDtoList = medicineService.getAllMedicine();
            model.addAttribute("medicineLst", medicineDtoList);
//        List<AppointmentDto> appointmentDtoList = appointmentService.getAllAppointmentByPatientMail(cm.getUserMailFromAuthentication());
            List<AppointmentDto> appointmentDtoList = doctorService.getAllAppointmentsforDoctor(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("appointmentList", appointmentDtoList);


            return "prescription";
        }
    }

    @GetMapping("/prescV")
    public String viewP(@ModelAttribute("prescriptionDtoModel") PrescriptionDto prescriptionDto, Model model){
        model.addAttribute("prescriptionDtoModel", prescriptionDto);
        return "prescription";

    }

    @PostMapping("/createPrescription/{appointmentId}")
    public String createPrescription(@Valid @ModelAttribute("prescriptionDtoModel") PrescriptionDto prescriptionDto,
                                                              @PathVariable("appointmentId") Long appointmentId,
                                     BindingResult result, Model model, RedirectAttributes redirectAttributes){
//        PatientDto patientDto= patientService.findPatientByMail(cm.getUserMailFromAuthentication());
       // prescriptionDto.setPatient(patientDto);


        if(result.hasErrors()){
            model.addAttribute("prescriptionDtoModel",prescriptionDto);
            List<MedicineDto> medicineDtoList = medicineService.getAllMedicine();
            model.addAttribute("medicineLst",medicineDtoList);
            return "prescription";
        }
        try{
            PrescriptionDto savedPrescriptionDto = prescriptionService.createPrescription(prescriptionDto);
            model.addAttribute("prescriptionDtoModel",savedPrescriptionDto);
            model.addAttribute("appointmentId",appointmentId);
            redirectAttributes.addFlashAttribute("prescriptionDtoModel",savedPrescriptionDto);

            return "redirect:/prescV?Success";
        }catch (Exception ex){
            return "prescription";
        }

    }


    @GetMapping("/viewPrescriptions")
    public  String viewPrescriptions(Model model){
        PatientDto patientDto =  patientService.findPatientByMail(cm.getUserMailFromAuthentication());
        List<PrescriptionDto> prescriptionDto = prescriptionService.getAllPrescriptionByPatientId(patientDto.getPatientId());
        model.addAttribute("prescriptionDtoLst",prescriptionDto);
        return "viewPrescription";
    }


    @GetMapping("/viewPrescription/{prescribID}")
    public String getPrescriptionById(@PathVariable("prescribID") long prescribId, Model model, RedirectAttributes redirectAttributes){

        PrescriptionDto prescriptionDto = prescriptionService.getPrescription(prescribId);
        //prescriptionDto.setAppointment(null);
        model.addAttribute("prescriptionDtoModel",prescriptionDto);
       // model.addAttribute("prescriptionDtoModel", prescriptionDto.getPrescribedMedicines());
//        List<MedicineDto> medicineDtoList = medicineService.getAllMedicine();
//        model.addAttribute("medicineLst",medicineDtoList);
//        List<AppointmentDto> appointmentDtoList =appointmentService.getAllConfirmedAppointmentByPatientMail(cm.getUserMailFromAuthentication());
//        model.addAttribute("appointmentList",appointmentDtoList);
        model.addAttribute("viewType","existingPrescription");
//        redirectAttributes.addFlashAttribute("viewType","existingPrescription");
//        model.addAttribute("viewType","existingPrescription");
//        redirectAttributes.addFlashAttribute("appId",prescribId);
        model.addAttribute("appId",prescribId);
        //PrescriptionDto prescriptionDto = prescriptionService.getPrescription(prescribId);

        return "prescriptionViewPatient";
    }

//    @GetMapping("/prescription/{prescribID}")
//    public ResponseEntity<PrescriptionDto> getPrescriptionById(@PathVariable("prescribID") long prescribId){
//        PrescriptionDto prescriptionDto = prescriptionService.getPrescription(prescribId);
//        return new ResponseEntity<PrescriptionDto>(prescriptionDto,HttpStatus.OK);
//    }



//    update method OLD
//    @PostMapping("/updatePrescription/{prescriptionId}")
//    public ResponseEntity<PrescriptionDto> updatePrescription(@PathVariable("prescriptionId") long prescriptionId, @RequestBody List<PrescribedMedicineDto> prescribedMedicineDtoList){
//        PrescriptionDto updatedPrescriptionDto = prescriptionService.updatePrescription(prescriptionId,prescribedMedicineDtoList);
//        return new ResponseEntity<PrescriptionDto>(updatedPrescriptionDto,HttpStatus.OK);
//    }


    @PostMapping("/updatePrescription/{prescriptionId}")
    public String updatePrescription(@PathVariable("prescriptionId") long prescriptionId,@ModelAttribute("prescriptionDtoModel") PrescriptionDto prescriptionDto,
                                     BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        PrescriptionDto prescriptionDto1 = prescriptionService.getPrescription(prescriptionId);
        if(result.hasErrors()){
            model.addAttribute("prescriptionDtoModel",prescriptionDto);
            List<MedicineDto> medicineDtoList = medicineService.getAllMedicine();
            model.addAttribute("medicineLst",medicineDtoList);
            return "prescription";
        }
        try{
            PrescriptionDto updatedPrescriptionDto = prescriptionService.updatePrescription(prescriptionId,prescriptionDto.getPrescribedMedicines());
            model.addAttribute("prescriptionDtoModel",updatedPrescriptionDto);
            redirectAttributes.addFlashAttribute("prescriptionDtoModel",updatedPrescriptionDto);
//            return "redirect:/prescription?updated";
            return "redirect:/prescription/"+updatedPrescriptionDto.getAppointment().getAppointmentId()+"?updated";
        } catch (Exception e) {
            //throw new RuntimeException(e);
            return "prescription";
        }
    }


    @RequestMapping("/removePrescribedMedicine/{prescriptionId}/{pmName}")
    public String removePrescribedMedicine(@PathVariable("prescriptionId") long prescriptionId, @PathVariable("pmName") String pmName){

        prescriptionService.removePrescribedMedicineFromPrescription(prescriptionId, pmName);
        return "redirect:/prescription/"+ prescriptionId;
    }





//    @DeleteMapping("prescription/{prescId}/deletePm/{medName}")
//    public ResponseEntity<String> removeMed(@PathVariable("prescId") Long prescId, @PathVariable("medName") String medName){
//        String msg = prescriptionService.deletePrescribedMedicine(prescId,medName);
//        return new ResponseEntity<String>(msg,HttpStatus.OK);
//    }












}
