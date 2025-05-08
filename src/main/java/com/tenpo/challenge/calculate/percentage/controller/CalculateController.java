package com.tenpo.challenge.calculate.percentage.controller;

import com.tenpo.challenge.calculate.percentage.dto.CalculateDTO;
import com.tenpo.challenge.calculate.percentage.service.CalculateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/calculatePercentage")
public class CalculateController {

    @Autowired
    private CalculateService calculateService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculatePercentage(@RequestBody @Valid CalculateDTO dto) {

        double result = calculateService.calculatePercentage(dto);
        return ResponseEntity.ok(result);
    }

}
