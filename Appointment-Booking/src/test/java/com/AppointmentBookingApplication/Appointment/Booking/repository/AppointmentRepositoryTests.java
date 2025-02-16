//package com.AppointmentBookingApplication.Appointment.Booking.repository;
//
//import com.AppointmentBookingApplication.Appointment.Booking.entity.*;
//import lombok.AllArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cglib.core.Local;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//
//@ActiveProfiles("test")  // Use H2 for tests
//@DataJpaTest
//
//public class AppointmentRepositoryTests {
//    @Autowired
//    private AppointmentRepository appointmentRepository;
//
//    private Appointment appointment;
//    private Patient patient;
//    private Doctor doctor;
//    private Slot slot;
//    private Medicine medicine;
//    private Department department;
//    private Prescription prescription;
//    private PrescribedMedicine prescribedMedicine;
//    private List<PrescribedMedicine> prescribedMedicineList;
//
//    @BeforeEach
//    public void setUp(){
//        patient = new Patient();
//        patient.setPatientName("Test Patient 1");
//        patient.setPassword("Test123");
//        patient.setPatientMail("Test1@gmail.com");
//        patient.setContactNumber("987654321");
////        patient.setAddress("CBE");
//        patient.setCountry("IND");
//        patient.setDob(LocalDate.of(2000,12,12));
////        patient.setState("ACTIVE");
//        patient.setPincode("641111");
//        patient.setPatientId(2222l);
//
//
//        slot = new Slot();
//        slot.setSlotId(1111l);
//        slot.setSlotName("SLOT 1");
//        slot.setSlotTiming("10AM to 11AM");
//
//        doctor = new Doctor();
//        doctor.setDoctorId(111l);
//        doctor.setDoctorName("Mr. Doctor 1");
//        doctor.setDoctorMail("Doctor1@gmail.com");
//        doctor.setDoctorStatus("ACTIVE");
//        doctor.setGender('M');
//
//
//        medicine= new Medicine();
//        medicine.setMedicineId(1111l);
//        medicine.setMedicineName("DOLO");
//        medicine.setMedicineDescription("TEST DESCRIPTION for DOLO");
//
//        department = new Department();
//        department.setDepartmentId(2222l);
//        department.setDepartmentName("TEST DEPT 1");
//        department.setDepartmentStatus("Active");
//
//
//
//
//
//        //prescribedMedicine.setPrescribedMedicineId(11l);
//        //prescribedMedicine.setMedicine(medicine);
//        //prescribedMedicine.setInstructions("TAKE for 10 Days - test");
//        //prescribedMedicine.setDuration("5050Test");
//        //prescribedMedicineList.add(prescribedMedicine);
//
//
////        prescription.setPrescribedMedicines(prescribedMedicineList);
////        prescription.setTotalNoOfMedicines(prescribedMedicineList.size());
////        prescription.setEntryDate(LocalDateTime.now());
////        prescription.setUpdatedDate(LocalDateTime.now());
//
//
//
//        appointment = new Appointment();
//        appointment.setAppointmentId(123l);
//        //appointment.setAppointmentDate(LocalDate.of(2025,));
//        appointment.setAppointmentDate(LocalDate.of(2025,2,2));
//        appointment.setPatient(patient);
//        appointment.setAppointmentStatus("CONFIRMED");
//        //appointment.setPrescription(prescription);
////        appointment.setDepartmentId(department.getDepartmentId());
////        appointment.setDoctorId(doctor.getDoctorId());
//        appointment.setEntryDate(LocalDateTime.now());
//        appointment.setUpdatedDate(LocalDateTime.now());
////        appointment.setSlotId(slot.getSlotId());
//
//
//    }
//
//
//    @Test
//    public void givenAppointmentObject_whenFindByDate_thenReturnAppointment(){
//
//        appointmentRepository.save(appointment);
//
//        //WHEN
//        List<Appointment> savedAppointment  = appointmentRepository.findByAppointmentDate(LocalDate.of(2025,2,2));
//        //Then
//        assertThat(savedAppointment).isNotNull();
//        //assertThat(savedAppointment).hasSize(1);  // Make sure there's exactly 1 appointment
//        assertThat(savedAppointment.get(0).getAppointmentStatus()).isEqualTo("CONFIRMED"); // Check the status
//    }
//}
