package com.prova.cvc.hoteis.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prova.cvc.dtos.responses.ApiResponseDTO;

@Service
public interface HoteisService {


	ResponseEntity<ApiResponseDTO> getHoteisCidade(String cityId, String checkInDate, String checkOutDate,
			String numberOfAdults, String numberOfChildren, String hotelId);
	
}
