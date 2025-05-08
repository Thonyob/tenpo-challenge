package com.tenpo.challenge.calculate.percentage.controller;

import com.tenpo.challenge.calculate.percentage.entity.CallLog;
import com.tenpo.challenge.calculate.percentage.service.CallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
public class CallLogController {

    @Autowired
    CallLogService callLogService;

    @GetMapping()
    public ResponseEntity<List<CallLog>> findAll() {
        List<CallLog> callLogs = callLogService.getCallHistory();
        return ResponseEntity.ok(callLogs);
    }

}
