package com.tenpo.challenge.calculate.percentage.service.impl;

import com.tenpo.challenge.calculate.percentage.dto.CalculateDTO;
import com.tenpo.challenge.calculate.percentage.event.ErrorEvent;
import com.tenpo.challenge.calculate.percentage.event.SuccessEvent;
import com.tenpo.challenge.calculate.percentage.service.CalculateService;
import com.tenpo.challenge.calculate.percentage.service.PercentageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
public class CalculateServiceImpl implements CalculateService {

    private ApplicationEventPublisher publisher;
    private final PercentageService percentageService;

    public CalculateServiceImpl(PercentageService percentageService,ApplicationEventPublisher publisher) {
        this.percentageService = percentageService;
        this.publisher=publisher;
    }

    @Override
    public double calculatePercentage(CalculateDTO dto) {

        double sum = sum(dto.getNum1(), dto.getNum2());
        double percentage = percentageService.getPercetange();
        double result = calculateFromPercentage(sum, percentage);

        publisher.publishEvent(new SuccessEvent( dto.getNum1()+" , "+ dto.getNum2(), "Success"));

        return result;
    }

    private double sum(double num1, double num2) {
        return num1 + num2;
    }

    private double calculateFromPercentage(double base, double percentage) {
        return base + (base * (percentage / 100));
    }
}
