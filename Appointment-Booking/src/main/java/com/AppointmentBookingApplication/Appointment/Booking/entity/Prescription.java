package com.AppointmentBookingApplication.Appointment.Booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "prescriptions")
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;
    @Column(nullable = false)
    private int totalNoOfMedicines;
    @Column(nullable = false)
    private LocalDateTime entryDate;
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "prescription",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonManagedReference
    private List<PrescribedMedicine> prescribedMedicines;


    @OneToOne(targetEntity = Appointment.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "fk_appointment_id")
    private Appointment appointment;


}
