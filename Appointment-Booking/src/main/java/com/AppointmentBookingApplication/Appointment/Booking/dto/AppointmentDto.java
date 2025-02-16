package com.AppointmentBookingApplication.Appointment.Booking.dto;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Patient;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Prescription;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    private Long appointmentId;
    @Valid
    @NotNull(message = "Select the doctor")
    private DoctorDto doctor;
    private LocalDateTime entryDate;
    private LocalDateTime updatedDate;
    private String appointmentStatus;

    @NotNull(message = "Select the Appointment Date")
    @FutureOrPresent(message = "Appointment date must be today or in the future")
    private LocalDate appointmentDate;

    private PatientDto patient;
    @NotNull(message = "select the department")
    private DepartmentDto department;
    @NotNull(message = "Select a slot")
    private SlotDto slot;



}
