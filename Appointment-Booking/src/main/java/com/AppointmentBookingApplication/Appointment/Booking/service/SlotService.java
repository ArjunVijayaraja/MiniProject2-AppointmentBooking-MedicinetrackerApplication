package com.AppointmentBookingApplication.Appointment.Booking.service;

import com.AppointmentBookingApplication.Appointment.Booking.dto.SlotDto;

import java.util.Set;

public interface SlotService {

    Set<SlotDto> getAllSlots();
}
