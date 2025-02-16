package com.AppointmentBookingApplication.Appointment.Booking.repository;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByDoctorMail(String docMail);
}
