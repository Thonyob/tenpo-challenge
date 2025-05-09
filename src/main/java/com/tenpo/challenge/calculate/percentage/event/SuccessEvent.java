package com.tenpo.challenge.calculate.percentage.event;

import lombok.Data;


@Data
public class SuccessEvent {

    private String params;
    private String response;

    public SuccessEvent(String params, String response) {
        this.params = params;
        this.response = response;
    }


}
