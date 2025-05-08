package com.tenpo.challenge.calculate.percentage.service.impl;

import com.tenpo.challenge.calculate.percentage.dto.CalculateDTO;
import com.tenpo.challenge.calculate.percentage.event.SuccessEvent;
import com.tenpo.challenge.calculate.percentage.service.PercentageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculateServiceImplTest {

    @Mock
    private PercentageService percentageService;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private CalculateServiceImpl calculateService;

    private CalculateDTO dto;

    @BeforeEach
    void setUp() {
        dto = new CalculateDTO();
        dto.setNum1(100.00);
        dto.setNum2(50.00);
    }

    @Test
    void testCalculatePercentage() {

        when(percentageService.getPercetange()).thenReturn(10.0);

        double result = calculateService.calculatePercentage(dto);

        double expectedResult = (100 + 50) + ((100 + 50) * 10 / 100); // (100 + 50) + ((100 + 50) * 10%)
        assertEquals(expectedResult, result);

        verify(publisher, times(1)).publishEvent(any(SuccessEvent.class));
    }
}
