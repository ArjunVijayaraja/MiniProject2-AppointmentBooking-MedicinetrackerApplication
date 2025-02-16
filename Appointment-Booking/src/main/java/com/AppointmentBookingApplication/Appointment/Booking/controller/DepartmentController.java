package com.AppointmentBookingApplication.Appointment.Booking.controller;

import com.AppointmentBookingApplication.Appointment.Booking.dto.DepartmentDto;
import com.AppointmentBookingApplication.Appointment.Booking.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping("/createDept")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto createdDeptDto = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<DepartmentDto>(createdDeptDto, HttpStatus.CREATED);

    }

}
