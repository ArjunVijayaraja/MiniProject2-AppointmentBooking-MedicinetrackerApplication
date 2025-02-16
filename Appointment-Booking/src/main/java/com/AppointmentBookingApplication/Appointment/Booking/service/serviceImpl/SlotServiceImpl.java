package com.AppointmentBookingApplication.Appointment.Booking.service.serviceImpl;

import com.AppointmentBookingApplication.Appointment.Booking.dto.SlotDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Slot;
import com.AppointmentBookingApplication.Appointment.Booking.repository.SlotRepository;
import com.AppointmentBookingApplication.Appointment.Booking.service.SlotService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SlotServiceImpl implements SlotService {

    private SlotRepository slotRepository;
    private ModelMapper modelMapper;

    @Override
    public Set<SlotDto> getAllSlots() {
        List<Slot> slots =  slotRepository.findAll();
        Set<SlotDto> slotDto = slots.stream().map(slot -> modelMapper.map(slot,SlotDto.class)).collect(Collectors.toSet());
        return slotDto;
    }
}
