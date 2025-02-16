package com.AppointmentBookingApplication.Appointment.Booking.dto;

import com.AppointmentBookingApplication.Appointment.Booking.entity.PrescribedMedicine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {
    private Long medicineId;
    private String medicineName;
    private String medicineDescription;
   // private LocalDateTime entryDate;
    //private LocalDateTime updatedDate;

}
