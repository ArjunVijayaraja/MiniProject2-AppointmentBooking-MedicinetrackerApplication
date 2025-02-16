package com.AppointmentBookingApplication.Appointment.Booking.service.serviceImpl;

import com.AppointmentBookingApplication.Appointment.Booking.dto.MedicineDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Medicine;
import com.AppointmentBookingApplication.Appointment.Booking.repository.MedicineRepository;
import com.AppointmentBookingApplication.Appointment.Booking.service.MedicineService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private ModelMapper modelMapper;
    private MedicineRepository medicineRepository;


    @Override
    public MedicineDto createMedicine(MedicineDto medicineDto) {
        Optional<Medicine> optionalMedicine = medicineRepository.findByMedicineName(medicineDto.getMedicineName());

        if(optionalMedicine.isEmpty()){
            Medicine medicine = modelMapper.map(medicineDto,Medicine.class);
            medicine.setEntryDate(LocalDateTime.now());
            medicine.setUpdatedDate(LocalDateTime.now());
            Medicine savedMedicine = medicineRepository.save(medicine);
            return modelMapper.map(savedMedicine,MedicineDto.class);
        }
        else{
            throw new RuntimeException("Medicine already exists with :"+medicineDto.getMedicineName());
        }
    }

    @Override
    public List<MedicineDto> getAllMedicine() {

        List<Medicine> medicineLst =  medicineRepository.findAll();
        List<MedicineDto> medicineDtoLList = medicineLst.stream().map(i-> modelMapper.map(i,MedicineDto.class)).collect(Collectors.toList());
        return medicineDtoLList;
    }
}
