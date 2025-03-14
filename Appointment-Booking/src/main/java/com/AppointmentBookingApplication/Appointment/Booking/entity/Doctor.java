package com.AppointmentBookingApplication.Appointment.Booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    @Column(nullable = false)
    private String doctorName;
    @Column(nullable = false)
    private LocalDate dob;
    @Column(nullable = false)
    private char gender;
    @Column(nullable = false)
    private String doctorMail;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String doctorStatus;
    @Column(nullable = false)
    private LocalDateTime entryDate;
    @Column(nullable = false)
    private LocalDateTime updatedDate;
    @Column
    private String departmentName;

//    @ManyToMany
//    @JoinTable(
//            name="doctorDepartments",
//            joinColumns = @JoinColumn(name="fk_doc_id",referencedColumnName = "doctorId"),
//            inverseJoinColumns = @JoinColumn(name = "fk_dept_id", referencedColumnName = "departmentId")
//    )
//    private List<Department> departmentList;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name ="doctor_role",
            joinColumns =@JoinColumn(name = "d_id", referencedColumnName = "doctorId"),
            inverseJoinColumns =@JoinColumn(name = "r_id", referencedColumnName = "roleId")
    )
    private Set<Role> roles;
}
