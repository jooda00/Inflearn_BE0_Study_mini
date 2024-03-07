package com.mini.commute.controller.work;

import com.mini.commute.common.ResponseData;
import com.mini.commute.dto.work.WorkResponse;
import com.mini.commute.service.work.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
