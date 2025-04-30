package com.aplazo.challenge.dto;

import com.aplazo.challenge.model.LoanStatus;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponse {
    private String id;
    private String customerId;
    private Double amount;
    private LoanStatus status;
    private OffsetDateTime createdAt;
    private PaymentPlan paymentPlan;
}
