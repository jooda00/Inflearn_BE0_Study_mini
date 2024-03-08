package com.mini.commute.controller.annual;

import com.mini.commute.common.ResponseData;
import com.mini.commute.dto.annual.AnnualRemainResponse;
import com.mini.commute.service.annual.AnnualService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/annuals")
public class AnnualController {
    private final AnnualService annualService;

    public AnnualController(AnnualService annualService) {
        this.annualService = annualService;
    }

    @PostMapping("{id}/{date}")
    public ResponseEntity<ResponseData> registerAnnual(@PathVariable("id") Long id, @PathVariable("date") LocalDate date) {
        AnnualRemainResponse response = annualService.useAnnual(id, date);
        ResponseData responseData = new ResponseData(HttpStatus.OK.value(), response);
        return ResponseEntity.ok(responseData);
    }
}
