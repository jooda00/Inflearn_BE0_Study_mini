package com.mini.commute.controller.work;

import com.mini.commute.common.ResponseData;
import com.mini.commute.dto.work.WorkResponse;
import com.mini.commute.dto.work.WorkResponseWithSum;
import com.mini.commute.service.work.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/works")
public class WorkController {
    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @PostMapping("/start/{id}/{date}")
    public ResponseEntity<ResponseData> startWork(@PathVariable("id") Long id, @PathVariable("date") LocalDate date) {
        WorkResponse response = workService.startWork(id, date);
        ResponseData responseData = new ResponseData(200, response);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/end/{id}/{date}")
    public ResponseEntity<ResponseData> endWork(@PathVariable("id") Long id, @PathVariable("date") LocalDate date) {
        WorkResponse response = workService.endWork(id, date);
        ResponseData responseData = new ResponseData(200, response);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/{id}/{date}")
    public ResponseEntity<ResponseData> getWorkingMinutes(@PathVariable("id") Long id, @PathVariable("date") LocalDate date) {
        WorkResponseWithSum response = workService.getWorkingMinutes(id, date);
        ResponseData responseData = new ResponseData(200, response);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
