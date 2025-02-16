package com.AppointmentBookingApplication.Appointment.Booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Column(nullable = false)
    private String departmentName;
    @Column(nullable = false)
    private String departmentStatus;
    @Column(nullable = false)
    private LocalDateTime entryDate;
    @Column(nullable = false)
    private LocalDateTime updatedDate;

//    @ManyToMany(mappedBy = "departmentList")
//    private List<Doctor> doctorList;
}
