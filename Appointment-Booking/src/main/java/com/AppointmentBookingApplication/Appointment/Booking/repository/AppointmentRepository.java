package com.AppointmentBookingApplication.Appointment.Booking.repository;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findByPatient_PatientIdAndAppointmentDate(Long patientId, LocalDate appointDate);

    List<Appointment> findByPatient_PatientId(Long patientId);
    //Optional<Appointment> findByAppointmentDate(LocalDate appDate);

    //CustomQuery
    @Query("SELECT a from Appointment a where a.appointmentDate= :appointmentDate")
    List<Appointment> findByAppointmentDate(@Param("appointmentDate") LocalDate appointDate);

    List<Appointment> findByPatient_PatientMail(String patientMail);
    List<Appointment> findByPatient_PatientIdAndAppointmentStatus(long patientId, String appointmentStatus);
    List<Appointment> findByDoctor_DoctorMail(String docMail);

}
