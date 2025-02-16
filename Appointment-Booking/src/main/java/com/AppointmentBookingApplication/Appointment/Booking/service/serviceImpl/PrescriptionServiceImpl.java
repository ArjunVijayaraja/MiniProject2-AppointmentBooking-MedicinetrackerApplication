package com.AppointmentBookingApplication.Appointment.Booking.service.serviceImpl;

import com.AppointmentBookingApplication.Appointment.Booking.dto.MedicineDto;
import com.AppointmentBookingApplication.Appointment.Booking.dto.PrescribedMedicineDto;
import com.AppointmentBookingApplication.Appointment.Booking.dto.PrescriptionDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.*;
import com.AppointmentBookingApplication.Appointment.Booking.repository.*;
import com.AppointmentBookingApplication.Appointment.Booking.service.PrescriptionService;
import com.AppointmentBookingApplication.Appointment.Booking.utilsMethods.CommonFunctions;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {
    private ModelMapper modelMapper;
    private PrescriptionRepository prescriptionRepository;
    private MedicineRepository medicineRepository;
    private PrescribedMedicineRepository prescribedMedicineRepository;
    private AppointmentRepository appointmentRepository;
    private PatientRepository patientRepository;
    private CommonFunctions cm;

    @Override
    public PrescriptionDto createPrescription(PrescriptionDto prescriptionDto) {
        List<PrescribedMedicine> prescribedMedicineList = prescriptionDto.getPrescribedMedicines().stream().map(i->modelMapper.map(i, PrescribedMedicine.class)).collect(Collectors.toList());
        //Patient patient = modelMapper.map(prescriptionDto.getPatient(), Patient.class);
        prescribedMedicineList.forEach(i->{
        i.setMedicine(medicineRepository.findByMedicineName(i.getMedicine().getMedicineName())
                        .orElseThrow(()->new RuntimeException("Medicine dose not exist by name "+i.getMedicine().getMedicineName())));
         });

        Appointment appointment = appointmentRepository.findById(prescriptionDto.getAppointment().getAppointmentId()).get();
        Prescription prescription = new Prescription();
       // prescription.setPatient(patient);
        prescription.setAppointment(appointment);
        prescription.setPrescribedMedicines(prescribedMedicineList);
        prescription.setTotalNoOfMedicines(prescribedMedicineList.size());
        prescription.setEntryDate(LocalDateTime.now());
        prescription.setUpdatedDate(LocalDateTime.now());
//        Prescription savedPrescription =  prescriptionRepository.save(prescription);
        Prescription savedPrescription =  prescriptionRepository.save(prescription);
        prescribedMedicineList.forEach(i -> i.setPrescription(savedPrescription));
        prescribedMedicineRepository.saveAll(prescribedMedicineList);
        //Appointment appointment =  appointmentRepository.findById(prescriptionDto.getAppointmentId()).orElseThrow(()->new RuntimeException("No such appointment Found"));
        //appointment.setPrescription(savedPrescription);
        //appointmentRepository.save(appointment);
        return modelMapper.map(savedPrescription,PrescriptionDto.class);
    }

    //this is for old API METHOD
//    @Override
//    public PrescriptionDto createPrescription(Long appointId,List<PrescribedMedicineDto> prescribedMedicineDtoList) {
//        //modelMapper.typeMap(MedicineDto.class, Medicine.class);
//        //modelMapper.typeMap(PrescriptionDto.class, Prescription.class);
//
//        Optional<Appointment> appointment = appointmentRepository.findById(appointId);
//        if(appointment.get().getPrescription() == null){
//            List<PrescribedMedicine> prescribedMedicineList = prescribedMedicineDtoList.stream().map(i->modelMapper.map(i, PrescribedMedicine.class)).collect(Collectors.toList());
//            prescribedMedicineList.forEach(i->{
//                i.setMedicine(medicineRepository.findByMedicineName(i.getMedicine().getMedicineName())
//                        .orElseThrow(()->new RuntimeException("Medicine dose not exist by name "+i.getMedicine().getMedicineName())));
//            });
//            Prescription prescription = new Prescription();
//            prescription.setTotalNoOfMedicines(prescribedMedicineList.size());
//            prescription.setEntryDate(LocalDateTime.now());
//            prescription.setUpdatedDate(LocalDateTime.now());
//            prescribedMedicineList.forEach(i->
//                    i.setPrescription(prescription));
//            prescription.setPrescribedMedicines(prescribedMedicineList);
//            Prescription savedPrescription =  prescriptionRepository.save(prescription);
//            appointment.get().setAppointmentStatus("COMPLETED");
//            appointment.get().setPrescription(savedPrescription);
//            appointmentRepository.save(appointment.get());
//            return modelMapper.map(savedPrescription,PrescriptionDto.class);
//
//        }
//        else {
//            throw  new RuntimeException("Prescription Already exists, please edit the existing prescription...!");
//        }
//
//    }





    @Override
    public PrescriptionDto updatePrescription(Long prescriptionId, List<PrescribedMedicineDto> prescribedMedicineDtoList) {
        Optional<Prescription> optionalPrescription = prescriptionRepository.findById(prescriptionId);
        //List<PrescribedMedicine> newPrescribedMedsLst = modelMapper.map(prescribedMedicineDtoList,PrescribedMedicine.class);
        List<PrescribedMedicine> newPm = new ArrayList<>();
        List<PrescribedMedicine> updatedPm = new ArrayList<>();
        if (optionalPrescription.isPresent()) {
           // List<PrescribedMedicine> existingPrescribedMeds = optionalPrescription.get().getPrescribedMedicines();
            Map<String, PrescribedMedicine> existingPrescribedMedMap = optionalPrescription.get().getPrescribedMedicines().stream()
                    .collect(Collectors.toMap(pm -> pm.getMedicine().getMedicineName(), pm -> pm));
            System.out.println(existingPrescribedMedMap);



            for (PrescribedMedicineDto dto : prescribedMedicineDtoList) {
                Optional<Medicine> med = medicineRepository.findByMedicineName(dto.getMedicine().getMedicineName());
                if (med.isPresent()) {
                    if (existingPrescribedMedMap.containsKey(dto.getMedicine().getMedicineName())) {
                        PrescribedMedicine existingPm = existingPrescribedMedMap.get(dto.getMedicine().getMedicineName());
                        if (!existingPm.getDuration().equalsIgnoreCase(dto.getDuration()) ||
                                !existingPm.getInstructions().equalsIgnoreCase(dto.getInstructions())) {
                            existingPm.setDuration(dto.getDuration());
                            existingPm.setInstructions(dto.getInstructions());
                        }
                        updatedPm.add(existingPm);
                    } else {
                        PrescribedMedicine Pm = new PrescribedMedicine();
                        Pm.setInstructions(dto.getInstructions());
                        Pm.setDuration(dto.getDuration());
                        Pm.setPrescription(optionalPrescription.get());
                        Pm.setMedicine(med.get());
                        newPm.add(Pm);
                    }

                }else {
                    throw new RuntimeException("NO MEDICINE FOUND"+dto.getMedicine().getMedicineName());
                }
            }
        } else {
            throw new RuntimeException("Prescription Dose not exist ID: " + prescriptionId);
        }
        List<PrescribedMedicine> finalPmLst = new ArrayList<>(updatedPm);
        finalPmLst.addAll(newPm);
        optionalPrescription.get().setPrescribedMedicines(finalPmLst);
        optionalPrescription.get().setTotalNoOfMedicines(finalPmLst.size());
        Prescription updatedPrescription = prescriptionRepository.save(optionalPrescription.get());
        return modelMapper.map(updatedPrescription,PrescriptionDto.class);
    }

    @Override
    public PrescriptionDto getPrescription(Long prescriptionId) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionId);
        return modelMapper.map(prescription,PrescriptionDto.class);
    }

  //  @Override
