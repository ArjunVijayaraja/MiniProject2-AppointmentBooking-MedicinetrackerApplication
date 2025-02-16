package com.AppointmentBookingApplication.Appointment.Booking.service.serviceImpl;

import com.AppointmentBookingApplication.Appointment.Booking.dto.DepartmentDto;
import com.AppointmentBookingApplication.Appointment.Booking.dto.DoctorDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Department;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Doctor;
import com.AppointmentBookingApplication.Appointment.Booking.repository.DepartmentRepository;
import com.AppointmentBookingApplication.Appointment.Booking.repository.DoctorRepository;
import com.AppointmentBookingApplication.Appointment.Booking.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private ModelMapper modelMapper;
    private DoctorRepository doctorRepository;
    private DepartmentRepository departmentRepository;


    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        Optional<Doctor> optionalDoctor =  doctorRepository.findByDoctorMail(doctorDto.getDoctorMail());
        if(optionalDoctor.isPresent()){
            throw new RuntimeException("Doctor Already exists with mail : "+doctorDto.getDoctorMail());
        }
        modelMapper.typeMap(DepartmentDto.class, Department.class);
        Doctor doctor = modelMapper.map(doctorDto, Doctor.class);

        doctor.setEntryDate(LocalDateTime.now());
        doctor.setUpdatedDate(LocalDateTime.now());
        List<Long> deptIds = doctorDto.getDepartmentList().stream().map(DepartmentDto::getDepartmentId).collect(Collectors.toList());
       // List<Department> deptList = departmentRepository.findAllById(deptIds);
        List<Department> deptList = doctorDto.getDepartmentList().stream().
                map(i->departmentRepository.findById(i.getDepartmentId()).get()).collect(Collectors.toList());
        //doctor.setDepartmentList(deptList);
        doctor.setDepartmentList(deptList);
        Doctor savedDoctor = doctorRepository.save(doctor);

        return modelMapper.map(savedDoctor, DoctorDto.class);
    }

    @Override
    public DoctorDto findDoctorById(long id) {
        return null;
    }

    @Override
    public List<DoctorDto> findAllDoctors() {

        List<Doctor> doctorList = doctorRepository.findAll();
        List<DoctorDto> doctorDtoList = doctorList.stream().map(i->modelMapper.map(i,DoctorDto.class)).collect(Collectors.toList());
        return doctorDtoList;
    }

    @Override
    public DoctorDto updateDoctor(long docId, DoctorDto doctorDto) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(docId);
        if(existingDoctor.isPresent()){
            List<Department> deptlst = existingDoctor.get().getDepartmentList();
            List<Long> dept = doctorDto.getDepartmentList().stream().map(DepartmentDto::getDepartmentId).collect(Collectors.toList());
            List<Department> newdeptlst = departmentRepository.findAllById(dept);
            newdeptlst.stream().map(i->deptlst.add(i)).collect(Collectors.toList());
            existingDoctor.get().setDepartmentList(deptlst);
            Doctor updatedDoctor = doctorRepository.save(existingDoctor.get());
            return modelMapper.map(updatedDoctor,DoctorDto.class);
        }
        else{
             throw new RuntimeException("Doctor dose not exist with provided mail id");
        }
    }
}
