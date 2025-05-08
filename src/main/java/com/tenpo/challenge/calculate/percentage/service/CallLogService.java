package com.tenpo.challenge.calculate.percentage.service;

import com.tenpo.challenge.calculate.percentage.entity.CallLog;

import java.util.List;

public interface CallLogService {
    void saveCallLog(CallLog callLog);
    List<CallLog> getCallHistory();
}