//    public String deletePrescribedMedicine(Long prescriptionId,String medName) {
//       Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(()-> new RuntimeException("NO PRESCRIPTION FOUND"));
//
//      //  Optional<PrescribedMedicine> optionalPrescribedMedicine = prescribedMedicineRepository.findByMedicine_MedicineName(medName);
////       prescribedMedicineRepository.deleteByMedicineName(optionalPrescribedMedicine.get().getMedicine().getMedicineName());
//        int  deletedroes = prescribedMedicineRepository.deleteByMedicineName(medName, prescriptionId);
//        if(deletedroes == 0){
//            throw new RuntimeException("NO Records were found to delete...!");
//        }
//
//        return  "Prescribed Medicine: "+medName+" removed from the Prescription";
//
//    }

    @Override
    public List<PrescriptionDto> getAllPrescriptionByPatientId(long PatientId) {
        Patient patient = patientRepository.findByPatientMail(cm.getUserMailFromAuthentication()).get();
        List<Prescription> prescriptionList = prescriptionRepository.findByAppointment_Patient_PatientId(patient.getPatientId());
        List<PrescriptionDto> prescriptionDtoList = prescriptionList.stream().map(i->modelMapper.map(i,PrescriptionDto.class))
                .collect(Collectors.toList());
        return prescriptionDtoList;
    }

    @Override
    public PrescriptionDto getPrescriptionByAppointmentId(long appointmentId) {
        Prescription prescription = prescriptionRepository.findByAppointment_AppointmentId(appointmentId).get();
        return modelMapper.map(prescription,PrescriptionDto.class);
    }

    @Override
    public String removePrescribedMedicineFromPrescription(long prescriptionId, String pmName) {

        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(()-> new RuntimeException("no prescription available"));
        PrescribedMedicine prescribedMedicine = prescription.getPrescribedMedicines().stream()
                .filter(i->i.getMedicine().getMedicineName().equalsIgnoreCase(pmName))
                        .findFirst()
                                .orElse(null);
        prescription.getPrescribedMedicines().remove(prescribedMedicine);
        System.out.println(prescribedMedicine);
        System.out.println(prescription);
//        prescribedMedicineRepository.delete(prescribedMedicine);
        prescribedMedicineRepository.deleteById(prescribedMedicine.getPrescribedMedicineId());
        prescription.setTotalNoOfMedicines(prescription.getPrescribedMedicines().size());
        prescriptionRepository.save(prescription);
        return "Prescribed Medicine deleted Successfully";

    }

    @Override
    public PrescriptionDto findPrescriptionByAppointmentId(long appointId) {
        Optional<Prescription> prescription = prescriptionRepository.findByAppointment_AppointmentId(appointId);
        return modelMapper.map(prescription,PrescriptionDto.class);
    }


}
