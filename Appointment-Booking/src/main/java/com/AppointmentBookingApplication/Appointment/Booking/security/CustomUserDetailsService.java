package com.AppointmentBookingApplication.Appointment.Booking.security;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Patient;
import com.AppointmentBookingApplication.Appointment.Booking.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String patientMail) throws UsernameNotFoundException {

            Patient patient =  patientRepository.findByPatientMail(patientMail)
                    .orElseThrow(()->new RuntimeException("Patient Not Found"));

            Set<GrantedAuthority> authorities = patient.getRoles().stream().map(r->new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toSet());

//        return new org.springframework.security.core.userdetails.User(
//                patientMail,patient.getPassword(),authorities

            return org.springframework.security.core.userdetails.User.builder()
                    .username(patient.getPatientMail())
                    .password(patient.getPassword())
                    .authorities(authorities)
                    .build();

    }
}
