package com.AppointmentBookingApplication.Appointment.Booking.repository;

import com.AppointmentBookingApplication.Appointment.Booking.entity.PrescribedMedicine;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PrescribedMedicineRepository extends JpaRepository<PrescribedMedicine,Long> {

//    @Transactional
//    @Modifying
//    @Query("DELETE from PrescribedMedicine pm where pm.medicine.medicineName = :medName and pm.prescription.prescriptionID = :prescId")
//    int deleteByMedicineName(@Param("medName") String medName, @Param("prescId") long prescId);

    Optional<PrescribedMedicine> findByMedicine_MedicineName(String medName);
}
