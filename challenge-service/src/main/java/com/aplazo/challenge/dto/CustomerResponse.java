package com.aplazo.challenge.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private Double creditLineAmount;
    private Double availableCreditLineAmount;
    private String createdAt;
}
