package com.AppointmentBookingApplication.Appointment.Booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AppointmentAlreadyCancelledException extends RuntimeException {
    public AppointmentAlreadyCancelledException(long appointId){
        super(String.format("Appointment: '%s' cancelled Already",appointId));
    }
}
