package com.AppointmentBookingApplication.Appointment.Booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    @Column(nullable = false)
    private LocalDateTime entryDate;
    @Column
    private LocalDateTime updatedDate;
    @Column
    private String appointmentStatus;
    private LocalDate appointmentDate;


    @ManyToOne(targetEntity = Patient.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_patientId", unique = false)
    private Patient patient;


    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "fk_slotId", unique = false)
    private Slot slot;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Department.class)
    @JoinColumn(name = "fk_deptId", unique = false)
    private Department department;


    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Doctor.class)
    @JoinColumn(name = "fk_doctorId", unique = false)
    private Doctor doctor;



}
