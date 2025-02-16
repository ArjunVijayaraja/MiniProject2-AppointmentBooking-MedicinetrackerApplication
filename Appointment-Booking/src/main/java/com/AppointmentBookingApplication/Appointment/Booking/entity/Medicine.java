package com.AppointmentBookingApplication.Appointment.Booking.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "medicines")
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long medicineId;
    @Column(nullable = false)
    private String medicineName;
    @Column(nullable = false)
    private String medicineDescription;
    @Column
    private LocalDateTime entryDate;
    @Column
    private LocalDateTime updatedDate;

}
