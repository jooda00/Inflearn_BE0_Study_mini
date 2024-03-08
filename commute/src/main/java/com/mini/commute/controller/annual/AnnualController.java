package com.mini.commute.controller.annual;

import com.mini.commute.common.ResponseData;
import com.mini.commute.dto.annual.AnnualRemainResponse;
import com.mini.commute.service.annual.AnnualService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/annuals")
public class AnnualController {
    private final AnnualService annualService;

    public AnnualController(AnnualService annualService) {
        this.annualService = annualService;
    }

    @PostMapping("{id}")
    public ResponseEntity<ResponseData> useAnnual(@PathVariable("id") Long id) {
        AnnualRemainResponse response = annualService.useAnnual(id);
        ResponseData responseData = new ResponseData(200, response);
        return ResponseEntity.ok(responseData);
    }
}
