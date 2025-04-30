package com.aplazo.challenge.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentPlan {
    private Double commissionAmount;
    private List<InstallmentResponse> installments;
}
