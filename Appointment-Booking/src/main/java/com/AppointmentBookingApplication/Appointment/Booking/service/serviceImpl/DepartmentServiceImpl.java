package com.AppointmentBookingApplication.Appointment.Booking.service.serviceImpl;

import com.AppointmentBookingApplication.Appointment.Booking.dto.DepartmentDto;
import com.AppointmentBookingApplication.Appointment.Booking.entity.Department;
import com.AppointmentBookingApplication.Appointment.Booking.repository.DepartmentRepository;
import com.AppointmentBookingApplication.Appointment.Booking.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private ModelMapper modelMapper;
    private DepartmentRepository departmentRepository;


    @Override
    public DepartmentDto findByDeptName(String deptName) {
        Department department = departmentRepository.findByDepartmentName(deptName).orElseThrow(() -> new RuntimeException("NO Department available with name "+ deptName));
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentName(departmentDto.getDepartmentName());
        if(optionalDepartment.isEmpty()){
            Department department =modelMapper.map(departmentDto, Department.class);
            department.setEntryDate(LocalDateTime.now());
            department.setUpdatedDate(LocalDateTime.now());
            Department createdDepartment =departmentRepository.save(department);
            return modelMapper.map(createdDepartment, DepartmentDto.class);
        }
        else{
            throw new RuntimeException("Department "+ departmentDto.getDepartmentName() +" already existes");
        }
    }

    @Override
    public DepartmentDto updateDepartment(long deptId, DepartmentDto departmentDto) {
        return null;
    }

    @Override
    public List<DepartmentDto> getALlDepartment() {
        List<Department> departmentLst = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoLst = departmentLst.stream().map(i->modelMapper.map(i,DepartmentDto.class)).collect(Collectors.toList());
        return departmentDtoLst;
    }
}
