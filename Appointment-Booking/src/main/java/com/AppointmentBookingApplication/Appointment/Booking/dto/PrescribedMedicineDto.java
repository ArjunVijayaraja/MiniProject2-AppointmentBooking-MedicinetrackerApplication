package com.AppointmentBookingApplication.Appointment.Booking.dto;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Medicine;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Prescription;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@ToString(exclude = "prescription")  // Prevent recursion

@NoArgsConstructor
public class PrescribedMedicineDto {

    private Long prescribedMedicineId;

    private String duration;
    private String instructions;
    //private LocalDateTime entryDate;
   // private LocalDateTime updatedDate;
    //@JsonBackReference
    private MedicineDto medicine;
   // @JsonBackReference
    //@JsonIgnore
   @JsonIgnoreProperties("prescribedMedicines") // Prevent recursion
   private PrescriptionDto prescription;
   // private String medicineName;
    //private Long prescriptionId;


}
