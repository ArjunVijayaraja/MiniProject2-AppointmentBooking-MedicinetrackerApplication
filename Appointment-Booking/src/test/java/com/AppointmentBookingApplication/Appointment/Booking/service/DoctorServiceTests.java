package com.AppointmentBookingApplication.Appointment.Booking.service;

import com.AppointmentBookingApplication.Appointment.Booking.dto.DoctorDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Doctor;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Role;
import com.AppointmentBookingApplication.Appointment.Booking.repository.AppointmentRepository;
import com.AppointmentBookingApplication.Appointment.Booking.repository.DoctorRepository;
import com.AppointmentBookingApplication.Appointment.Booking.repository.RoleRepository;
import com.AppointmentBookingApplication.Appointment.Booking.service.serviceImpl.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTests {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;


    private Role role;
    private DoctorDto doctorDto;
    private DoctorDto savedDoctorDto;
    private Doctor doctor;
    private Doctor savedDoctor;
    private Set<Role> roleSet;
    @BeforeEach
    void setUp(){
        doctorDto = new DoctorDto(null,"Arjun",
                LocalDate.of(2000,12,8),'M',"arjun@gmail.com",
                "test123","LIVE","TEST DEPT1");

        savedDoctorDto = new DoctorDto(1L,"Arjun",
                LocalDate.of(2000,12,8),'M',"arjun@gmail.com",
                "test123","LIVE","TEST DEPT1");


        doctor = new Doctor();
        doctor.setDoctorId(null);
        doctor.setDoctorStatus("LIVE");
        doctor.setDoctorMail("arjun@gmail.com");
        doctor.setPassword("encodedPassword");
        doctor.setGender('M');
        doctor.setEntryDate(LocalDateTime.now());
        doctor.setUpdatedDate(LocalDateTime.now());
        doctor.setDob(LocalDate.of(2000,12,8));
        doctor.setDepartmentName("TEST DEPT1");
        roleSet = new HashSet<>();
        roleSet.add(role);
        doctor.setRoles(roleSet);


        savedDoctor = new Doctor();
        savedDoctor.setDoctorId(1L);
        savedDoctor.setDoctorStatus("LIVE");
        savedDoctor.setDoctorMail("arjun@gmail.com");
        savedDoctor.setPassword("encodedPassword");
        savedDoctor.setGender('M');
        savedDoctor.setEntryDate(LocalDateTime.now());
        savedDoctor.setUpdatedDate(LocalDateTime.now());
        savedDoctor.setDob(LocalDate.of(2000,12,8));
        savedDoctor.setDepartmentName("TEST DEPT1");


        role = new Role(2L,"ROLE_DOCTOR");
    }

    @Test
    public void givenDoctor_whenSaveDoctor_thenReturnDoctor(){

        // Given (Arrange)
        given(doctorRepository.findByDoctorMail(doctorDto.getDoctorMail())).willReturn(Optional.empty());
        given(roleRepository.findById(2L)).willReturn(Optional.of(role));
        given(modelMapper.map(doctorDto, Doctor.class)).willReturn(doctor);
        given(doctorRepository.save(doctor)).willReturn(savedDoctor);
        given(modelMapper.map(savedDoctor, DoctorDto.class)).willReturn(savedDoctorDto);

        // When (Act)
        DoctorDto result = doctorService.createDoctor(doctorDto);
        System.out.println(result);

        // Then (Assert)
        assertNotNull(result);
        assertEquals(1L, result.getDoctorId());
        assertEquals("Arjun", result.getDoctorName());

        // Verify interactions
        verify(doctorRepository, times(1)).findByDoctorMail(doctorDto.getDoctorMail());
        verify(roleRepository,times(1)).findById(2L);
        verify(modelMapper, times(1)).map(doctorDto, Doctor.class);
        verify(doctorRepository, times(1)).save(doctor);
        verify(modelMapper, times(1)).map(savedDoctor, DoctorDto.class);
    }

    @Test
    public void givenExistingDoctor_whenSave_thenReturnAlreadyExistException(){
        //given
        given(doctorRepository.findByDoctorMail(doctorDto.getDoctorMail())).willReturn(Optional.of(savedDoctor));

        Exception exception = assertThrows(RuntimeException.class,()->
                doctorService.createDoctor(doctorDto));


        //verify
        String expectedMessage = "Doctor Already exists with mail : "+doctorDto.getDoctorMail();
        assertEquals(expectedMessage,exception.getMessage());
    }

}
