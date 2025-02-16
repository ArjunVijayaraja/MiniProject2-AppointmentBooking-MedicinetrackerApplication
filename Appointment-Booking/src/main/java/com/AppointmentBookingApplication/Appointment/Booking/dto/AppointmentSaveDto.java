package com.AppointmentBookingApplication.Appointment.Booking.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentSaveDto {
    @NotNull(message = "Select the doctor")
    private long doctorId;
    @NotNull(message = "select the department")
    private long departmentId;
    @NotNull(message = "Select the Appointment Date")
    @FutureOrPresent(message = "Appointment date must be today or in the future")
    private LocalDate appointmentDate;
    private long patientId;
    @NotNull(message = "Select a slot")
    private long slotId;
}
