package com.AppointmentBookingApplication.Appointment.Booking.service;

import com.AppointmentBookingApplication.Appointment.Booking.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    public MedicineDto createMedicine(MedicineDto medicineDto);
    public List<MedicineDto> getAllMedicine();
}
