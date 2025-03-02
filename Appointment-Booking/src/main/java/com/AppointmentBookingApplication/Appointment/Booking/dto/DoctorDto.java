package com.AppointmentBookingApplication.Appointment.Booking.dto;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Department;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Long doctorId;
    private String doctorName;
    private LocalDate dob;
    private char gender;
    private String doctorMail;
    private String password;
    private String doctorStatus;
//    private List<DepartmentDto> departmentList;
    private String departmentName;
}
