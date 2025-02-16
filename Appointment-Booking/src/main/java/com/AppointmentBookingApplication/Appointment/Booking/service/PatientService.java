package com.AppointmentBookingApplication.Appointment.Booking.service;

import com.AppointmentBookingApplication.Appointment.Booking.dto.PatientDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Patient;

public interface  PatientService {

    public PatientDto createPatient(PatientDto patientDto);
    public PatientDto findPatientByMail(String patientMail);

}
