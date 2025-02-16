package com.AppointmentBookingApplication.Appointment.Booking.service;

import com.AppointmentBookingApplication.Appointment.Booking.dto.DepartmentDto;

import java.util.List;


public interface DepartmentService {
    DepartmentDto findByDeptName(String deptName);
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(long deptId, DepartmentDto departmentDto);
    List<DepartmentDto> getALlDepartment();
}
