package com.AppointmentBookingApplication.Appointment.Booking.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    @Column(nullable = false)
    private String patientName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private LocalDate dob;
    @Column(nullable = false)
    private String patientMail;
    @Column(nullable = false)
    private String contactNumber;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String pincode;
    @Column(nullable = false)
    private String patientStatus;
    @Column(nullable = false)
    private LocalDateTime entryDate;
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name ="patient_role",
            joinColumns =@JoinColumn(name = "p_id", referencedColumnName = "patientId"),
            inverseJoinColumns =@JoinColumn(name = "r_id", referencedColumnName = "roleId")
    )
    private Set<Role> roles;
}
