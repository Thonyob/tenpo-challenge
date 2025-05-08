package com.tenpo.challenge.calculate.percentage.service.impl;

import com.tenpo.challenge.calculate.percentage.entity.CallLog;
import com.tenpo.challenge.calculate.percentage.repository.CallLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CallLogServiceImplTest {

    private CallLogRepository callLogRepository;
    private CallLogServiceImpl callLogService;

    @BeforeEach
    void setUp() {
        callLogRepository = mock(CallLogRepository.class);
        callLogService = new CallLogServiceImpl(callLogRepository);
    }

    @Test
    void testSaveCallLog() {
        CallLog log = new CallLog();
        log.setEndpoint("/calculatePercentage");
        log.setParameters("5, 10");
        log.setResponse("Success");
        log.setTimestamp(LocalDateTime.now());

        callLogService.saveCallLog(log);

        ArgumentCaptor<CallLog> captor = ArgumentCaptor.forClass(CallLog.class);
        verify(callLogRepository, times(1)).save(captor.capture());

        CallLog savedLog = captor.getValue();
        assertEquals("/calculatePercentage", savedLog.getEndpoint());
        assertEquals("5, 10", savedLog.getParameters());
        assertEquals("Success", savedLog.getResponse());
    }

    @Test
    void testGetCallHistory() {
        CallLog log1 = new CallLog();
        log1.setId(1L);
        log1.setEndpoint("/calculatePercentage");

        CallLog log2 = new CallLog();
        log2.setId(2L);
        log2.setEndpoint("/history");

        when(callLogRepository.findAll()).thenReturn(Arrays.asList(log1, log2));

        List<CallLog> result = callLogService.getCallHistory();

        assertEquals(2, result.size());
        assertEquals("/calculatePercentage", result.get(0).getEndpoint());
        assertEquals("/history", result.get(1).getEndpoint());

        verify(callLogRepository, times(1)).findAll();
    }
}
