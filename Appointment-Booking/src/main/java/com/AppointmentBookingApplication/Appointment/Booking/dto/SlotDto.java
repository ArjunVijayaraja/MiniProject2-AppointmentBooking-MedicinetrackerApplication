package com.AppointmentBookingApplication.Appointment.Booking.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotDto {

    private long slotId;
    private String SlotName;
    private String slotTiming;
}
