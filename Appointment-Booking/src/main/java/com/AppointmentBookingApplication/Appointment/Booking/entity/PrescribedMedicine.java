package com.AppointmentBookingApplication.Appointment.Booking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"prescription", "medicine"})
@Entity
@Table(name = "prescribedMedicines")
public class PrescribedMedicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescribedMedicineId;

    @Column(nullable = false)
    private String duration;
    @Column(nullable = false)
    private String instructions;
    @Column
    private LocalDateTime entryDate;
    @Column
    private LocalDateTime updatedDate;

    @ManyToOne(targetEntity = Medicine.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_medicineId")
    @JsonBackReference
    private Medicine medicine;

    @ManyToOne(targetEntity = Prescription.class)
    @JoinColumn(name = "fk_prescriptionId")
    @JsonBackReference
    private Prescription prescription;


}
