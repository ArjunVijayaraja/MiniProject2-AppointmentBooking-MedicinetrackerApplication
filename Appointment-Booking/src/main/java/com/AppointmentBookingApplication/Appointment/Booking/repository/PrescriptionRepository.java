package com.AppointmentBookingApplication.Appointment.Booking.repository;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

//    List<Prescription> findByPatient_PatientId(long patientId);
    List<Prescription> findByAppointment_Patient_PatientId(long patientId);
    Optional<Prescription> findByAppointment_AppointmentId(long appointId);


}
