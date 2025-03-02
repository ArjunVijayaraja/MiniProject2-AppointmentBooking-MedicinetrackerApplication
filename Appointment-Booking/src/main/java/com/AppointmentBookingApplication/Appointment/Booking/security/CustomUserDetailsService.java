package com.AppointmentBookingApplication.Appointment.Booking.security;

import com.AppointmentBookingApplication.Appointment.Booking.entity.Doctor;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Patient;
import com.AppointmentBookingApplication.Appointment.Booking.repository.DoctorRepository;
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
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    Set<GrantedAuthority> authorities;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//            Patient patient =  patientRepository.findByPatientMail(patientMail)
//                    .orElseThrow(()->new RuntimeException("Patient Not Found"));

        // First, try to find the user as a Patient
        Patient patient = patientRepository.findByPatientMail(email).orElse(null);

        // If the email doesn't belong to a Patient, try to find the user as a Doctor
        if (patient == null) {
            Doctor doctor = doctorRepository.findByDoctorMail(email).orElse(null);

            if (doctor == null) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }else {

                // Doctor is found, create and return Doctor-specific UserDetails
                authorities = doctor.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                        .collect(Collectors.toSet());


                return org.springframework.security.core.userdetails.User.builder()
                        .username(doctor.getDoctorMail())
                        .password(doctor.getPassword())
                        .authorities(authorities)
                        .build();

            }
        }else {


            authorities = patient.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toSet());
        }
//        return new org.springframework.security.core.userdetails.User(
//                patientMail,patient.getPassword(),authorities

            return org.springframework.security.core.userdetails.User.builder()
                    .username(patient.getPatientMail())
                    .password(patient.getPassword())
                    .authorities(authorities)
                    .build();

        }
    }
