package com.AppointmentBookingApplication.Appointment.Booking.service;

import com.AppointmentBookingApplication.Appointment.Booking.dto.PrescribedMedicineDto;
import com.AppointmentBookingApplication.Appointment.Booking.dto.PrescriptionDto;

import java.util.List;

public interface PrescriptionService {
    //PrescriptionDto createPrescription(Long appointId, List<PrescribedMedicineDto> prescribedMedicineDtoList);
    PrescriptionDto createPrescription(PrescriptionDto prescriptionDto);
    PrescriptionDto updatePrescription(Long prescriptionId, List<PrescribedMedicineDto> prescribedMedicineDtoList);
    PrescriptionDto getPrescription (Long prescriptionId);
//    String deletePrescribedMedicine(Long appointId,String medName);
    List<PrescriptionDto> getAllPrescriptionByPatientId(long patientId);
    PrescriptionDto getPrescriptionByAppointmentId(long appointmentId);
    String removePrescribedMedicineFromPrescription(long prescriptionId, String pmName);
    PrescriptionDto findPrescriptionByAppointmentId(long appointId);


}
