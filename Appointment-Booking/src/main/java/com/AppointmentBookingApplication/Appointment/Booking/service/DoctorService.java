package com.AppointmentBookingApplication.Appointment.Booking.service;

import com.AppointmentBookingApplication.Appointment.Booking.dto.DoctorDto;
import java.util.List;


public interface DoctorService {

    DoctorDto createDoctor(DoctorDto doctorDto);
    DoctorDto findDoctorById(long id);
    List<DoctorDto> findAllDoctors();
    DoctorDto updateDoctor(long docId, DoctorDto doctorDto);
}
