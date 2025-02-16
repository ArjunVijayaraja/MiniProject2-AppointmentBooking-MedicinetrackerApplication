package com.AppointmentBookingApplication.Appointment.Booking.repository;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepository extends JpaRepository<Slot,Long> {
}
