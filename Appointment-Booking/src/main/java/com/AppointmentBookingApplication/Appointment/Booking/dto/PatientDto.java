package com.AppointmentBookingApplication.Appointment.Booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {


    private Long patientId;
    @NotBlank(message = "Patient name cannot be empty")
    private String patientName;

    @NotBlank(message = "Password cannot be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "DOB cannot be empty")
    private LocalDate dob;
    @NotBlank(message = "city cannot be empty")
    private String city;
    @NotBlank(message = "Country cannot be empty")
    private String country;
    @NotBlank(message = "pincode cannot be empty")
    private String pincode;
    @NotBlank(message = "Patient mail cannot be empty")
    private String patientMail;
    @NotBlank(message = "contact number cannot be empty")
    private String contactNumber;
    private String patientStatus;
}
