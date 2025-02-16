package com.AppointmentBookingApplication.Appointment.Booking.service.serviceImpl;

import com.AppointmentBookingApplication.Appointment.Booking.dto.AppointmentDto;
import com.AppointmentBookingApplication.Appointment.Booking.dto.AppointmentSaveDto;
import com.AppointmentBookingApplication.Appointment.Booking.dto.DepartmentDto;
import com.AppointmentBookingApplication.Appointment.Booking.dto.PatientDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.*;
import com.AppointmentBookingApplication.Appointment.Booking.exceptions.AppointmentAlreadyCancelledException;
import com.AppointmentBookingApplication.Appointment.Booking.repository.*;
import com.AppointmentBookingApplication.Appointment.Booking.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private ModelMapper modelMapper;
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;
    private DepartmentRepository departmentRepository;
    private SlotRepository slotRepository;
    private DoctorRepository doctorRepository;
    private PrescriptionRepository prescriptionRepository;

    @Override
    public AppointmentDto createAppointment(Long patientID, AppointmentSaveDto appointmentSaveDto) {
        Patient patient= patientRepository.findById(patientID).orElseThrow(()-> new RuntimeException("Patient Does not exist by id: "+patientID));
        Optional<Appointment> optionalAppoint = appointmentRepository.findByPatient_PatientIdAndAppointmentDate(patientID,appointmentSaveDto.getAppointmentDate());

        if(optionalAppoint.isEmpty() || optionalAppoint.get().getAppointmentStatus().equalsIgnoreCase("Canceled") || optionalAppoint.get().getAppointmentStatus().equalsIgnoreCase("Completed")){
            Appointment appointment = new Appointment();
            appointment .setPatient(patient);
            Slot slot = slotRepository.findById(appointmentSaveDto.getSlotId()).orElseThrow(()->new RuntimeException("Slot dose not exist"));
            appointment.setSlot(slot);
            Department department = departmentRepository.findById(appointmentSaveDto.getDepartmentId()).orElseThrow(()->new RuntimeException("Department dose not Exist"));
            appointment.setDepartment(department);
            Doctor doctor = doctorRepository.findById(appointmentSaveDto.getDoctorId()).orElseThrow(()->new RuntimeException("Doctor dose not exist"));
            appointment.setDoctor(doctor);
            System.out.println(appointmentSaveDto.getAppointmentDate());
            appointment.setAppointmentDate(appointmentSaveDto.getAppointmentDate());
            appointment.setEntryDate(LocalDateTime.now());
            appointment.setAppointmentStatus("CONFIRMED");
            Appointment savedAppointment = appointmentRepository.save(appointment);
            return  modelMapper.map(savedAppointment,AppointmentDto.class);
        }
        else {
                throw  new RuntimeException("Appointment already exist");
        }

    }

//    @Override
//    public AppointmentDto cancelAppointment(Long appointId) {
//        Optional<Appointment> existingAppointment = appointmentRepository.findById(appointId);
//        if(existingAppointment.isPresent()){
//            if(existingAppointment.get().getAppointmentStatus().equalsIgnoreCase("CONFIRMED")){
//                existingAppointment.get().setAppointmentStatus("CANCELED");
//                Appointment updatedAppointment =  appointmentRepository.save(existingAppointment.get());
//                return modelMapper.map(updatedAppointment,AppointmentDto.class);
//            }
//            else {
//                throw new RuntimeException("Appointment Already Canceled");
//            }
//        }else {
//            throw new RuntimeException("Appoint Dose not exist");
//        }
//
//    }


    @Override
    public AppointmentDto cancelAppointment(Long appointId) {
        Optional<Appointment> existingAppointment = appointmentRepository.findById(appointId);
        Optional<Prescription> prescription= prescriptionRepository.findByAppointment_AppointmentId(appointId);
        if(existingAppointment.isPresent() && prescription.isEmpty()){
            if(existingAppointment.get().getAppointmentStatus().equalsIgnoreCase("CONFIRMED")){
                existingAppointment.get().setAppointmentStatus("CANCELED");
                Appointment updatedAppointment =  appointmentRepository.save(existingAppointment.get());
                return modelMapper.map(updatedAppointment,AppointmentDto.class);
            }
            else {
                throw new RuntimeException("Appointment Already Canceled");
            }
        }else {
            //throw new RuntimeException("Appoint Dose not exist");
            throw new AppointmentAlreadyCancelledException(existingAppointment.get().getAppointmentId());
        }

    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(i-> modelMapper.map(i,AppointmentDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDto> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> patientAppointment =appointmentRepository.findByPatient_PatientId(patientId);
        List<AppointmentDto> patientAppointDto = patientAppointment.stream().map(i->modelMapper.map(i,AppointmentDto.class))
                .collect(Collectors.toList());
        return patientAppointDto;
    }

    @Override
    public List<AppointmentDto> getAppointmentByDate(LocalDate appointDate) {
        List<Appointment> appointmentList = appointmentRepository.findByAppointmentDate(appointDate);
        List<AppointmentDto> appointmentDtoList = appointmentList.stream().map(appoint -> modelMapper.map(appoint,AppointmentDto.class))
                .collect(Collectors.toList());

        return appointmentDtoList;
    }

    @Override
    public List<AppointmentDto> getAllAppointmentByPatientMail(String patientMail) {
        Patient patient = patientRepository.findByPatientMail(patientMail).get();
        List<Appointment> appointmentList = appointmentRepository.findByPatient_PatientMail(patientMail);
        //List<Appointment> appointmentList = appointmentRepository.findByPatient_PatientIdAndAppointmentStatus(patient.getPatientId(),"CONFIRMED");
        List<AppointmentDto> appointmentDtoList = appointmentList.stream().map(i-> modelMapper.map(i,AppointmentDto.class)).collect(Collectors.toList());
        return appointmentDtoList;
    }

    @Override
    public List<AppointmentDto> getAllConfirmedAppointmentByPatientMail(String patientMail) {
        Patient patient = patientRepository.findByPatientMail(patientMail).get();
        //List<Appointment> appointmentList = appointmentRepository.findByPatient_PatientMail(patientMail);
        List<Appointment> appointmentList = appointmentRepository.findByPatient_PatientIdAndAppointmentStatus(patient.getPatientId(),"CONFIRMED");
        List<AppointmentDto> appointmentDtoList = appointmentList.stream().map(i-> modelMapper.map(i,AppointmentDto.class)).collect(Collectors.toList());
        return appointmentDtoList;
    }


    @Override
    public AppointmentDto findByAppointmentId(long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(()->new RuntimeException("No such Appointment"));
        return modelMapper.map(appointment,AppointmentDto.class);
    }


}
