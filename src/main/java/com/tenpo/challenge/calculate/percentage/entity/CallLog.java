package com.tenpo.challenge.calculate.percentage.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "call_log")
public class CallLog {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String endpoint;
        private String parameters;
        private String response;
        private LocalDateTime timestamp;
    }
