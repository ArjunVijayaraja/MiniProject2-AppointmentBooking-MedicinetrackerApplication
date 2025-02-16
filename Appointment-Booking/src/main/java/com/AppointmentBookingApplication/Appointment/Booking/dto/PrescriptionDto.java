package com.AppointmentBookingApplication.Appointment.Booking.dto;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Appointment;
import com.AppointmentBookingApplication.Appointment.Booking.entity.PrescribedMedicine;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "prescribedMedicines")  // Prevent recursion

//@JsonIgnoreProperties(ignoreUnknown = true)  // Ignore unknown properties in JSON
public class PrescriptionDto {

    private Long prescriptionId;
    private int totalNoOfMedicines;
   // @JsonManagedReference
    @Valid
    @NotNull(message = "Prescribed Medicine cannot be null")
    @JsonIgnoreProperties("prescription") // Prevent recursion
    private List<PrescribedMedicineDto> prescribedMedicines;


    private AppointmentDto appointment;

}
