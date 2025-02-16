package com.AppointmentBookingApplication.Appointment.Booking.utilsMethods;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CommonFunctions {

    public String getUserMailFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userMail = authentication.getName();
        System.out.println("UerMail from authentication: " + userMail);
        return userMail;
    }
}
