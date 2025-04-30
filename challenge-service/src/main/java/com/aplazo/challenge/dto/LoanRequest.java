package com.aplazo.challenge.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest {
    private String customerId;
    private Double amount;
}
