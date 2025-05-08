package com.tenpo.challenge.calculate.percentage.controller;

import com.tenpo.challenge.calculate.percentage.entity.CallLog;
import com.tenpo.challenge.calculate.percentage.service.CallLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith({MockitoExtension.class})
class CallLogControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Mock
    private CallLogService callLogService;

    @InjectMocks
    private CallLogController callLogController;

    @Test
    void findAll() {

        CallLog callLog1 = new CallLog();
        callLog1.setId(1L);
        callLog1.setEndpoint("/api/calls");
        callLog1.setParameters("param1=value1");
        callLog1.setResponse("200 OK");
        callLog1.setTimestamp(LocalDateTime.of(2025, 5, 1, 14, 30));

        CallLog callLog2 = new CallLog();
        callLog2.setId(2L);
        callLog2.setEndpoint("/api/calls");
        callLog2.setParameters("param2=value2");
        callLog2.setResponse("404 Not Found");
        callLog2.setTimestamp(LocalDateTime.of(2025, 5, 2, 15, 45));
        List<CallLog> callLogs = Arrays.asList(callLog1, callLog2);

        when(callLogService.getCallHistory()).thenReturn(callLogs);

        ResponseEntity<List<CallLog>> response = callLogController.findAll();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(callLogs, response.getBody());
        verify(callLogService, times(1)).getCallHistory();
    }
}