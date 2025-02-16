package com.AppointmentBookingApplication.Appointment.Booking.service;

import com.AppointmentBookingApplication.Appointment.Booking.dto.AppointmentDto;
import com.AppointmentBookingApplication.Appointment.Booking.dto.AppointmentSaveDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    AppointmentDto createAppointment(Long patientID, AppointmentSaveDto appointmentSaveDto);
    AppointmentDto cancelAppointment(Long appointId);
    List<AppointmentDto> getAllAppointments();
    List<AppointmentDto> getAppointmentsByPatientId(Long patientId);
    List<AppointmentDto> getAppointmentByDate(LocalDate appointDate);
    List<AppointmentDto> getAllAppointmentByPatientMail(String patientMail);
    AppointmentDto findByAppointmentId(long appointmentId);
    List<AppointmentDto> getAllConfirmedAppointmentByPatientMail(String patientMail);

}
