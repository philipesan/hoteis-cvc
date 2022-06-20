package com.prova.cvc.dtos.responses;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prova.cvc.models.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomResponseDTO {
	private Integer id;
	private Category category;
	private BigDecimal totalPrice;
	private PriceDetailResponseDTO priceDetail;
}
