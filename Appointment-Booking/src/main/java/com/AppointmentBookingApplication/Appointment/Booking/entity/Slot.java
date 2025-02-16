package com.AppointmentBookingApplication.Appointment.Booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "slots")
@AllArgsConstructor
@NoArgsConstructor
public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long slotId;
    @Column(nullable = false)
    private String SlotName;
    @Column(nullable = false)
    private String slotTiming;
    @Column
    private LocalDateTime entryDate;
    @Column
    private LocalDateTime updatedDate;
}
