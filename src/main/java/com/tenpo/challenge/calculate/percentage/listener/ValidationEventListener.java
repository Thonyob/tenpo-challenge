package com.tenpo.challenge.calculate.percentage.listener;

import com.tenpo.challenge.calculate.percentage.entity.CallLog;
import com.tenpo.challenge.calculate.percentage.event.ErrorEvent;
import com.tenpo.challenge.calculate.percentage.event.SuccessEvent;
import com.tenpo.challenge.calculate.percentage.service.CallLogService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidationEventListener {

    private final CallLogService callLogService;

    public ValidationEventListener(CallLogService callLogService) {
        this.callLogService = callLogService;
    }

    @Async
    @EventListener
    public void onValidationError(ErrorEvent event) {
        CallLog log = new CallLog();
        log.setTimestamp(LocalDateTime.now());
        log.setEndpoint("/calculatePercentage");
        log.setParameters("Error");
        log.setResponse(event.getResponse());
        callLogService.saveCallLog(log);
    }

    @Async
    @EventListener
    public void onValidationSuccess(SuccessEvent event) {
        CallLog log = new CallLog();
        log.setTimestamp(LocalDateTime.now());
        log.setEndpoint("/calculatePercentage");
        log.setParameters(event.getParams());
        log.setResponse(event.getResponse());
        callLogService.saveCallLog(log);
    }

}
