package com.mini.commute.controller.employee;

import com.mini.commute.common.ResponseData;
import com.mini.commute.dto.employee.EmployeeRequest;
import com.mini.commute.dto.employee.EmployeeResponse;
import com.mini.commute.service.employee.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<ResponseData> saveEmployee(@RequestBody EmployeeRequest request) {
        ResponseData responseData = new ResponseData(HttpStatus.OK.value(), request);
        employeeService.save(request);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<ResponseData> getAllEmployees() {
        List<EmployeeResponse> employeeResponses = employeeService.getEmployees();
        ResponseData responseData = new ResponseData(HttpStatus.OK.value(), employeeResponses);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
