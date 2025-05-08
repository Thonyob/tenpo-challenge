package com.tenpo.challenge.calculate.percentage.event;

import lombok.Data;

@Data
public class ErrorEvent {

    private String response;

    public ErrorEvent(String response) {
        this.response = response;
    }

}
