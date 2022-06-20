package com.prova.cvc.hoteis.utils;

import java.math.BigDecimal;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;

import com.prova.cvc.dtos.responses.PriceDetailResponseDTO;

public class HoteisServiceUtils {
	public static Long calcularData(String dtInicio, String dtFim) throws ParseException, IllegalArgumentException {
		DateTime dataInicio = HoteisServiceUtils.converterEmData(dtInicio);
		DateTime dataFim = HoteisServiceUtils.converterEmData(dtFim);

		return Long.valueOf(Math.abs(Days.daysBetween(dataFim, dataInicio).getDays()));
	}

	public static DateTime converterEmData(String data) throws IllegalArgumentException {
		return DateTime.parse(data, DateTimeFormat.forPattern("dd/MM/YYYY"));
	}

	public static BigDecimal calculaValorTotal(PriceDetailResponseDTO priceDetailResponseDTO, BigDecimal diarias) {
		BigDecimal calculo = priceDetailResponseDTO.getPricePerDayAdult()
						.add(priceDetailResponseDTO.getPricePerDayChildren());
		
		return calculo.multiply(diarias);
	}
	
	public static BigDecimal calculaValorDiarias(BigDecimal unitario, BigDecimal pessoas) {
		return unitario.multiply(pessoas);
	}
	
	public static Boolean validaRequest(String cityId,
									   String numberOfAdults,
									   String numberOfChildren,
									   String hotelId, 
									   String checkInDate, 
									   String checkOutDate) {
		 
		return ((!StringUtils.isNumeric(cityId) | 
				!StringUtils.isNumeric(numberOfAdults) |
				!StringUtils.isNumeric(numberOfChildren) |
				(hotelId != null && !StringUtils.isNumeric(hotelId)) |
				!GenericValidator.isDate(checkInDate, "dd/MM/yyyy", true) |
				!GenericValidator.isDate(checkOutDate, "dd/MM/yyyy", true)) ? false :  true);
	}
}
