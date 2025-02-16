package com.AppointmentBookingApplication.Appointment.Booking.service.serviceImpl;

import com.AppointmentBookingApplication.Appointment.Booking.dto.PatientDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Patient;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Role;
import com.AppointmentBookingApplication.Appointment.Booking.repository.PatientRepository;
import com.AppointmentBookingApplication.Appointment.Booking.repository.RoleRepository;
import com.AppointmentBookingApplication.Appointment.Booking.service.PatientService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;



    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto,Patient.class);
        Optional<Patient> optionalPatient = patientRepository.findByPatientMail(patientDto.getPatientMail());
        Optional<Role> role = roleRepository.findById(1l);
         Set<Role> roleSet = role.stream().collect(Collectors.toSet());

        if(optionalPatient.isEmpty()){
            patient.setRoles(roleSet);
            patient.setPassword(passwordEncoder.encode(patientDto.getPassword()));
            patient.setEntryDate(LocalDateTime.now());
            patient.setUpdatedDate(LocalDateTime.now());
            patient.setPatientStatus("ACTIVE");
            Patient savedPatient =  patientRepository.save(patient);
            return modelMapper.map(savedPatient, PatientDto.class);
        }
        else {
            throw new RuntimeException("Patient already exists...!");
        }

    }

    @Override
    public PatientDto findPatientByMail(String patientMail) {

        Optional<Patient> patient = patientRepository.findByPatientMail(patientMail);
        return modelMapper.map(patient.get(),PatientDto.class) ;
    }


}
