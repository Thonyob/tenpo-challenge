package com.tenpo.challenge.calculate.percentage.listener;

import com.tenpo.challenge.calculate.percentage.entity.CallLog;
import com.tenpo.challenge.calculate.percentage.event.ErrorEvent;
import com.tenpo.challenge.calculate.percentage.event.SuccessEvent;
import com.tenpo.challenge.calculate.percentage.service.CallLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ValidationEventListenerTest {

    @Mock
    private CallLogService callLogService;

    @InjectMocks
    private ValidationEventListener validationEventListener;


    @BeforeEach
    void setUp() {
    }

    //@Test
    void testOnValidationError() {

        ErrorEvent errorEvent = new ErrorEvent("400 Bad Request");

        validationEventListener.onValidationError(errorEvent);

        verify(callLogService, times(1)).saveCallLog(new CallLog() {{
            setEndpoint("/calculatePercentage");
            setParameters("Error");
            setResponse("400 Bad Request");
        }});
    }

    //@Test
    void testOnValidationSuccess() {

        SuccessEvent successEvent = new SuccessEvent("params=value1", "200 OK");

        validationEventListener.onValidationSuccess(successEvent);

        verify(callLogService, times(1)).saveCallLog(new CallLog() {{
            setEndpoint("/calculatePercentage");
            setParameters("params=value1");
            setResponse("200 OK");
        }});
    }
}