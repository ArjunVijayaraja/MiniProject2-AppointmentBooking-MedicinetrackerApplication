package com.AppointmentBookingApplication.Appointment.Booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class emailExistsAlready extends RuntimeException{
    public emailExistsAlready(String patientMail){
        super(String.format("Email '%s'  exists already..",patientMail));
    }
}
