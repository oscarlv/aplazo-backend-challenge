package com.aplazo.challenge.dto;

import com.aplazo.challenge.model.InstallmentStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstallmentResponse {
    private Double amount;
    private LocalDate scheduledPaymentDate;
    private InstallmentStatus status;
}
