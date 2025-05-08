package com.tenpo.challenge.calculate.percentage.service.impl;


import com.tenpo.challenge.calculate.percentage.entity.CallLog;
import com.tenpo.challenge.calculate.percentage.repository.CallLogRepository;
import com.tenpo.challenge.calculate.percentage.service.CallLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallLogServiceImpl implements CallLogService {

    private final CallLogRepository callLogRepository;

    public CallLogServiceImpl(CallLogRepository callLogRepository){
        this.callLogRepository=callLogRepository;
    }

    @Override
    @Async
    public void saveCallLog(CallLog callLog) {
        callLogRepository.save(callLog);
    }

    @Override
    public List<CallLog> getCallHistory() {
        return callLogRepository.findAll();
    }
}
