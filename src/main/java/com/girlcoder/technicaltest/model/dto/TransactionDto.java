package com.girlcoder.technicaltest.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransactionDto {
    @NotNull(message = "From Cannot be null")
    private String from;
    @NotNull(message = "To Cannot be null")
    private String to;
    @NotNull(message = "Amount Cannot be null")
    private Double amount;
}
