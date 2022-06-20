package com.prova.cvc.dtos.responses;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceDetailResponseDTO {
	private BigDecimal pricePerDayAdult;
	private BigDecimal pricePerDayChildren;
}
