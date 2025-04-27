package com.challenge.costumer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private Integer id;
    private Double creditLineAmount;
    private Double availableCreditLineAmount;
    private String createdAt;
}
