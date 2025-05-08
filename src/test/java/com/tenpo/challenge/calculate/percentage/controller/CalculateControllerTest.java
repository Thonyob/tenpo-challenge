package com.tenpo.challenge.calculate.percentage.controller;

import com.tenpo.challenge.calculate.percentage.dto.CalculateDTO;
import com.tenpo.challenge.calculate.percentage.service.CalculateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith({MockitoExtension.class})
class CalculateControllerTest {

    @Mock
    private CalculateService calculateService;

    @InjectMocks
    private CalculateController calculateController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculatePercentageResponseOK() {

        CalculateDTO dto = new CalculateDTO();
        dto.setNum1(50.0);
        dto.setNum2(30.0);

        double expectedResult = 100.0;
        when(calculateService.calculatePercentage(dto)).thenReturn(expectedResult);

        ResponseEntity<Double> response = calculateController.calculatePercentage(dto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedResult, response.getBody());

        verify(calculateService, times(1)).calculatePercentage(dto);
    }

    @Test
    void calculatePercentageResponseError() {

        CalculateDTO dto = new CalculateDTO();
        dto.setNum1(50.0);
        dto.setNum2(30.0);

        Mockito.when(calculateService.calculatePercentage(dto)).thenThrow(new RuntimeException("Error en el servicio"));

        try {
            calculateController.calculatePercentage(dto);
            fail("Exception");
        } catch (RuntimeException e) {
            assertEquals("Error en el servicio", e.getMessage());
        }

        Mockito.verify(calculateService, Mockito.times(1)).calculatePercentage(dto);
    }
}