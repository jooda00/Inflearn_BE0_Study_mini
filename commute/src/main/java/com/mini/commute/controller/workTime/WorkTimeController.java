package com.mini.commute.controller.workTime;

import com.mini.commute.common.ResponseData;
import com.mini.commute.dto.workTime.WorkTimeStartResponse;
import com.mini.commute.service.workTime.WorkTimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/work")
public class WorkTimeController {
    private final WorkTimeService workTimeService;

    public WorkTimeController(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }

    @PostMapping("/start/{id}/{date}")
    public ResponseEntity<WorkTimeStartResponse> startWork(@PathVariable("id") Long id, @PathVariable("date") LocalDate date) {
        WorkTimeStartResponse response = workTimeService.startWork(id, date);
        ResponseData responseData = new ResponseData(200, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
