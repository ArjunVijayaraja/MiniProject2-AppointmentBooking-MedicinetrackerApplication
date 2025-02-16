package com.AppointmentBookingApplication.Appointment.Booking.repository;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Optional<Department> findByDepartmentName(String deptName);
}
